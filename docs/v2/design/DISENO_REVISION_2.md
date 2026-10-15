# StackFlowServer v2: diseño, revisión 2

Versión 2 · 15.10.2026 · Aprobada con ROGER.

**Precedencia:**
1. [DECISIONES.md](DECISIONES.md) (DA-xxx)
2. **este documento**
3. [DISENO_REST_UNIFICADO.md](DISENO_REST_UNIFICADO.md) y [DISENO_CLASES_REST.md](DISENO_CLASES_REST.md)

Los documentos 3 siguen siendo válidos en todo lo que este no cambia (modelo de errores, filtros, ciclo de vida de los jobs, RAG por dentro, modo sin RAG…), con los **nombres traducidos** según la tabla de la sección 11. La documentación de referencia del usuario son sus notas de Obsidian (`MerLab-Oekosystem-Updated`, `StackFlowServer_Architektur`, `StackFlowServer_IDE_und_Entwicklungsstrategie` y `StackFlowServer_Server_und_Hexagonal_Architektur`).

---

## 1. Decisiones de alcance (del usuario, 15.10.2026)

| # | Decisión |
|---|---|
| S1 | **StackFlowServer es la runtime de MerLabCore**: operaciones del stack RPN + ML sobre señales y datos (cargar señal → FFT → algoritmo ML…). Los pipelines de MerLabCore son **el núcleo**. |
| S2 | **Primeras aplicaciones: audio y trading.** |
| S3 | Se suben **archivos de todo tipo**: WAV, imágenes, CSV, JSON, HDF5… |
| S4 | **`stackflow-registry`**: registro unificado de operaciones con descubrimiento y ejecución. |
| S5 | **PegasusRAG** (= MerLabRAG) usa el mismo REST **pero no se mezcla** con MerLabCore. Sus resultados se guardan como archivo o en BD y, si se quiere, se **cargan después como data/señal** al stack. |
| S6 | **El PC Windows no entrena modelos ni ejecuta LLMs.** Los LLM corren en los dos **Mac mini**. **ModelForge** se desarrolla aparte, en el Mac mini; StackFlow solo lo llama por **API remota**. La inferencia de los algoritmos clásicos de MerLabCore sí corre en StackFlow. |
| S7 | **Merlynix** (el lenguaje del stack RPN) todavía no existe y va **al final**, cuando todo esté integrado. |
| S8 | Mandan los nombres de módulos de Obsidian y los nombres de clases v1. |
| S9 | Estilo del núcleo: **DA-001, opción C** (EJB para servicios, Java puro para el motor). |
| S10 | Sin Spring en v2 (notas aparte en [FUTURO_SPRING.md](FUTURO_SPRING.md)). IDE: NetBeans + WildFly. |
| S11 | Hecho técnico: **MerLabCore ya trae su motor RPN** (`RPNEngine`, `RPNStack`, `RPNParser`, `RPNOperation`, unas 50 operaciones: `norm`, `fft`, filtros, `ma`, `deriv`…). StackFlow **no lo duplica**: lo expone, lo registra y lo ejecuta. |

---

## 2. Módulos

Nombres de Obsidian, adaptados a DA-001. Cada módulo se crea **en el primer micropaso que lo necesita**, no todos al principio.

| Módulo | Tipo | Estilo | Contenido principal |
|---|---|---|---|
| `stackflow-api-contract` | jar | Java + `jakarta.validation` | DTOs `*DTO` (records) |
| `stackflow-domain` | jar | **Java puro** | `StackElement`, `OperationDescriptor`, `PipelineDefinition`, `PipelineStep`, `Job`, `JobStatus`, `JobType`, `Scenario`… |
| `stackflow-ports` | jar | **Java puro** | `MerLabCorePort`, `PegasusRagPort`, `ModelForgePort`, `MerlynixPort` (grupo N) |
| `stackflow-rpn` | jar | **Java puro** + 1 EJB | `StackSession`, `StackElementCodec`, `RpnExpression` · EJB `RPNStackServiceBean` |
| `stackflow-registry` | jar | EJB | `OperationRegistryLocal` / `OperationRegistryBean` (`@Singleton`) |
| `stackflow-runtime` | jar | EJB | `ExecutionContext`, `ResourceManagerBean` (límites de memoria), `MonitorServiceBean` |
| `stackflow-jobs` | jar | EJB | `OrchestratorServiceBean`, `JobQueueServiceBean`, `WorkerServiceBean`, `ScheduleServiceBean`, `PostProcessServiceBean`, `JobHandler` |
| `stackflow-pipeline` | jar | EJB + Java puro | `PipelineServiceBean`, `PipelineExecutorBean`, `PipelineRunner` (puro), `BatchProcessorServiceBean`, `StreamProcessorServiceBean`, `Buffer` (puro), `ScenarioManagerBean`, `ArchivingServiceBean` |
| `stackflow-storage` | jar | EJB + JPA | entidades, `XDaoLocal` / `XDaoBean` (DBAdapter v1), `FileStorageServiceBean`, `DataManagerServiceBean`, importadores |
| `stackflow-security` | jar | EJB | `UserServiceBean`, `AuthServiceBean`, `JwtServiceBean`, `ApiKeyServiceBean` |
| `stackflow-application` | jar | EJB | servicios de aplicación transversales: `ProjectServiceBean`, `ConfigServiceBean` (`ConfigProvider`), `HistoryServiceBean`, `MLCoreServiceBean`, `PegasusRagServiceBean`, `ModelServiceBean` |
| `stackflow-adapter-merlabcore` | jar | Java puro + producer | `MerLabCoreAdapter` (implementa `MerLabCorePort`) |
| `stackflow-adapter-pegasus` | jar · **perfil `pegasus`** | Java puro + producer | `PegasusRagAdapter`, `PegasusRuntimeProducer` |
| `stackflow-adapter-modelforge` | jar · **perfil `modelforge`** | Java puro + producer | `ModelForgeRemoteAdapter` (cliente HTTP al Mac mini) |
| `stackflow-adapter-merlynix` | jar · grupo N | — | más adelante |
| `stackflow-adapter-jakarta` | jar | Jakarta | `XResource`, `ExceptionMapper`, filtros, `XSocketEndpoint`, `SignalProcessorMDB`, `XMapper` (DTO ↔ dominio) |
| `stackflow-host-wildfly` | **war** | Jakarta | `StackflowApplication`, `StartupBean`, `web.xml`, `jboss-web.xml`, `jboss-deployment-structure.xml`, configuración |

```mermaid
flowchart BT
    DOM["stackflow-domain<br/>(pure Java)"]
    PORTS["stackflow-ports<br/>(pure Java)"]
    CONTRACT["stackflow-api-contract<br/>(DTOs)"]
    RPN["stackflow-rpn"]
    REG["stackflow-registry"]
    RT["stackflow-runtime"]
    JOBS["stackflow-jobs"]
    PIPE["stackflow-pipeline"]
    STO["stackflow-storage"]
    SEC["stackflow-security"]
    APP["stackflow-application"]
    AMC["adapter-merlabcore"]
    APG["adapter-pegasus<br/>(profile pegasus)"]
    AMF["adapter-modelforge<br/>(profile modelforge)"]
    AJK["adapter-jakarta"]
    HOST["host-wildfly (WAR)"]

    PORTS --> DOM
    RPN --> DOM
    RPN --> PORTS
    REG --> RPN
    RT --> DOM
    JOBS --> RT
    JOBS --> STO
    PIPE --> REG
    PIPE --> JOBS
    STO --> DOM
    SEC --> STO
    APP --> STO
    APP --> PORTS
    APP --> JOBS
    AMC --> PORTS
    APG --> PORTS
    AMF --> PORTS
    AJK --> CONTRACT
    AJK --> APP
    AJK --> PIPE
    AJK --> SEC
    HOST --> AJK
    HOST --> AMC
    HOST -. "profile pegasus" .-> APG
    HOST -. "profile modelforge" .-> AMF
```

```plantuml
@startuml r2-modules
skinparam componentStyle rectangle
[stackflow-domain\n(pure Java)] as DOM
[stackflow-ports\n(pure Java)] as PORTS
[stackflow-api-contract\n(DTOs)] as CONTRACT
[stackflow-rpn] as RPN
[stackflow-registry] as REG
[stackflow-runtime] as RT
[stackflow-jobs] as JOBS
[stackflow-pipeline] as PIPE
[stackflow-storage] as STO
[stackflow-security] as SEC
[stackflow-application] as APP
[adapter-merlabcore] as AMC
[adapter-pegasus\n(profile pegasus)] as APG
[adapter-modelforge\n(profile modelforge)] as AMF
[adapter-jakarta] as AJK
[host-wildfly (WAR)] as HOST

PORTS --> DOM
RPN --> DOM
RPN --> PORTS
REG --> RPN
RT --> DOM
JOBS --> RT
JOBS --> STO
PIPE --> REG
PIPE --> JOBS
STO --> DOM
SEC --> STO
APP --> STO
APP --> PORTS
APP --> JOBS
AMC --> PORTS
APG --> PORTS
AMF --> PORTS
AJK --> CONTRACT
AJK --> APP
AJK --> PIPE
AJK --> SEC
HOST --> AJK
HOST --> AMC
HOST ..> APG : profile pegasus
HOST ..> AMF : profile modelforge
@enduml
```

**Reglas de dependencia (ArchUnit):**
- `domain`, `ports` y las clases puras de `rpn` y `pipeline` no importan `jakarta.*`;
- solo `adapter-merlabcore` importa `com.merlab.signals..`;
- solo `adapter-pegasus` importa `pegasus..`;
- solo `adapter-modelforge` hace HTTP hacia ModelForge;
- nadie depende de `adapter-jakarta` salvo `host-wildfly`.

---

## 3. Despliegue con los recursos reales

```mermaid
flowchart LR
    subgraph WIN["PC Windows"]
        WF["WildFly 36<br/>stackflow.war"]
        MC[["MerLabCore<br/>(in-process)"]]
        PG[["PegasusRAG<br/>(in-process, optional)"]]
        DB[("MariaDB<br/>stackflowdb")]
        FS[("File storage<br/>WAV, images, CSV…")]
    end
    subgraph MAC1["Mac mini 1"]
        LLM["Ollama / LLMs<br/>embeddings"]
    end
    subgraph MAC2["Mac mini 2"]
        MF["ModelForge API<br/>training, fine-tuning"]
    end
    VS[("Vector / graph / cache stores<br/>Qdrant, pgvector, Neo4j, Redis<br/>(host configurable)")]
    CL["Clients<br/>FlowScope, Postman"]

    CL --> WF
    WF --> MC
    WF -.-> PG
    WF --> DB
    WF --> FS
    PG -- HTTP --> LLM
    PG --> VS
    WF -- "HTTP (profile modelforge)" --> MF
```

```plantuml
@startuml r2-deployment
left to right direction
node "PC Windows" {
  [WildFly 36\nstackflow.war] as WF
  artifact "MerLabCore\n(in-process)" as MC
  artifact "PegasusRAG\n(in-process, optional)" as PG
  database "MariaDB\nstackflowdb" as DB
  folder "File storage\nWAV, images, CSV..." as FS
}
node "Mac mini 1" {
  [Ollama / LLMs\nembeddings] as LLM
}
node "Mac mini 2" {
  [ModelForge API\ntraining, fine-tuning] as MF
}
database "Vector / graph / cache stores\nQdrant, pgvector, Neo4j, Redis\n(host configurable)" as VS
[Clients\nFlowScope, Postman] as CL

CL --> WF
WF --> MC
WF ..> PG
WF --> DB
WF --> FS
PG --> LLM : HTTP
PG --> VS
WF --> MF : HTTP (profile modelforge)
@enduml
```

> La asignación concreta de servicios a cada Mac mini (qué LLM corre en cuál, dónde vive ModelForge) es configuración, no código. El diagrama solo es un ejemplo. URLs en `ConfigProvider`: `pegasus.ollama.url`, `modelforge.api.url`.

---

## 4. Convenciones de nombres (v1 + Obsidian)

| Tipo | Patrón | Ejemplo |
|---|---|---|
| DTO | `<Nombre>DTO` (record) | `UserDTO`, `JobDTO`, `ErrorDTO`, `PipelineRequestDTO`, `RagRequestDTO` |
| Servicio EJB | `<Nombre>ServiceLocal` (`@Local`) + `<Nombre>ServiceBean` | `UserServiceLocal` / `UserServiceBean` |
| Bean de rol especial | `<Nombre>Bean` + `<Nombre>Local` | `PipelineExecutorBean`, `ScenarioManagerBean`, `OperationRegistryBean` |
| DAO | `<Entidad>DaoLocal` + `<Entidad>DaoBean` | `UserDaoBean`, `JobQueueDaoBean`, `SignalDaoBean` |
| Entidad JPA | nombre en plural, como en v1 y en la tabla | `Users`, `Signals`, `JobQueue`, `Pipelines` |
| Mapper | `<Nombre>Mapper` (DTO ↔ dominio/entidad) | `UserMapper`, `JobMapper` |
| Resource | `<Nombre>Resource` | `UserResource`, `OperationResource` |
| WebSocket | `<Nombre>SocketEndpoint` | `SignalSocketEndpoint`, `JobSocketEndpoint` |
| MDB | `<Nombre>MDB` | `SignalProcessorMDB` |
| Port | `<Motor>Port` | `MerLabCorePort`, `PegasusRagPort` |
| Adaptador | `<Motor>Adapter` | `MerLabCoreAdapter`, `PegasusRagAdapter` |
| Motor no instalado | `Unavailable<Motor>` | `UnavailablePegasusRag`, `UnavailableModelForge` |
| Paquete raíz | `com.merlab.stackflow.<módulo>` | `com.merlab.stackflow.jobs` |

---

## 5. Motor RPN, registro y pipelines (núcleo S1/S4/S11)

**Idea:** MerLabCore ya sabe ejecutar `RPNOperation` sobre su `RPNStack` mediante `RPNEngine`. StackFlow añade lo que hace falta para un servidor:
- **registro** con metadatos (`OperationDescriptor`), que unifica las operaciones RPN y los algoritmos ML del catálogo;
- **elementos de stack serializables** (`StackElement`), para enviarlos por REST, guardarlos y hacer snapshots;
- **sesiones de stack** con límites de memoria;
- **pipelines**: definición `LOAD → tokens RPN / operaciones ML → SAVE` y su ejecución como job.

```mermaid
classDiagram
    direction LR

    class StackElement {
        <<pure Java, record>>
        +ElementType type
        +String ref
        +Object value
        +Map meta
    }
    class ElementType {
        <<enumeration>>
        SCALAR
        VECTOR
        COMPLEX_VECTOR
        SIGNAL
        DATASET
        IMAGE
        TEXT
        MODEL_REF
    }
    class OperationDescriptor {
        <<pure Java, record>>
        +String token
        +String name
        +String category
        +int arity
        +String description
        +String example
        +OperationSource source
        +boolean sideEffects
    }
    class OperationSource {
        <<enumeration>>
        MERLABCORE_RPN
        MERLABCORE_ALGORITHM
        PLUGIN
        MERLYNIX
    }
    class StackSession {
        <<pure Java>>
        +String id
        +int maxDepth
        +push(StackElement)
        +pop() StackElement
        +peek() StackElement
        +snapshot() List~StackElement~
    }
    class PipelineDefinition {
        <<pure Java, record>>
        +String name
        +List~PipelineStep~ steps
    }
    class PipelineStep {
        <<pure Java, record>>
        +StepKind kind
        +String operation
        +Map params
    }
    class StepKind {
        <<enumeration>>
        LOAD
        RPN
        ALGORITHM
        SAVE
    }
    class PipelineRunner {
        <<pure Java>>
        +run(PipelineDefinition, StackSession, MerLabCorePort, StepListener) PipelineOutcome
    }
    class MerLabCorePort {
        <<interface, pure Java>>
        +listRpnOperations() List~OperationDescriptor~
        +listAlgorithms() List~OperationDescriptor~
        +applyRpn(String token, StackSession)
        +runAlgorithm(String id, StackSession, Map params) AlgorithmResult
        +health() EngineHealth
    }
    class MerLabCoreAdapter {
        <<adapter, pure Java>>
        -RPNEngine engine
        -AlgorithmCatalog catalog
        -StackElementCodec codec
    }
    class OperationRegistryBean {
        <<EJB Singleton>>
        +all() List~OperationDescriptor~
        +find(String token) Optional~OperationDescriptor~
        +search(String q) List~OperationDescriptor~
    }
    class RPNStackServiceBean {
        <<EJB Stateless>>
        +createSession(user) StackSession
        +push(sessionId, StackElement)
        +apply(sessionId, String token)
        +evaluate(String expression, Map inputs) List~StackElement~
    }
    class PipelineExecutorBean {
        <<EJB Stateless>>
        +execute(PipelineDefinition, JobContext) PipelineOutcome
    }
    class DataManagerServiceBean {
        <<EJB Stateless>>
        +load(String ref) StackElement
        +save(StackElement, String target) String
    }
    class RPNEngine {
        <<library: merlab-core>>
    }
    class RPNOperation {
        <<library: merlab-core>>
    }
    class AlgorithmCatalog {
        <<library: merlab-core>>
    }

    StackElement --> ElementType
    OperationDescriptor --> OperationSource
    StackSession o-- StackElement
    PipelineDefinition *-- PipelineStep
    PipelineStep --> StepKind
    PipelineRunner ..> PipelineDefinition
    PipelineRunner ..> StackSession
    PipelineRunner ..> MerLabCorePort
    MerLabCorePort <|.. MerLabCoreAdapter
    MerLabCoreAdapter --> RPNEngine
    RPNEngine o-- RPNOperation
    MerLabCoreAdapter --> AlgorithmCatalog
    OperationRegistryBean --> MerLabCorePort
    OperationRegistryBean o-- OperationDescriptor
    RPNStackServiceBean --> MerLabCorePort
    RPNStackServiceBean ..> StackSession
    PipelineExecutorBean ..> PipelineRunner : new
    PipelineExecutorBean --> DataManagerServiceBean : LOAD and SAVE
    PipelineExecutorBean --> OperationRegistryBean : validate steps
```

```plantuml
@startuml r2-rpn-core
left to right direction
skinparam classAttributeIconSize 0

package "stackflow-domain / stackflow-rpn (pure Java)" {
  class StackElement <<record>> {
    +type : ElementType
    +ref : String
    +value : Object
    +meta : Map<String,String>
  }
  enum ElementType {
    SCALAR
    VECTOR
    COMPLEX_VECTOR
    SIGNAL
    DATASET
    IMAGE
    TEXT
    MODEL_REF
  }
  class OperationDescriptor <<record>> {
    +token : String
    +name : String
    +category : String
    +arity : int
    +description : String
    +example : String
    +source : OperationSource
    +sideEffects : boolean
  }
  enum OperationSource {
    MERLABCORE_RPN
    MERLABCORE_ALGORITHM
    PLUGIN
    MERLYNIX
  }
  class StackSession {
    +id : String
    +maxDepth : int
    +push(StackElement)
    +pop() : StackElement
    +peek() : StackElement
    +snapshot() : List<StackElement>
  }
  class PipelineDefinition <<record>> {
    +name : String
    +steps : List<PipelineStep>
  }
  class PipelineStep <<record>> {
    +kind : StepKind
    +operation : String
    +params : Map<String,Object>
  }
  enum StepKind {
    LOAD
    RPN
    ALGORITHM
    SAVE
  }
  class PipelineRunner {
    +run(PipelineDefinition, StackSession, MerLabCorePort, StepListener) : PipelineOutcome
  }
}

package "stackflow-ports (pure Java)" {
  interface MerLabCorePort {
    +listRpnOperations() : List<OperationDescriptor>
    +listAlgorithms() : List<OperationDescriptor>
    +applyRpn(token, StackSession)
    +runAlgorithm(id, StackSession, params) : AlgorithmResult
    +health() : EngineHealth
  }
}

package "stackflow-adapter-merlabcore (pure Java)" {
  class MerLabCoreAdapter {
    -engine : RPNEngine
    -catalog : AlgorithmCatalog
    -codec : StackElementCodec
  }
}

package "EJB (registry, rpn, pipeline, storage)" {
  class OperationRegistryBean <<@Singleton>> {
    +all() : List<OperationDescriptor>
    +find(token) : Optional<OperationDescriptor>
    +search(q) : List<OperationDescriptor>
  }
  class RPNStackServiceBean <<@Stateless>> {
    +createSession(user) : StackSession
    +push(sessionId, StackElement)
    +apply(sessionId, token)
    +evaluate(expression, inputs) : List<StackElement>
  }
  class PipelineExecutorBean <<@Stateless>> {
    +execute(PipelineDefinition, JobContext) : PipelineOutcome
  }
  class DataManagerServiceBean <<@Stateless>> {
    +load(ref) : StackElement
    +save(StackElement, target) : String
  }
}

package "merlab-core.jar <<library>>" #EEEEEE {
  class RPNEngine
  interface RPNOperation
  class AlgorithmCatalog
}

StackElement --> ElementType
OperationDescriptor --> OperationSource
StackSession o-- StackElement
PipelineDefinition *-- PipelineStep
PipelineStep --> StepKind
PipelineRunner ..> PipelineDefinition
PipelineRunner ..> StackSession
PipelineRunner ..> MerLabCorePort
MerLabCorePort <|.. MerLabCoreAdapter
MerLabCoreAdapter --> RPNEngine
RPNEngine o-- RPNOperation
MerLabCoreAdapter --> AlgorithmCatalog
OperationRegistryBean --> MerLabCorePort
OperationRegistryBean o-- OperationDescriptor
RPNStackServiceBean --> MerLabCorePort
RPNStackServiceBean ..> StackSession
PipelineExecutorBean ..> PipelineRunner : new
PipelineExecutorBean --> DataManagerServiceBean : LOAD / SAVE
PipelineExecutorBean --> OperationRegistryBean : validate steps
@enduml
```

**Notas**
- Las operaciones con efectos secundarios de MerLabCore (`plot` abre ventanas; `save` escribe en disco) se marcan `sideEffects=true`. **No se exponen** por REST: `SAVE` lo gestiona StackFlow mediante `DataManagerServiceBean`, y los gráficos se devuelven como datos o HTML.
- Una expresión RPN completa (por ejemplo `"norm 8000 lp fft"`) se ejecuta con el `RPNParser` de MerLabCore. Los pipelines guardan sus pasos como lista estructurada (`PipelineStep`) para poder validarlos y versionarlos.
- Si para trading o audio falta alguna operación en MerLabCore (por ejemplo RSI o detección de picos), **la añade el usuario en MerLabCore** (Eclipse, fuera de este roadmap) o se aporta como plugin (grupo L). StackFlow nunca reimplementa algoritmos.

---

## 6. Archivos y datos (S3)

```mermaid
classDiagram
    direction LR
    class FileStorageServiceBean {
        <<EJB Stateless>>
        +store(InputStream, fileName, mediaType, owner) StoredFile
        +open(fileId) InputStream
        +list(filter) List~StoredFile~
        +delete(fileId)
    }
    class StoredFile {
        <<domain>>
        +String id
        +String fileName
        +String mediaType
        +long sizeBytes
        +String sha256
        +String origin
    }
    class DataManagerServiceBean {
        <<EJB Stateless>>
        +importFile(fileId, ImportFormat, options) DataRef
        +load(String ref) StackElement
        +save(StackElement, String target) String
        +export(DataRef, ExportFormat) StoredFile
    }
    class ImportFormat {
        <<enumeration>>
        WAV
        CSV
        OHLCV_CSV
        JSON
        HDF5
        IMAGE
    }
    class FileImporter {
        <<interface, pure Java>>
        +supports(ImportFormat) boolean
        +read(InputStream, options) StackElement
    }
    class WavImporter {
        <<pure Java, javax.sound.sampled>>
    }
    class CsvImporter {
        <<pure Java, uses MerLabCore CSVDataLoader via port>>
    }
    class OhlcvImporter {
        <<pure Java>>
    }
    class ImageImporter {
        <<pure Java, javax.imageio>>
    }
    class SignalsDaoBean {
        <<EJB DAO>>
    }
    class DatasetsDaoBean {
        <<EJB DAO>>
    }
    class StoredFilesDaoBean {
        <<EJB DAO>>
    }

    FileStorageServiceBean ..> StoredFile
    FileStorageServiceBean --> StoredFilesDaoBean
    DataManagerServiceBean --> FileStorageServiceBean
    DataManagerServiceBean o-- FileImporter
    DataManagerServiceBean --> SignalsDaoBean
    DataManagerServiceBean --> DatasetsDaoBean
    FileImporter <|.. WavImporter
    FileImporter <|.. CsvImporter
    FileImporter <|.. OhlcvImporter
    FileImporter <|.. ImageImporter
    DataManagerServiceBean ..> ImportFormat
```

```plantuml
@startuml r2-data
left to right direction
skinparam classAttributeIconSize 0
class FileStorageServiceBean <<@Stateless>> {
  +store(InputStream, fileName, mediaType, owner) : StoredFile
  +open(fileId) : InputStream
  +list(filter) : List<StoredFile>
  +delete(fileId)
}
class StoredFile <<domain>> {
  +id : String
  +fileName : String
  +mediaType : String
  +sizeBytes : long
  +sha256 : String
  +origin : String
}
class DataManagerServiceBean <<@Stateless>> {
  +importFile(fileId, ImportFormat, options) : DataRef
  +load(ref) : StackElement
  +save(StackElement, target) : String
  +export(DataRef, ExportFormat) : StoredFile
}
enum ImportFormat {
  WAV
  CSV
  OHLCV_CSV
  JSON
  HDF5
  IMAGE
}
interface FileImporter {
  +supports(ImportFormat) : boolean
  +read(InputStream, options) : StackElement
}
class WavImporter <<pure Java>>
class CsvImporter <<pure Java>>
class OhlcvImporter <<pure Java>>
class ImageImporter <<pure Java>>
class SignalsDaoBean <<DAO>>
class DatasetsDaoBean <<DAO>>
class StoredFilesDaoBean <<DAO>>

FileStorageServiceBean ..> StoredFile
FileStorageServiceBean --> StoredFilesDaoBean
DataManagerServiceBean --> FileStorageServiceBean
DataManagerServiceBean o-- FileImporter
DataManagerServiceBean --> SignalsDaoBean
DataManagerServiceBean --> DatasetsDaoBean
FileImporter <|.. WavImporter
FileImporter <|.. CsvImporter
FileImporter <|.. OhlcvImporter
FileImporter <|.. ImageImporter
DataManagerServiceBean ..> ImportFormat
@enduml
```

**Notas**
- Los binarios van a disco (`stackflow.storage.dir`, configurable) y sus metadatos a la tabla nueva `stored_files`. La BD nunca guarda archivos grandes.
- `WavImporter` usa solo el JDK (`javax.sound.sampled`) para convertir WAV en una señal MerLabCore (muestras + `sampleRate` en `meta`). Leer un archivo no es duplicar un algoritmo.
- `OhlcvImporter` convierte velas de trading (timestamp, open, high, low, close, volume) en un `DATASET` y en una señal por columna.
- `ImageImporter` (javax.imageio) convierte una imagen en matriz o vector. Qué operaciones de imagen existen depende de MerLabCore (spike).

---

## 7. Jobs: Orchestrator y Worker (v1)

Sustituye a `JobService`, `JobExecutor` y `JobHandlerRegistry` del diseño anterior. Los **estados y transiciones son los mismos** (DISENO_CLASES_REST §4.1).

```mermaid
classDiagram
    direction LR
    class OrchestratorServiceBean {
        <<EJB Stateless>>
        +scheduleJob(JobType, payload, user) JobId
        +triggerEvent(JobEvent)
        +runBatch(PipelineDefinition, BatchConfig) JobId
        +handleStream(streamId)
        +manageScenario(scenarioId) JobId
        +cancel(JobId) boolean
    }
    class JobQueueServiceBean {
        <<EJB Stateless>>
        +get(JobId) Job
        +list(JobQuery) List~Job~
        +events(JobId, after) List~JobEvent~
        +result(JobId) JobResult
    }
    class WorkerServiceBean {
        <<EJB Stateless>>
        +execute(JobId) Future~JobResult~
    }
    class JobHandler {
        <<interface>>
        +type() JobType
        +execute(payload, JobContext) JobResult
    }
    class ScheduleServiceBean {
        <<EJB Singleton Startup>>
        -TimerService timerService
        +create(cron, JobType, payload)
        +onTimeout(Timer)
    }
    class PostProcessServiceBean {
        <<EJB Stateless>>
        +onJobFinished(JobFinishedEvent)
    }
    class JobRecoveryBean {
        <<EJB Singleton Startup>>
        +recoverOrphans()
    }
    class JobQueueDaoBean {
        <<EJB DAO>>
    }
    class PipelineJobHandler
    class AlgorithmJobHandler
    class ScenarioJobHandler
    class DataImportJobHandler
    class PegasusQueryJobHandler
    class ModelForgeTrainJobHandler

    OrchestratorServiceBean --> JobQueueDaoBean
    OrchestratorServiceBean --> WorkerServiceBean : Asynchronous
    WorkerServiceBean o-- JobHandler
    JobQueueServiceBean --> JobQueueDaoBean
    ScheduleServiceBean --> OrchestratorServiceBean
    PostProcessServiceBean --> OrchestratorServiceBean
    JobRecoveryBean --> JobQueueDaoBean
    JobHandler <|.. PipelineJobHandler
    JobHandler <|.. AlgorithmJobHandler
    JobHandler <|.. ScenarioJobHandler
    JobHandler <|.. DataImportJobHandler
    JobHandler <|.. PegasusQueryJobHandler
    JobHandler <|.. ModelForgeTrainJobHandler
```

```plantuml
@startuml r2-jobs
left to right direction
skinparam classAttributeIconSize 0
class OrchestratorServiceBean <<@Stateless>> {
  +scheduleJob(JobType, payload, user) : JobId
  +triggerEvent(JobEvent)
  +runBatch(PipelineDefinition, BatchConfig) : JobId
  +handleStream(streamId)
  +manageScenario(scenarioId) : JobId
  +cancel(JobId) : boolean
}
class JobQueueServiceBean <<@Stateless>> {
  +get(JobId) : Job
  +list(JobQuery) : List<Job>
  +events(JobId, after) : List<JobEvent>
  +result(JobId) : JobResult
}
class WorkerServiceBean <<@Stateless>> {
  +execute(JobId) : Future<JobResult> <<@Asynchronous>>
}
interface JobHandler {
  +type() : JobType
  +execute(payload, JobContext) : JobResult
}
class ScheduleServiceBean <<@Singleton @Startup>> {
  -timerService : TimerService
  +create(cron, JobType, payload)
  +onTimeout(Timer) <<@Timeout>>
}
class PostProcessServiceBean <<@Stateless>> {
  +onJobFinished(@Observes JobFinishedEvent)
}
class JobRecoveryBean <<@Singleton @Startup>> {
  +recoverOrphans()
}
class JobQueueDaoBean <<DAO>>
class PipelineJobHandler
class AlgorithmJobHandler
class ScenarioJobHandler
class DataImportJobHandler
class PegasusQueryJobHandler
class ModelForgeTrainJobHandler

OrchestratorServiceBean --> JobQueueDaoBean
OrchestratorServiceBean --> WorkerServiceBean : @Asynchronous
WorkerServiceBean o-- JobHandler
JobQueueServiceBean --> JobQueueDaoBean
ScheduleServiceBean --> OrchestratorServiceBean
PostProcessServiceBean --> OrchestratorServiceBean
JobRecoveryBean --> JobQueueDaoBean
JobHandler <|.. PipelineJobHandler
JobHandler <|.. AlgorithmJobHandler
JobHandler <|.. ScenarioJobHandler
JobHandler <|.. DataImportJobHandler
JobHandler <|.. PegasusQueryJobHandler
JobHandler <|.. ModelForgeTrainJobHandler
@enduml
```

**Notas**
- `WorkerServiceBean.execute` es `@Asynchronous`: WildFly gestiona el pool de hilos. El "carril serie" del diseño anterior (para `ExampleRunner`, que captura `System.out`) se implementa con un `@Singleton` `@Lock(WRITE)` (`SerialExecutionBean`).
- Los schedules usan el **TimerService de EJB** (timers persistentes de WildFly), no un executor propio.

---

## 8. Motores opcionales (S5, S6, S7)

Mismo patrón para los tres: un port Java puro, un adaptador en un módulo con perfil Maven y un bean por defecto `Unavailable<Motor>` que responde **503 `ENGINE_NOT_INSTALLED`** (sustituye a `RAG_NOT_INSTALLED`; el detalle indica qué motor falta).

```mermaid
classDiagram
    direction LR
    class PegasusRagPort {
        <<interface, pure Java>>
        +query(RagQuery, JobContext) RagResult
        +useCases() List~UseCaseInfo~
        +conversations() List~ConversationInfo~
        +isInstalled() boolean
        +health() EngineHealth
    }
    class ModelForgePort {
        <<interface, pure Java>>
        +submitTraining(TrainingSpec) RemoteJobRef
        +trainingStatus(RemoteJobRef) RemoteJobStatus
        +downloadModel(RemoteJobRef) InputStream
        +isInstalled() boolean
        +health() EngineHealth
    }
    class MerlynixPort {
        <<interface, group N>>
        +parse(String program) MerlynixProgram
        +run(MerlynixProgram, StackSession) PipelineOutcome
    }
    class UnavailablePegasusRag {
        <<default bean>>
    }
    class PegasusRagAdapter {
        <<profile pegasus, Alternative>>
    }
    class UnavailableModelForge {
        <<default bean>>
    }
    class ModelForgeRemoteAdapter {
        <<profile modelforge, Alternative>>
        -URI baseUri
        -Client httpClient
    }
    class PegasusRagServiceBean {
        <<EJB Stateless>>
    }
    class ModelServiceBean {
        <<EJB Stateless>>
        +train(modelId, TrainingSpec) JobId
        +predict(modelId, inputs) Prediction
    }
    class MLCoreServiceBean {
        <<EJB Stateless>>
        +predictLocal(modelId, inputs) Prediction
    }

    PegasusRagPort <|.. UnavailablePegasusRag
    PegasusRagPort <|.. PegasusRagAdapter
    ModelForgePort <|.. UnavailableModelForge
    ModelForgePort <|.. ModelForgeRemoteAdapter
    PegasusRagServiceBean --> PegasusRagPort
    ModelServiceBean --> ModelForgePort : train (remote)
    ModelServiceBean --> MLCoreServiceBean : predict (local)
```

```plantuml
@startuml r2-engines
left to right direction
skinparam classAttributeIconSize 0
interface PegasusRagPort {
  +query(RagQuery, JobContext) : RagResult
  +useCases() : List<UseCaseInfo>
  +conversations() : List<ConversationInfo>
  +isInstalled() : boolean
  +health() : EngineHealth
}
interface ModelForgePort {
  +submitTraining(TrainingSpec) : RemoteJobRef
  +trainingStatus(RemoteJobRef) : RemoteJobStatus
  +downloadModel(RemoteJobRef) : InputStream
  +isInstalled() : boolean
  +health() : EngineHealth
}
interface MerlynixPort <<group N>> {
  +parse(program) : MerlynixProgram
  +run(MerlynixProgram, StackSession) : PipelineOutcome
}
class UnavailablePegasusRag <<default bean>>
class PegasusRagAdapter <<profile pegasus>> <<@Alternative>>
class UnavailableModelForge <<default bean>>
class ModelForgeRemoteAdapter <<profile modelforge>> <<@Alternative>> {
  -baseUri : URI
  -httpClient : Client
}
class PegasusRagServiceBean <<@Stateless>>
class ModelServiceBean <<@Stateless>> {
  +train(modelId, TrainingSpec) : JobId
  +predict(modelId, inputs) : Prediction
}
class MLCoreServiceBean <<@Stateless>> {
  +predictLocal(modelId, inputs) : Prediction
}
PegasusRagPort <|.. UnavailablePegasusRag
PegasusRagPort <|.. PegasusRagAdapter
ModelForgePort <|.. UnavailableModelForge
ModelForgePort <|.. ModelForgeRemoteAdapter
PegasusRagServiceBean --> PegasusRagPort
ModelServiceBean --> ModelForgePort : train (remote)
ModelServiceBean --> MLCoreServiceBean : predict (local)
@enduml
```

**Notas**
- **ModelForge:** su API todavía no existe. El contrato (`ModelForgePort`) se define en StackFlow y se ajusta cuando ModelForge publique su API (spike en el grupo H). Un entrenamiento es un job local de StackFlow (`MODELFORGE_TRAIN`) que solo envía la orden al Mac mini y consulta el estado; no consume recursos del PC.
- **Inferencia local:** solo la de los algoritmos clásicos de MerLabCore. Un modelo grande entrenado en ModelForge se usa por API remota, no en el PC.
- **PegasusRAG:** todo lo descrito en DISENO_CLASES_REST §3.6, §3.9, §3.10 y §4.6 sigue válido, renombrando según §11. El perfil Maven pasa a llamarse `pegasus`.

---

## 9. RAG separado: resultados al stack (S5)

PegasusRAG y MerLabCore **no se llaman entre sí**. El puente es el almacenamiento: un resultado de Pegasus se **exporta** a un archivo o dataset, y un pipeline de MerLabCore lo puede **cargar** con `LOAD`.

```mermaid
sequenceDiagram
    autonumber
    actor C as Client
    participant RR as PegasusRagResource
    participant PS as PegasusRagServiceBean
    participant P as PegasusRagPort
    participant DM as DataManagerServiceBean
    participant FS as FileStorageServiceBean
    participant PR as PipelineResource
    participant PE as PipelineExecutorBean

    C->>RR: POST /rag/query (or async job)
    RR->>PS: query(dto)
    PS->>P: query(ragQuery)
    P-->>PS: RagResult
    PS-->>C: 200 RagResponseDTO (jobId if async)

    C->>RR: POST /rag/runs/{jobId}/export (format JSON or CSV)
    RR->>PS: export(jobId, format)
    PS->>DM: export(result as DATASET)
    DM->>FS: store(file)
    FS-->>DM: StoredFile id
    DM-->>C: 201 dataset ref

    Note over C,PE: later, independent of RAG
    C->>PR: POST /pipelines/{id}/execute (step LOAD dataset ref)
    PR->>PE: execute(definition)
    PE->>DM: load(ref)
    DM-->>PE: StackElement DATASET
    PE-->>C: 202 jobId
```

```plantuml
@startuml r2-rag-export
autonumber
actor Client as C
participant PegasusRagResource as RR
participant PegasusRagServiceBean as PS
participant PegasusRagPort as P
participant DataManagerServiceBean as DM
participant FileStorageServiceBean as FS
participant PipelineResource as PR
participant PipelineExecutorBean as PE

C -> RR : POST /rag/query (or async job)
RR -> PS : query(dto)
PS -> P : query(ragQuery)
P --> PS : RagResult
PS --> C : 200 RagResponseDTO (jobId if async)

C -> RR : POST /rag/runs/{jobId}/export (JSON / CSV)
RR -> PS : export(jobId, format)
PS -> DM : export(result as DATASET)
DM -> FS : store(file)
FS --> DM : StoredFile id
DM --> C : 201 dataset ref

== later, independent of RAG ==
C -> PR : POST /pipelines/{id}/execute (step LOAD dataset ref)
PR -> PE : execute(definition)
PE -> DM : load(ref)
DM --> PE : StackElement DATASET
PE --> C : 202 jobId
@enduml
```

---

## 10. Primeras aplicaciones: audio y trading (S2)

### 10.1 Audio: `voice.wav → normalize → lowpass → FFT → picos → guardar`

```mermaid
sequenceDiagram
    autonumber
    actor C as Client
    participant FR as FileResource
    participant DM as DataManagerServiceBean
    participant PR as PipelineResource
    participant O as OrchestratorServiceBean
    participant W as WorkerServiceBean
    participant PE as PipelineExecutorBean
    participant MC as MerLabCorePort

    C->>FR: POST /files (voice.wav, multipart)
    FR-->>C: 201 fileId
    C->>FR: POST /signals/import (fileId, WAV)
    FR->>DM: importFile(fileId, WAV)
    DM-->>C: 201 signalId
    C->>PR: POST /pipelines/{id}/execute
    PR->>O: scheduleJob(PIPELINE, def)
    O-->>C: 202 jobId
    O-)W: execute(jobId)
    W->>PE: execute(def, ctx)
    PE->>DM: load(signal:signalId)
    loop steps norm, lp 8000, fft, peaks
        PE->>MC: applyRpn(token, session)
        PE-)C: job event (WebSocket)
    end
    PE->>DM: save(spectrum, dataset:spectrum)
    W-->>O: COMPLETED
    C->>PR: GET /jobs/{jobId}/result
```

```plantuml
@startuml r2-audio
autonumber
actor Client as C
participant FileResource as FR
participant DataManagerServiceBean as DM
participant PipelineResource as PR
participant OrchestratorServiceBean as O
participant WorkerServiceBean as W
participant PipelineExecutorBean as PE
participant MerLabCorePort as MC

C -> FR : POST /files (voice.wav, multipart)
FR --> C : 201 fileId
C -> FR : POST /signals/import (fileId, WAV)
FR -> DM : importFile(fileId, WAV)
DM --> C : 201 signalId
C -> PR : POST /pipelines/{id}/execute
PR -> O : scheduleJob(PIPELINE, def)
O --> C : 202 jobId
O ->> W : execute(jobId) <<@Asynchronous>>
W -> PE : execute(def, ctx)
PE -> DM : load(signal:signalId)
loop steps norm, lp 8000, fft, peaks
  PE -> MC : applyRpn(token, session)
  PE ->> C : job event (WebSocket)
end
PE -> DM : save(spectrum, dataset:spectrum)
W --> O : COMPLETED
C -> PR : GET /jobs/{jobId}/result
@enduml
```

### 10.2 Trading: `velas OHLCV → medias móviles → derivada → algoritmo ML → señal`

Misma secuencia que 10.1, con:
1. `POST /datasets/import` (`OHLCV_CSV`);
2. pasos `LOAD close`, `ma 20`, `deriv`, `ALGORITHM <id>` (por ejemplo, regresión o clasificación del catálogo de MerLabCore) y `SAVE signal:prediction`.

Los indicadores que falten (RSI, MACD…) se añaden en MerLabCore, fuera de este roadmap. Ver la nota de §5.

---

## 11. Traducción de nombres (diseño anterior → revisión 2)

| Antes (DISENO_*.md) | Ahora |
|---|---|
| `stackflow-api` | `stackflow-api-contract` |
| `stackflow-service` | `stackflow-domain`, `stackflow-ports`, `stackflow-jobs`, `stackflow-pipeline`, `stackflow-rpn`, `stackflow-registry`, `stackflow-runtime`, `stackflow-security`, `stackflow-application` |
| `stackflow-persistence` | `stackflow-storage` |
| `stackflow-web` | `stackflow-adapter-jakarta` + `stackflow-host-wildfly` |
| `stackflow-adapter-merlabrag`, perfil `rag` | `stackflow-adapter-pegasus`, perfil `pegasus` |
| Records `XRequest`/`XResponse` | `XDTO` (p. ej. `QueryRequest` → `RagRequestDTO`, `QueryResponse` → `RagResponseDTO`, `ErrorResponse` → `ErrorDTO`, `JobResponse` → `JobDTO`) |
| `JobService` | `OrchestratorServiceBean` (crear, cancelar) + `JobQueueServiceBean` (consultar) |
| `JobExecutor` | `WorkerServiceBean` (`@Asynchronous`) + `SerialExecutionBean` |
| `JobHandlerRegistry` | lista de `JobHandler` dentro de `WorkerServiceBean` |
| `JpaJobRepository` / `JobRepository` | `JobQueueDaoBean` / `JobQueueDaoLocal` |
| `MlEnginePort` / `MerLabCoreEngineAdapter` | `MerLabCorePort` / `MerLabCoreAdapter` |
| `StackOperationCatalog` + `AlgorithmCatalogReader` | `OperationRegistryBean` |
| `StackSessionService` | `RPNStackServiceBean` + `StackSession` |
| `MlService` | `MLCoreServiceBean` |
| `RagEnginePort` / `MerLabRagEngineAdapter` / `UnavailableRagEngine` | `PegasusRagPort` / `PegasusRagAdapter` / `UnavailablePegasusRag` |
| `RagService` | `PegasusRagServiceBean` |
| `RAG_NOT_INSTALLED` | `ENGINE_NOT_INSTALLED` (detalle: `engine=pegasus`) |
| `HealthService` | `MonitorServiceBean` (`health()`, métricas) |
| `ApiMapper` | un `XMapper` por área |
| `/scripts` (grupo L, Merlynix) | grupo N: `/merlynix/*` |
| Entrenamiento local (`ML_TRAIN`) | `MODELFORGE_TRAIN` (remoto) |

## 12. Endpoints nuevos o cambiados (sobre el Anexo A)

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/operations?q=&category=&source=` | registro unificado (S4) |
| GET | `/operations/{token}` | detalle de una operación |
| POST | `/operations/{token}/execute` | ejecutar una operación suelta sobre entradas en línea o refs (`signal:12`) |
| POST | `/stack/evaluate` | expresión RPN completa (`RPNParser` de MerLabCore) |
| POST | `/files` | subir cualquier archivo (multipart) |
| GET | `/files`, `/files/{id}`, `/files/{id}/content` | listar, metadatos, descargar |
| DELETE | `/files/{id}` | borrar |
| POST | `/signals/import` | `{fileId, format: WAV\|CSV\|JSON\|HDF5\|IMAGE}` → señal |
| POST | `/datasets/import` | `{fileId, format: CSV\|OHLCV_CSV\|JSON\|HDF5}` → dataset |
| GET | `/datasets/{id}/export?format=` | exportar a archivo |
| POST | `/pipelines/{id}/execute` | alias de `/pipelines/{id}/runs` (202 + job) |
| POST | `/rag/runs/{jobId}/export` | resultado de Pegasus → dataset/archivo (S5) |
| GET | `/modelforge/status` | estado del ModelForge remoto |
| POST | `/models/{id}/train` | job `MODELFORGE_TRAIN` (503 `ENGINE_NOT_INSTALLED` sin ModelForge) |
| — | `/scripts/*` | **eliminado**; pasa a `/merlynix/*` en el grupo N |
