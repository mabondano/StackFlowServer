# StackFlowServer v2: roadmap de implementación

Versión 2 (revisión 2 del diseño) · 15.10.2026 · Rama `v2` · NetBeans + WildFly 36 + MariaDB

Este roadmap describe la implementación completa de StackFlowServer v2, desde cero y separada del código v1. Está escrito para que un agente (Codex o Claude) implemente **un micropaso cada vez** y espere `ROGER` antes de seguir (ver [README.md](README.md)).

- **Estado:** [TRACKING.md](TRACKING.md)
- **Registro de lo hecho:** [UMSETZUNGSPLAN.md](UMSETZUNGSPLAN.md)
- **Diseño (en este orden de precedencia):**
  1. [design/DECISIONES.md](design/DECISIONES.md)
  2. [design/DISENO_REVISION_2.md](design/DISENO_REVISION_2.md)
  3. [design/DISENO_REST_UNIFICADO.md](design/DISENO_REST_UNIFICADO.md) y [design/DISENO_CLASES_REST.md](design/DISENO_CLASES_REST.md) (traducir nombres con Revisión 2 §11)

---

## 1. Convenciones (válidas para todos los pasos)

### 1.1 Entorno

| Elemento | Valor |
|---|---|
| Carpeta de trabajo | `C:\merLab\projects\StackFlowServer\stackflow` (rama `v2`) |
| Código v1 | rama `master` + tag `v1-legacy`. **No se toca ni se mezcla.** Se puede **leer** como referencia. |
| IDE | NetBeans (proyecto Maven multi-módulo) |
| Java / plataforma | Java 17 · Jakarta EE 10 (`jakarta.jakartaee-api` 10.0.0, `provided`) |
| Servidor | WildFly 36.0.1.Final (`C:\merLab\projects\StackFlowServer\wildfly-36.0.1.Final`), HTTP 8090 |
| URL base | `http://localhost:8090/stackflow/api/v1` |
| Base de datos | MariaDB `localhost:3307`, `stackflowdb` (`java:/jdbc/StackflowDS`); tests: `stackflowdb_test` (`java:/jdbc/StackflowTestDS`) |
| Archivos | carpeta `stackflow.storage.dir` (configurable), metadatos en `stored_files` |
| Recursos externos | LLM y ModelForge en los **Mac mini**, por HTTP (URLs en la configuración). El PC **no** entrena ni ejecuta LLMs. |
| Librerías propias | `merlab-core` (obligatoria); `merlab-rag` = PegasusRAG (perfil `pegasus`, opcional). **Nunca se modifican.** |
| Tests | JUnit 5 + Mockito (`*Test`), Arquillian + WildFly (`*IT`, perfil `it`), ArchUnit |

### 1.2 Estilo de código (DA-001, opción C)

- **EJB** para todo lo que toca BD, seguridad, transacciones, hilos, timers o mensajes: `XServiceLocal` + `XServiceBean`, `XDaoLocal` + `XDaoBean`.
- **Java puro** para lo que solo calcula: dominio, `StackSession`, `StackElement`, `PipelineRunner`, importadores, comparadores, ventanas. Los EJB lo crean con `new`.
- Nombres según Revisión 2 §4 (`*DTO`, `*ServiceBean`, `*DaoBean`, `*Resource`, `*Mapper`, `*Port`, `*Adapter`, `Unavailable*`).
- Los módulos de Revisión 2 §2 se crean **en el primer paso que los necesita**.
- Motores opcionales (Pegasus, ModelForge): port + adaptador con perfil Maven + bean `Unavailable*` por defecto → 503 `ENGINE_NOT_INSTALLED`.

### 1.3 Reglas

1. **Un micropaso = un cambio pequeño y verificable.**
2. **Verificación mínima:** `mvn -q -P!pegasus,!modelforge clean verify` en verde. Desde J2, también `-Ppegasus`; desde H3, también `-Pmodelforge`. Los pasos con IT: `mvn -Pit verify` con WildFly local.
3. Cada clase con lógica lleva su test en el mismo paso: Java puro → JUnit; EJB → JUnit + Mockito (`@Mock` DAOs/ports, `@InjectMocks` bean).
4. **Prohibido:** modificar MerLabCore o PegasusRAG (ni sus miniservers); reimplementar algoritmos que ya existen en MerLabCore; tocar `master` o `v1-legacy`; copiar código de `genai-java-rest-api`; secretos en git; push sin aprobación.
5. Si el roadmap y el diseño no coinciden, manda el diseño (en el orden de precedencia). Lo que falte se anota como "Decisión" y se pregunta.
6. 👤 = requiere al usuario (el agente prepara todo y espera). **Spike** = solo nota de decisión, sin código de producción.

### 1.4 Orden de los grupos

| Orden | Grupo | Tema | Módulos principales |
|---|---|---|---|
| 1 | **0** | Fundamentos | parent, host-wildfly, guardarraíles |
| 2 | **A** | Contrato de la API (DTOs) | api-contract |
| 3 | **B** | Infraestructura web | adapter-jakarta, host-wildfly, runtime |
| 4 | **C** | Storage, seguridad y administración | storage, security, application |
| 5 | **D** | Jobs: Orchestrator y Worker | jobs, runtime |
| 6 | **E** | MerLabCore, motor RPN y registry | ports, adapter-merlabcore, rpn, registry |
| 7 | **F** | Archivos y datos (WAV, imágenes, CSV, OHLCV…) | storage |
| 8 | **G** | Pipelines, batch, escenarios y archivo | pipeline |
| 9 | **U** | Primeras aplicaciones: audio y trading | (usa todo lo anterior) |
| 10 | **I** | Streaming y alertas (trading en tiempo real) | pipeline, adapter-jakarta |
| 11 | **H** | Modelos: inferencia local y ModelForge remoto | application, adapter-modelforge |
| 12 | **J** | PegasusRAG (opcional y separado) | application, adapter-pegasus |
| 13 | **K** | Observabilidad y operación | runtime |
| 14 | **L** | Plugins (PluginManager) | registry |
| 15 | **M** | Cierre y release 2.0 | — |
| 16 | **N** | Merlynix (después de 2.0) | adapter-merlynix |

Formato de cada milestone: **Objetivo**, **Depende de**, **Diseño**, tabla de micropasos (**ID · Micropaso · Clases / archivos · Verificación**) y **DoD**. En la columna Diseño, "R2" es DISENO_REVISION_2, "RU" es DISENO_REST_UNIFICADO y "CL" es DISENO_CLASES_REST.

---

## Grupo 0 · Fundamentos

### 0.1 · Rama v2 y retirada del código v1
**Objetivo:** trabajar limpio sin perder el v1. **Depende de:** —. **Diseño:** —

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.1.1 | 👤 Crear el tag `v1-legacy` sobre el último commit de `master` (`53ef018`). Push del tag solo con aprobación. | git | `git tag -l v1-legacy` |
| 0.1.2 | Crear la rama `v2` desde `master` y cambiar a ella en la carpeta `stackflow`. | git | `git branch --show-current` → `v2` |
| 0.1.3 | En `v2`: mover la documentación v1 a `docs/v1/` (`API.md`, `DATABASE_SCHEMA.md`, `SETUP_GUIDE.md`, `Setup Guide.docx`, `sqlscript_stackflowdb.sql`, `postman/`). Borrar `src/`, `class_list.txt` y el `pom.xml` v1. | `docs/v1/` | no queda `src/` en la raíz |
| 0.1.4 | `.gitignore` (`target/`, `nbproject/private/`, `*.class`, `*.log`) y `git rm -r --cached target`. | `.gitignore` | `git ls-files target` vacío |
| 0.1.5 | README v2 provisional (qué es, cómo compilar, enlace a `docs/v2/`). | `README.md` | — |

**DoD:** `v2` sin código v1; v1 recuperable.

### 0.2 · Proyecto Maven multi-módulo
**Objetivo:** esqueleto que compila y despliega. **Depende de:** 0.1. **Diseño:** R2 §2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.2.1 | POM padre `com.merlab:stackflow-parent:2.0.0-SNAPSHOT`: Java 17, UTF-8, Jakarta 10, `dependencyManagement` (jakartaee-api, junit-bom, mockito, archunit), `pluginManagement` (compiler, surefire, failsafe, war, enforcer). | `pom.xml` | `mvn -q validate` |
| 0.2.2 | Módulos iniciales: `stackflow-domain`, `stackflow-ports` (Java puro) y `stackflow-api-contract`. | 3 POMs + `package-info.java` | compila |
| 0.2.3 | Módulo `stackflow-adapter-jakarta` (jar, `META-INF/beans.xml`). | POM | compila |
| 0.2.4 | Módulo `stackflow-host-wildfly` (war, `finalName` `stackflow`, `jboss-web.xml` con context root `/stackflow`, `WEB-INF/beans.xml`, `index.html`). | POM + `WEB-INF` | `target/stackflow.war` |
| 0.2.5 | Perfiles `pegasus` y `modelforge` declarados en el padre (vacíos de momento). `pegasus` se activa por fichero si existe el JAR de merlab-rag en `~/.m2`. | `pom.xml` | `mvn -P!pegasus,!modelforge verify` |
| 0.2.6 | Regla documentada en el POM padre: los demás módulos (rpn, registry, runtime, jobs, pipeline, storage, security, application, adapters) se añaden en su primer paso. | comentario en `pom.xml` | — |

**DoD:** padre + 5 módulos compilan.

### 0.3 · NetBeans y WildFly
**Objetivo:** abrir, desplegar y ver la app. **Depende de:** 0.2. **Diseño:** R2 §3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.3.1 | 👤 Abrir `stackflow` (rama `v2`) en NetBeans como proyecto padre; `nb-configuration.xml` en `host-wildfly` con el hint de WildFly. | `nb-configuration.xml` | NetBeans muestra los módulos |
| 0.3.2 | 👤 Registrar WildFly 36 en NetBeans (8090) y decidir `standalone.xml` o `standalone-microprofile.xml` (se recomienda microprofile por OpenAPI y métricas). | Umsetzungsplan | servidor arranca |
| 0.3.3 | 👤 DataSources `StackflowDS` y `StackflowTestDS` con un script CLI versionado. | `scripts/wildfly/datasources.cli` | `test-connection-in-pool` OK |
| 0.3.4 | Desplegar el WAR vacío. | — | `GET /stackflow/` → 200 |
| 0.3.5 | `wildfly-maven-plugin` + `scripts/build.ps1` y `scripts/deploy.ps1`. | POM, `scripts/` | despliegue por script |

**DoD:** despliegue desde NetBeans y desde Maven.

### 0.4 · Guardarraíles
**Objetivo:** que la arquitectura y DA-001 no se rompan. **Depende de:** 0.2. **Diseño:** DECISIONES DA-001; R2 §2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.4.1 | Módulo de test `stackflow-architecture-tests` con ArchUnit: `domain`/`ports` sin `jakarta.*`; solo `adapter-merlabcore` importa `com.merlab.signals..`; solo `adapter-pegasus` importa `pegasus..`; nadie depende de `adapter-jakarta` salvo `host-wildfly`. | `ArchitectureTest` | verde |
| 0.4.2 | Convención `*Test` (surefire) / `*IT` (failsafe, perfil `it`). | POMs | — |
| 0.4.3 | Base Arquillian (WildFly managed, `jbossHome` por propiedad) + `AbstractStackflowIT` + `IndexIT`. | `host-wildfly/src/test` | `mvn -Pit verify` |
| 0.4.4 | `.editorconfig` y cabecera de licencia Apache 2.0. | raíz | — |

**DoD:** reglas activas y un IT de humo.

### 0.5 · Documentación de trabajo
**Objetivo:** que cualquier agente sepa qué hacer. **Depende de:** —. **Diseño:** —

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.5.1 | `docs/v2/` con README, ROADMAP, TRACKING, UMSETZUNGSPLAN y `design/`. | `docs/v2/` | existen |
| 0.5.2 | `AGENTS.md` y `CLAUDE.md` en la raíz, con el protocolo ROGER. | raíz | existen |
| 0.5.3 | 👤 Primer commit de la documentación. | git | `git log` |
| 0.5.4 | Registro de decisiones (DA-001), notas para Spring y Revisión 2 del diseño y del roadmap. | `design/DECISIONES.md`, `FUTURO_SPRING.md`, `DISENO_REVISION_2.md`, `ROADMAP.md` | commit |

**DoD:** documentación v2 versionada y alineada con las notas de Obsidian.

---

## Grupo A · Contrato de la API (DTOs)

Todos los DTOs son `record`s `<Nombre>DTO` en `stackflow-api-contract`, con Bean Validation y sin lógica. Las fechas viajan como `String` ISO-8601 y los enums como `String`. Cada milestone incluye tests de ida y vuelta JSON y de validación.

### A0 · Soporte de pruebas
**Objetivo:** probar DTOs sin servidor. **Depende de:** 0.2. **Diseño:** CL §3.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A0.1 | Dependencias de test: jackson-databind (+ records), hibernate-validator + expressly. | POM | compila |
| A0.2 | `JsonTestSupport` y `ValidationTestSupport`. | `src/test/.../support` | test de ejemplo |

**DoD:** un DTO se prueba en una línea.

### A1 · Comunes
**Objetivo:** errores, salud, info, paginación y rutas. **Depende de:** A0. **Diseño:** CL §3.8; RU §6; R2 §8

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A1.1 | `ErrorDTO(code, status, message, correlationId, details)` y `ErrorDetailDTO`. | `contract.common` | round-trip |
| A1.2 | `HealthDTO(status, components)` y `ComponentHealthDTO` (estados UP, DEGRADED, DOWN, NOT_INSTALLED). | `contract.common` | round-trip |
| A1.3 | `InfoDTO(version, buildTime, engines, counts)`. | `contract.common` | round-trip |
| A1.4 | `PageDTO<T>(items, total, offset, limit)` + límites (1–500). | `contract.common` | validación |
| A1.5 | `ApiPaths`: todas las rutas (RU §5, Anexo A.3, R2 §12). | `contract.common` | compila |

### A2 · Seguridad y administración
**Objetivo:** contrato del grupo C. **Depende de:** A1. **Diseño:** RU Anexo A.3, A.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A2.1 | `LoginDTO`, `TokenDTO(token, expiresAt, roles)`, `MeDTO`. | `contract.auth` | validación |
| A2.2 | `ApiKeyDTO`, `ApiKeyCreateDTO`, `ApiKeyCreatedDTO(+secret)`. | `contract.auth` | round-trip |
| A2.3 | `UserDTO` (sin contraseña), `UserCreateDTO` (contraseña ≥ 10), `UserUpdateDTO`, `RoleAssignmentDTO`. | `contract.user` | validación |
| A2.4 | `UserSettingDTO`. | `contract.user` | round-trip |
| A2.5 | `ProjectDTO`, `ProjectMemberDTO`. | `contract.project` | validación |
| A2.6 | `ConfigEntryDTO`, `HistoryDTO`. | `contract.admin` | round-trip |

### A3 · Jobs
**Objetivo:** contrato del grupo D. **Depende de:** A1. **Diseño:** CL §3.2; R2 §7

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A3.1 | `CreateJobDTO(type, payload)` con validación del tipo. | `contract.job` | validación |
| A3.2 | `JobDTO` y `PageDTO<JobDTO>`. | `contract.job` | round-trip |
| A3.3 | `JobEventsDTO(jobId, status, nextSequence, events)` y `JobEventDTO`. | `contract.job` | round-trip |
| A3.4 | `JobResultDTO(jobId, status, result, completedAt)` (result polimórfico). | `contract.job` | round-trip |
| A3.5 | `ScheduleDTO(name, cron, jobType, payload, enabled, nextFireAt)`. | `contract.job` | validación |
| A3.6 | `PostProcessTaskDTO(jobType, payload, onStatus)`. | `contract.job` | round-trip |

### A4 · Operaciones, stack RPN y ML
**Objetivo:** contrato del grupo E. **Depende de:** A1. **Diseño:** R2 §5, §12

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A4.1 | `OperationDTO(token, name, category, arity, description, example, source)`. | `contract.operation` | round-trip |
| A4.2 | `OperationExecuteDTO(inputs[StackElementDTO], params)` y `OperationResultDTO(outputs)`. | `contract.operation` | validación |
| A4.3 | `StackElementDTO(type, ref, value, meta)` (`ref` tipo `signal:12`; `value` en línea para escalares y vectores pequeños). | `contract.stack` | validación (ref xor value) |
| A4.4 | `RpnEvaluateDTO(expression, inputs)` y `RpnResultDTO(stack)`. | `contract.stack` | round-trip |
| A4.5 | `StackSessionDTO(id, depth, top, expiresAt)`, `StackPushDTO`, `StackApplyDTO(token, params)`, `StackSnapshotDTO`. | `contract.stack` | validación |
| A4.6 | `AlgorithmDTO` (campos de `AlgorithmDescriptor`), `AlgorithmRunDTO(params, mode)` y `AlgorithmResultDTO(success, consoleOutput, chartHtml, metrics)`. | `contract.ml` | round-trip |

### A5 · Archivos y datos
**Objetivo:** contrato del grupo F. **Depende de:** A1. **Diseño:** R2 §6, §12

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A5.1 | `StoredFileDTO(id, fileName, mediaType, sizeBytes, sha256, createdAt, origin)`. | `contract.file` | round-trip |
| A5.2 | `ImportDTO(fileId, format, options)` con formatos WAV, CSV, OHLCV_CSV, JSON, HDF5 e IMAGE. | `contract.data` | validación |
| A5.3 | `SignalDTO(id, name, projectId, unit, sampleRate, length, meta)` y `SignalDataPageDTO`. | `contract.data` | round-trip |
| A5.4 | `SignalGroupDTO`, `DatasetDTO(id, name, columns, rows, format, origin)`. | `contract.data` | round-trip |
| A5.5 | `DbConnectionDTO` (password solo de escritura), `DbConnectionTestDTO`, `DataImportQueryDTO`. | `contract.data` | `password` nunca en la respuesta |

### A6 · Pipelines, escenarios y archivo
**Objetivo:** contrato del grupo G. **Depende de:** A4. **Diseño:** R2 §5; RU Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A6.1 | `PipelineStepDTO(kind: LOAD\|RPN\|ALGORITHM\|SAVE, operation, params)` y `PipelineDTO(id, name, projectId, steps, version)`. | `contract.pipeline` | validación (≥ 1 paso, LOAD primero) |
| A6.2 | `PipelineVersionDTO`, `CurrentVersionDTO`, `BatchConfigDTO(chunkSize, parallelism)`. | `contract.pipeline` | round-trip |
| A6.3 | `ScenarioDTO(…, stackSnapshot, parameters, golden)`, `ScenarioEntityDTO`, `ScenarioRunDTO`. | `contract.scenario` | round-trip |
| A6.4 | `ScenarioCompareDTO(runA, runB, equal, differences, tolerance)`. | `contract.scenario` | round-trip |
| A6.5 | `ArchiveDTO`, `RollbackDTO`. | `contract.archive` | round-trip |

### A7 · Streaming y alertas
**Objetivo:** contrato del grupo I. **Depende de:** A1. **Diseño:** RU Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A7.1 | `WindowSpecDTO(type, unit, size, slide)`. | `contract.stream` | validación |
| A7.2 | `StreamDTO(name, sourceType, window, pipelineId, retention, state)`. | `contract.stream` | validación |
| A7.3 | `StreamEventBatchDTO(points[{timestamp, values}])` y `StreamResultDTO`. | `contract.stream` | límite de lote |
| A7.4 | `AlertRuleDTO`, `AlertDTO`. | `contract.alert` | round-trip |

### A8 · Modelos y ModelForge
**Objetivo:** contrato del grupo H. **Depende de:** A1. **Diseño:** R2 §8

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A8.1 | `ModelDTO(id, name, origin: MERLABCORE\|MODELFORGE, algorithm, status, metrics)` y `ModelFileDTO`. | `contract.model` | round-trip |
| A8.2 | `TrainRequestDTO(datasetRef, algorithm, params)`. | `contract.model` | validación |
| A8.3 | `PredictRequestDTO(inputs)` y `PredictResultDTO(outputs, modelVersion, latencyMs)`. | `contract.model` | round-trip |
| A8.4 | `ModelForgeStatusDTO(installed, reachable, version, queue)`. | `contract.model` | round-trip |

### A9 · PegasusRAG
**Objetivo:** contrato del grupo J (forma de referencia de la empresa, código propio). **Depende de:** A1. **Diseño:** CL §3.2, §3.9; R2 §9

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A9.1 | `RagRequestDTO(useCaseId, sessionId, userInput, orchestrationMode)`. | `contract.rag` | validación |
| A9.2 | `RagResponseDTO` + `RagArtifactDTO`, `RagErrorDTO`, `RagTraceDTO`, `RagTraceEventDTO`, `RagAuditDTO`. | `contract.rag` | round-trip |
| A9.3 | `UseCaseDTO`, `FlowListDTO`. | `contract.rag` | round-trip |
| A9.4 | `ConversationDTO`, `ConversationMessageDTO` (lista y mensajes). | `contract.rag` | round-trip |
| A9.5 | `RunLogDTO`, `RunLogEntryDTO`, `RunLogAnalysisDTO`. | `contract.rag` | round-trip |
| A9.6 | `RagIndexingDTO`, `RagExportDTO(format: JSON\|CSV, target)`. | `contract.rag` | validación |

### A10 · Administración y plugins
**Objetivo:** contrato de los grupos K y L. **Depende de:** A1. **Diseño:** RU Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A10.1 | `ComponentDTO` (lista de componentes) y `MetricsDTO`. | `contract.admin` | round-trip |
| A10.2 | `PluginDTO`, `PluginRegisterDTO`. | `contract.plugin` | validación |

**DoD del grupo A:** `stackflow-api-contract` completo (salvo Merlynix, grupo N).

---

## Grupo B · Infraestructura web

### B1 · Aplicación JAX-RS
**Objetivo:** arrancar el REST. **Depende de:** A1, 0.3. **Diseño:** CL §3.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B1.1 | `StackflowApplication` (`@ApplicationPath("api/v1")`, una sola) en `host-wildfly`. | `host.StackflowApplication` | despliega |
| B1.2 | `JacksonConfig` (`ContextResolver<ObjectMapper>`): records, `NON_NULL`. | `adapter-jakarta` | unit test |

### B2 · Modelo de errores
**Objetivo:** todo error sale como `ErrorDTO`. **Depende de:** B1. **Diseño:** CL §3.8, §7.2; R2 §8

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B2.1 | `ErrorCode` (con estado HTTP; incluye `ENGINE_NOT_INSTALLED`, `CAPABILITY_NOT_SUPPORTED`, `ALGORITHM_NOT_EXECUTABLE`, `OPERATION_NOT_ALLOWED`). | `stackflow-domain` | unit test |
| B2.2 | Excepciones `StackflowException` y subclases (NotFound, Conflict, Validation, JobNotFinished, EngineNotInstalled, EngineUnavailable, UpstreamTimeout, UnprocessableResult). | `stackflow-domain` | unit tests |
| B2.3 | `ErrorDTOFactory` (Java puro, política de detalle dev/prod). | `adapter-jakarta` | unit test |
| B2.4 | `StackflowExceptionMapper`, `ConstraintViolationExceptionMapper`, `WebApplicationExceptionMapper`. | `adapter-jakarta` | unit tests |

### B3 · Filtros
**Objetivo:** correlación, logging y CORS. **Depende de:** B2. **Diseño:** CL §4.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B3.1 | `CorrelationIdFilter` (header, MDC, respuesta). | `adapter-jakarta` | unit test |
| B3.2 | `RequestLoggingFilter` (sin cuerpos ni secretos). | `adapter-jakarta` | log |
| B3.3 | `CorsFilter` (FlowScope dev `http://localhost:5173` por defecto). | `adapter-jakarta` | OPTIONS → 204 |

### B4 · Health, info y OpenAPI
**Objetivo:** endpoints comunes. **Depende de:** B2. **Diseño:** CL §5.5; R2 §11 (`MonitorServiceBean`)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B4.1 | Crear `stackflow-runtime`: `EngineHealth`, `HealthState` (en domain) y `MonitorServiceLocal`/`MonitorServiceBean.health()` (agregación; NOT_INSTALLED no es fallo). | `runtime` | unit tests |
| B4.2 | `HealthResource` (`/health/live`, `/health/ready`) e `InfoResource` (`/info`). | `adapter-jakarta` | `curl` 200 |
| B4.3 | **Spike:** MicroProfile OpenAPI de WildFly frente a swagger-core. | Umsetzungsplan | decisión |
| B4.4 | Aplicar la decisión: anotaciones OpenAPI y Swagger UI. | `adapter-jakarta`, `api-contract` | `/openapi` visible |

### B5 · ITs base
**Objetivo:** proteger lo común. **Depende de:** B4, 0.4.3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B5.1 | `HealthIT`. | `*IT` | verde |
| B5.2 | `ErrorFormatIT` (404 y JSON inválido → `ErrorDTO` con `correlationId`). | `*IT` | verde |

**DoD del grupo B:** base web probada.

---

## Grupo C · Storage, seguridad y administración

### C1 · Base de datos y migraciones
**Objetivo:** esquema versionado. **Depende de:** 0.3.3, B4. **Diseño:** RU Anexo A.2; CL §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C1.1 | Crear `stackflow-storage`; Flyway ejecutado por `MigrationStartupBean` (`@Singleton @Startup`). | `storage` | arranca |
| C1.2 | `V001__baseline.sql` desde `docs/v1/sqlscript_stackflowdb.sql` (InnoDB, utf8mb4, FKs, índices; `baselineOnMigrate`). | `db/migration` | 27 tablas |
| C1.3 | `V002__jobs.sql` (`job_queue` ampliada + `job_events`). | migración | `DESCRIBE job_events` |
| C1.4 | `V003__security.sql` (`api_keys` + roles ADMIN, ANALYST, VIEWER, AUDITOR). | migración | roles |
| C1.5 | `V004__files.sql` (`stored_files`). | migración | tabla |
| C1.6 | `persistence.xml` (`StackflowPU`) + `DatabaseHealthBean` en `MonitorServiceBean`. | `storage` | `/health/ready` muestra la BD |
| C1.7 | 👤 Admin inicial desde `STACKFLOW_ADMIN_PASSWORD` (`AdminBootstrapBean`). | `storage` | login tras C3 |

### C2 · Usuarios y roles
**Objetivo:** CRUD de usuarios. **Depende de:** C1, A2. **Diseño:** RU Anexo A.3, A.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C2.1 | Entidades `Users`, `Roles`, `UserRoles`; `UserDaoLocal`/`UserDaoBean`, `RoleDaoLocal`/`RoleDaoBean`. | `storage` | IT de DAO |
| C2.2 | Crear `stackflow-security`; `UserServiceLocal`/`UserServiceBean` (BCrypt; 409 por duplicados). Se puede leer `UserServiceBean` v1 como referencia. | `security` | JUnit + Mockito |
| C2.3 | `UserMapper` + `UserResource` (`/users` CRUD, `/users/{id}/roles`). | `adapter-jakarta` | IT |

### C3 · Autenticación
**Objetivo:** JWT + API key. **Depende de:** C2. **Diseño:** CL §4.5; RU Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C3.1 | `JwtServiceBean` (HS256, secreto y caducidad de la config). | `security` | unit tests |
| C3.2 | `AuthResource` (`POST /auth/login`, `GET /auth/me`). | `adapter-jakarta` | IT |
| C3.3 | `AuthFilter` (Bearer o `X-API-Key` → `SecurityContext`); rutas públicas: health, login, openapi. | `adapter-jakarta` | 401 |
| C3.4 | `@RolesAllowed` activo y aplicado a los resources existentes. | `web.xml`, resources | 403 |
| C3.5 | API keys: entidad `ApiKeys`, DAO, `ApiKeyServiceBean` (hash SHA-256) y `ApiKeyResource`. | `storage`, `security`, `adapter-jakarta` | IT |

### C4 · Ajustes y proyectos
**Objetivo:** `user_settings`, `projects`, `project_users`. **Depende de:** C3. **Diseño:** RU Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C4.1 | Ajustes: entidad, DAO, `UserSettingServiceBean`, `/users/me/settings`. | varios | IT |
| C4.2 | Crear `stackflow-application`; proyectos: entidad, DAO, `ProjectServiceBean`, `ProjectResource`. | varios | IT |
| C4.3 | Miembros `/projects/{id}/members`. | `ProjectResource` | IT |
| C4.4 | `ProjectAccessPolicy` (Java puro) reutilizada por F, G y H. | `application` | unit tests |

### C5 · Configuración e historial
**Objetivo:** `ConfigProvider` (v1) y `history`. **Depende de:** C3. **Diseño:** RU §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C5.1 | `ConfigProvider` (env/sistema > tabla `config` > defaults) + `ConfigKeys` + `ConfigServiceBean` con caché y reload. | `application` | unit tests |
| C5.2 | `ConfigResource` (`/admin/config`, claves secretas ocultas). | `adapter-jakarta` | IT |
| C5.3 | Historial: evento CDI `EntityChangedEvent` → `HistoryServiceBean` (`@Observes`) → tabla `history`. | `application` | unit test |
| C5.4 | `HistoryResource` (`/history`, AUDITOR). | `adapter-jakarta` | IT |

**DoD del grupo C:** BD, usuarios, seguridad, proyectos, config e historial.

---

## Grupo D · Jobs: Orchestrator y Worker

### D1 · Dominio de jobs
**Objetivo:** modelo y estados. **Depende de:** B2. **Diseño:** CL §4.1; R2 §7

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D1.1 | `JobId`, `JobType` (PIPELINE, ALGORITHM, EXAMPLE, SCENARIO, SCENARIO_REGRESSION, DATA_IMPORT, POST_PROCESS, PEGASUS_QUERY, PEGASUS_SCENARIO, PEGASUS_INDEXING, MODELFORGE_TRAIN, PREDICT, TEST_ECHO) y `JobStatus.canTransitionTo` (tabla CL §4.1). | `domain` | unit test de toda la tabla |
| D1.2 | `Job`, `JobEvent`, `JobResult`, `JobQuery`, `JobContext` (interfaz). | `domain` | unit tests |

### D2 · Persistencia
**Objetivo:** `job_queue` + `job_events`. **Depende de:** D1, C1.3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D2.1 | Entidades `JobQueue` (`@Version`, `event_seq`) y `JobEvents`. | `storage` | compila |
| D2.2 | `JobQueueDaoLocal`/`JobQueueDaoBean` (transiciones válidas, `appendEvent` atómico, `findByStatus`). | `storage` | IT |

### D3 · Ejecución
**Objetivo:** Orchestrator + Worker. **Depende de:** D2. **Diseño:** R2 §7; CL §5.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D3.1 | Crear `stackflow-jobs`; `JobHandler` + `DefaultJobContext` (Java puro). | `jobs` | unit test |
| D3.2 | `WorkerServiceBean.execute` (`@Asynchronous`): elige el handler, gestiona los estados y los eventos. | `jobs` | JUnit + Mockito |
| D3.3 | `SerialExecutionBean` (`@Singleton @Lock(WRITE)`) para el trabajo no paralelizable. | `jobs` | unit test |
| D3.4 | `OrchestratorServiceBean`: `scheduleJob`, `cancel` (CANCELLING), `triggerEvent`. | `jobs` | unit tests |
| D3.5 | Timeouts y reintentos por tipo (config). | `jobs` | unit tests |
| D3.6 | `JobRecoveryBean` (`@Startup`): huérfanos → FAILED `SERVER_RESTART`. | `jobs` | IT |
| D3.7 | `TestEchoJobHandler` (solo con `stackflow.jobs.testEcho=true`). | `jobs` | unit test |

### D4 · REST de jobs
**Objetivo:** `/jobs…`. **Depende de:** D3, A3, C3. **Diseño:** RU §5.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D4.1 | `JobQueueServiceBean` (get, list, events, result 409) con control de acceso. | `jobs` | unit tests |
| D4.2 | `JobMapper` + `JobResource` (POST 202, GET, events, result, DELETE). | `adapter-jakarta` | IT con TEST_ECHO |

### D5 · Eventos en tiempo real
**Objetivo:** `/ws/jobs/{id}`. **Depende de:** D4.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D5.1 | `JobEventBus` (Java puro, suscriptores) + `JobSocketEndpoint` (token en el handshake). | `jobs`, `adapter-jakarta` | IT WS |

### D6 · Post-proceso y schedules
**Objetivo:** `post_process_tasks` y `schedules` (scheduleJob v1). **Depende de:** D4, A3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D6.1 | Post-proceso: entidad, DAO, `PostProcessServiceBean` (`@Observes JobFinishedEvent`) y `/jobs/{id}/post-process-tasks`. | varios | IT |
| D6.2 | Schedules: entidad, DAO y validación de cron (`cron-utils`). | `storage`, `jobs` | unit test |
| D6.3 | `ScheduleServiceBean` con **TimerService** (timers programáticos persistentes), sin solapamientos. | `jobs` | IT |
| D6.4 | `ScheduleResource` (CRUD, pause, resume). | `adapter-jakarta` | IT |

**DoD del grupo D:** jobs completos, programables y observables.

---

## Grupo E · MerLabCore, motor RPN y registry

### E1 · Integración de MerLabCore
**Objetivo:** la librería dentro del WAR, sin modificarla. **Depende de:** B4, D3. **Diseño:** R2 §5; RU §8.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E1.1 | 👤 `mvn install` de MerLabCore (sin cambios) y anotar la versión. | — | jar en `~/.m2` |
| E1.2 | Crear `stackflow-adapter-merlabcore`; dependencia con exclusiones (JavaFX, XChart, waffle-jna, driver MariaDB) y prueba de despliegue. | POM | despliega |
| E1.3 | **Spike:** inventario de `RPNEngine`/`RPNOperationProfiles` (tokens, aridad, categorías; cuáles tienen efectos secundarios: `plot`, `save`) y de los tipos de objeto del `RPNStack` (Signal, Complex[], DataSet, Double…). | Umsetzungsplan | tabla |
| E1.4 | `StackElement`, `ElementType`, `OperationDescriptor`, `OperationSource` (domain) y `MerLabCorePort` (ports). | `domain`, `ports` | compila |
| E1.5 | `MerLabCoreAdapter` (Java puro) + producer CDI + `health()`; integrar en `MonitorServiceBean`. | `adapter-merlabcore` | `/health/ready` muestra merlabcore |

### E2 · Motor RPN y sesiones
**Objetivo:** stack con estado y expresiones. **Depende de:** E1. **Diseño:** R2 §5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E2.1 | Crear `stackflow-rpn`; `StackElementCodec` (StackElement ↔ objetos de MerLabCore, en el adaptador) con tests de ida y vuelta por tipo. | `rpn`, `adapter-merlabcore` | unit tests |
| E2.2 | `StackSession` (Java puro): push, pop, peek, snapshot, profundidad máxima. | `rpn` | unit tests |
| E2.3 | `MerLabCoreAdapter.applyRpn` (vía `RPNEngine`, bloqueando operaciones con efectos secundarios). | `adapter-merlabcore` | unit tests con ops reales |
| E2.4 | `RPNStackServiceBean`: sesiones en memoria con TTL y límite por usuario; `evaluate(expression)` con `RPNParser`. | `rpn` | JUnit + Mockito |
| E2.5 | `StackResource`: `/stack/evaluate` y `/stack/sessions…` (crear, push, pop, apply, snapshot, borrar). | `adapter-jakarta` | IT |

### E3 · Registry de operaciones
**Objetivo:** `stackflow-registry` (S4). **Depende de:** E2. **Diseño:** R2 §5, §12

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E3.1 | Crear `stackflow-registry`; `OperationRegistryBean` (`@Singleton @Startup`): carga las operaciones RPN y los algoritmos desde `MerLabCorePort`; búsqueda por texto, categoría y fuente. | `registry` | unit tests |
| E3.2 | `OperationResource`: `GET /operations`, `GET /operations/{token}`. | `adapter-jakarta` | IT |
| E3.3 | `POST /operations/{token}/execute` (entradas en línea o refs; respuesta con salidas). | `adapter-jakarta`, `rpn` | IT con `fft` |
| E3.4 | Recuentos en `/info` (operaciones RPN, algoritmos). | `adapter-jakarta` | IT |

### E4 · Algoritmos ML y ejemplos
**Objetivo:** catálogo y ejecución (paridad con el miniserver). **Depende de:** E3, D4. **Diseño:** CL §3.5, §5.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E4.1 | `MerLabCoreAdapter.listAlgorithms` / `runAlgorithm` (lectura de `AlgorithmCatalog`; `ExampleRunner` en `SerialExecutionBean`). | `adapter-merlabcore` | unit tests |
| E4.2 | `MLCoreServiceBean` + `AlgorithmJobHandler` y `ExampleJobHandler`. | `application`, `jobs` | JUnit + Mockito |
| E4.3 | `AlgorithmResource` (`/ml/algorithms…`, `/ml/examples…`, runs 202 o `mode=sync`). | `adapter-jakarta` | IT (p. ej. KMeans) |
| E4.4 | IT de paridad con cada fila MerLabCore de RU §5.6. | `MerLabCoreParityIT` | verde |

**DoD del grupo E:** operaciones RPN y algoritmos de MerLabCore usables por REST.

---

## Grupo F · Archivos y datos

### F1 · Archivos
**Objetivo:** subir y descargar cualquier archivo (S3). **Depende de:** C4. **Diseño:** R2 §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F1.1 | `StoredFiles` (entidad) + `StoredFileDaoBean`. | `storage` | IT |
| F1.2 | `FileStorageServiceBean`: disco (`stackflow.storage.dir`), SHA-256, límites de tamaño, sin rutas peligrosas. | `storage` | unit tests |
| F1.3 | `FileResource`: `POST /files` (multipart), `GET /files`, `/files/{id}`, `/files/{id}/content`, `DELETE`. | `adapter-jakarta` | IT con un WAV y un PNG |

### F2 · Señales y datasets
**Objetivo:** `signals`, `signal_data`, `signal_groups`, `datasets`. **Depende de:** F1. **Diseño:** RU Anexo A.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F2.1 | Entidades y DAOs de señales y datos de señal. | `storage` | IT |
| F2.2 | Entidades y DAOs de grupos y datasets. | `storage` | IT |
| F2.3 | `DataManagerServiceBean.load/save` (refs `signal:`, `dataset:`, `file:`) con lectura por chunks (keyset) y `ResourceManagerBean` (límite de memoria). | `storage`, `runtime` | unit + IT |
| F2.4 | `SignalResource`, `SignalGroupResource`, `DatasetResource` (CRUD + datos paginados). | `adapter-jakarta` | IT |

### F3 · Importadores
**Objetivo:** WAV, CSV, OHLCV, JSON, HDF5 e imagen → señal/dataset. **Depende de:** F2. **Diseño:** R2 §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F3.1 | `FileImporter` + `WavImporter` (`javax.sound.sampled`; mono/estéreo, `sampleRate` en meta). | `storage` | unit test con un WAV de prueba |
| F3.2 | `CsvImporter` y `JsonImporter` (con los loaders de MerLabCore vía `MerLabCorePort` si aplica). | `storage`, `adapter-merlabcore` | unit tests |
| F3.3 | `OhlcvImporter` (timestamp, open, high, low, close, volume → dataset + una señal por columna). | `storage` | unit test |
| F3.4 | **Spike:** HDF5 (`HDF5DataLoader` de MerLabCore) e imágenes (qué operaciones de imagen ofrece MerLabCore). | Umsetzungsplan | decisión |
| F3.5 | `ImageImporter` y HDF5 según el spike. | `storage` | unit tests |
| F3.6 | `POST /signals/import`, `POST /datasets/import` (job `DATA_IMPORT` si es grande) y `GET /datasets/{id}/export`. | `adapter-jakarta`, `jobs` | IT |

### F4 · BD externas (DBAdapter v1)
**Objetivo:** `db_connections` e importación. **Depende de:** F3, C5.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F4.1 | `SecretCipher` (Java puro, AES-GCM, clave de la config). | `security` | unit test |
| F4.2 | `DbConnections`: entidad, DAO, `DbConnectionServiceBean`, `DbConnectionResource` (sin devolver la contraseña). | varios | IT |
| F4.3 | `POST /db-connections/{id}/test` (MariaDB, PostgreSQL). | `storage` | IT |
| F4.4 | Importación por consulta (`SELECT` solamente, por chunks) como job `DATA_IMPORT`. | `storage`, `jobs` | IT |

**DoD del grupo F:** cualquier dato entra al sistema y se puede cargar al stack.

---

## Grupo G · Pipelines, batch, escenarios y archivo

### G1 · Pipelines
**Objetivo:** definición LOAD → RPN/ALGORITHM → SAVE, versionada. **Depende de:** E3, F2, A6. **Diseño:** R2 §5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G1.1 | Crear `stackflow-pipeline`; `PipelineDefinition`, `PipelineStep`, `StepKind` (domain) + `PipelineValidator` (Java puro: tokens existentes en el registry, LOAD primero). | `domain`, `pipeline` | unit tests |
| G1.2 | Entidades y DAOs `Pipelines`, `PipelineSteps`, `PipelineVersions`, `PipelineCurrentVersion`. | `storage` | IT |
| G1.3 | `PipelineServiceBean` (CRUD, últimas N versiones, versión actual). | `pipeline` | JUnit + Mockito |
| G1.4 | `PipelineResource` (CRUD, steps, versions, current-version). | `adapter-jakarta` | IT |

### G2 · Ejecución de pipelines
**Objetivo:** `PipelineExecutorBean` (núcleo S1). **Depende de:** G1, D4. **Diseño:** R2 §5, §10.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G2.1 | `PipelineRunner` (Java puro): ejecuta los pasos sobre una `StackSession` con `MerLabCorePort`, emitiendo eventos a un `StepListener`. | `pipeline` | unit tests con port falso |
| G2.2 | `PipelineExecutorBean` (LOAD/SAVE con `DataManagerServiceBean`) + `PipelineJobHandler`. | `pipeline`, `jobs` | JUnit + Mockito |
| G2.3 | `POST /pipelines/{id}/execute` (alias `/runs`) → 202 + eventos por WebSocket. | `adapter-jakarta` | IT con MerLabCore real |

### G3 · Batch (BatchProcessor + Worker v1)
**Objetivo:** datos grandes por chunks. **Depende de:** G2, F2.3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G3.1 | `ChunkPlanner` (Java puro: tamaño, solapamiento para filtros). | `pipeline` | unit tests |
| G3.2 | `BatchProcessorServiceBean`: reparte chunks a `WorkerServiceBean` (`@Asynchronous`), une resultados y aplica la política de fallo parcial; progreso por eventos. | `pipeline` | IT con 1 M de muestras |
| G3.3 | `OrchestratorServiceBean.runBatch` + `BatchConfigDTO` en `/pipelines/{id}/execute`. | `jobs`, `adapter-jakarta` | IT |

### G4 · Escenarios (ScenarioManager v1)
**Objetivo:** reproducibilidad. **Depende de:** G2, E2.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G4.1 | Entidades y DAOs `Scenarios`, `ScenarioEntities`, `ScenarioRuns`. | `storage` | IT |
| G4.2 | `ScenarioManagerBean` (snapshot de stack + parámetros + entidades; cargar; listar) + `ScenarioJobHandler`. | `pipeline` | JUnit + Mockito |
| G4.3 | `ScenarioComparator` (Java puro, tolerancia) + golden + job `SCENARIO_REGRESSION`. | `pipeline` | unit tests |
| G4.4 | `ScenarioResource` (CRUD, runs, compare, golden). | `adapter-jakarta` | IT |

### G5 · Archivo (Archiving v1)
**Objetivo:** versiones y rollback. **Depende de:** G4.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G5.1 | `V0xx__archives.sql`, entidad `Archives`, DAO. | `storage` | IT |
| G5.2 | `ArchivingServiceBean` (versiones de resultados, escenarios y pipelines; retención). | `pipeline` | JUnit + Mockito |
| G5.3 | `ArchiveResource` (list, get, rollback). | `adapter-jakarta` | IT |

**DoD del grupo G:** pipelines de MerLabCore ejecutables, en batch, reproducibles y versionados.

---

## Grupo U · Primeras aplicaciones: audio y trading

### U1 · Audio
**Objetivo:** el ejemplo de las notas: `voice.wav → normalize → lowpass → FFT → picos → guardar`. **Depende de:** G2, F3. **Diseño:** R2 §10.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| U1.1 | **Spike:** qué operaciones de audio ofrece MerLabCore hoy (normalize, filtros, FFT, ventanas, detección de picos…) y cuáles faltan. 👤 Lo que falte lo decide y añade el usuario en MerLabCore. | Umsetzungsplan | tabla |
| U1.2 | Pipeline de ejemplo versionado (`docs/v2/examples/audio-spectrum.json`) y WAV de prueba pequeño. | `docs/v2/examples/` | — |
| U1.3 | IT extremo a extremo: subir WAV → importar → ejecutar → descargar el espectro. | `AudioPipelineIT` | verde |
| U1.4 | Guía corta "audio con StackFlow" (curl/Postman). | `docs/v2/guides/AUDIO.md` | — |

### U2 · Trading
**Objetivo:** velas OHLCV → indicadores → algoritmo ML → señal de salida. **Depende de:** G2, F3, E4. **Diseño:** R2 §10.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| U2.1 | **Spike:** indicadores disponibles en MerLabCore (`ma`, `wma`, `deriv`, `stddev`…) y algoritmos ML útiles; lista de los que faltan (RSI, MACD…). 👤 El usuario decide cuáles añade a MerLabCore. | Umsetzungsplan | tabla |
| U2.2 | Pipeline de ejemplo (`docs/v2/examples/trading-ma-ml.json`) y CSV OHLCV de prueba (datos sintéticos o públicos). | `docs/v2/examples/` | — |
| U2.3 | IT extremo a extremo: importar OHLCV → ejecutar → señal de predicción. | `TradingPipelineIT` | verde |
| U2.4 | Escenario golden del pipeline de trading (regresión). | escenario | job SCENARIO_REGRESSION verde |
| U2.5 | Guía corta "trading con StackFlow". | `docs/v2/guides/TRADING.md` | — |

**DoD del grupo U:** dos aplicaciones reales funcionando de punta a punta.

---

## Grupo I · Streaming y alertas

### I1 · Streams
**Objetivo:** StreamProcessor + Buffer v1. **Depende de:** G2, A7.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I1.1 | `V0xx__streams_alerts.sql`; entidades y DAOs `Streams`, `AlertRules`, `AlertEvents`. | `storage` | IT |
| I1.2 | `Buffer` (Java puro, acotado, política de descarte) y `WindowAssembler` (tumbling/sliding, por cantidad o tiempo). | `pipeline` | unit tests |
| I1.3 | `StreamProcessorServiceBean`: ejecuta el pipeline por ventana (`@Asynchronous`); estados CREATED, RUNNING, STOPPED y FAILED (añadir su diagrama de estado al diseño). | `pipeline`, `design/` | IT |
| I1.4 | `StreamResource` (CRUD, start, stop). | `adapter-jakarta` | IT |

### I2 · Ingesta
**Objetivo:** EventListener v1. **Depende de:** I1.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I2.1 | `POST /streams/{id}/events` (lotes; API key permitida). | `adapter-jakarta` | IT |
| I2.2 | `StreamSocketEndpoint` (`/ws/streams/{id}/ingest` y `/results`). | `adapter-jakarta` | IT |
| I2.3 | 👤 Cola JMS en WildFly + `SignalProcessorMDB` (nombre v1, reescrito). | `adapter-jakarta`, `scripts/wildfly/jms.cli` | IT |

### I3 · Alertas
**Objetivo:** Alerting v1. **Depende de:** I2.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I3.1 | `AlertRuleEvaluator` (Java puro: GT, LT, RATE). | `pipeline` | unit tests |
| I3.2 | `AlertServiceBean` + `AlertResource` + `AlertSocketEndpoint` (`/ws/alerts`, ack). | varios | IT |

### I4 · Fuentes externas (opcional)
**Objetivo:** datos de mercado en vivo. **Depende de:** I2.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I4.1 | `StreamSourcePort` (ports). | `ports` | compila |
| I4.2 | **Spike:** fuente de trading en vivo (JForex SDK, proxy REST/WS u otra); decidir. | Umsetzungsplan | decisión |
| I4.3 | Adaptador elegido en un módulo con perfil propio. | nuevo módulo | IT |
| I4.4 | Kafka / RabbitMQ (perfiles `kafka` / `rabbit`), si se necesitan. | nuevos módulos | IT |

**DoD del grupo I:** trading en tiempo real con ventanas y alertas.

---

## Grupo H · Modelos: inferencia local y ModelForge remoto

### H1 · Modelos y archivos
**Objetivo:** `models`, `model_files`. **Depende de:** F1, A8.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H1.1 | Entidades y DAOs `Models`, `ModelFiles` (archivos en `FileStorageServiceBean`). | `storage` | IT |
| H1.2 | `ModelServiceBean` + `ModelResource` (CRUD, archivos). | `application`, `adapter-jakarta` | IT |

### H2 · Inferencia local (MerLabCore)
**Objetivo:** predecir con algoritmos clásicos en el PC. **Depende de:** H1, E4.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H2.1 | **Spike:** qué algoritmos de MerLabCore permiten guardar/cargar un modelo entrenado y predecir. | Umsetzungsplan | tabla |
| H2.2 | `MLCoreServiceBean.predictLocal` + caché LRU con límite de memoria. | `application` | unit tests |
| H2.3 | `POST /models/{id}/predict` (sync o job `PREDICT`). | `adapter-jakarta` | IT |

### H3 · ModelForge remoto (Mac mini)
**Objetivo:** entrenar por API sin usar el PC. **Depende de:** H1, D4. **Diseño:** R2 §8

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H3.1 | `ModelForgePort` (ports) + `UnavailableModelForge` (por defecto, 503 `ENGINE_NOT_INSTALLED`). | `ports`, `application` | unit test |
| H3.2 | 👤 **Spike:** contrato de la API de ModelForge con el usuario (endpoints, autenticación, formatos); se documenta en `design/MODELFORGE_API.md`. | `design/` | documento aprobado |
| H3.3 | Crear `stackflow-adapter-modelforge` (perfil `modelforge`): `ModelForgeRemoteAdapter` (cliente HTTP con timeouts, URL de la config). | nuevo módulo | unit tests con servidor simulado (WireMock) |
| H3.4 | `ModelForgeTrainJobHandler` (envía la orden, consulta el estado y descarga el modelo al terminar) + `POST /models/{id}/train` + `GET /modelforge/status`. | `jobs`, `adapter-jakarta` | IT con servidor simulado |
| H3.5 | 👤 Prueba real contra ModelForge en el Mac mini (cuando exista). | — | entrenamiento remoto OK |

**DoD del grupo H:** inferencia local y entrenamiento remoto, sin cargar el PC.

---

## Grupo J · PegasusRAG (opcional y separado)

Todo lo de CL §3.6, §3.9, §3.10, §4.2–4.6 y §5.1/§5.6 sigue válido con los nombres de R2 §11. **No se mezcla con MerLabCore** (R2 §9).

### J1 · API Pegasus sin Pegasus
**Objetivo:** los endpoints existen y responden 503 sin la librería. **Depende de:** D4, A9.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J1.1 | `PegasusRagPort` + dominio RAG (`RagQuery`, `RagResult`, `ConversationInfo`…). | `ports`, `domain` | compila |
| J1.2 | `UnavailablePegasusRag` + `PegasusRagServiceBean` + guarda de jobs `PEGASUS_*`. | `application`, `jobs` | JUnit + Mockito |
| J1.3 | `PegasusRagMapper` + todos los resources RAG (query, usecases, flows, conversations ×4, scenarios, run-logs ×4, indexing, export). | `adapter-jakarta` | compila |
| J1.4 | Handlers `PegasusQueryJobHandler`, `PegasusScenarioJobHandler`, `PegasusIndexingJobHandler`. | `jobs` | unit tests |
| J1.5 | IT sin Pegasus: todo `/rag/*` → 503 `ENGINE_NOT_INSTALLED`; `/health/ready` UP. | `NoPegasusIT` | verde |

### J2 · Perfil `pegasus` y arranque
**Objetivo:** Pegasus dentro de WildFly, con los LLM en el Mac mini. **Depende de:** J1. **Diseño:** CL §3.6, §4.3, §5.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J2.1 | 👤 `mvn install` de PegasusRAG (sin cambios). | — | jar en `~/.m2` |
| J2.2 | Crear `stackflow-adapter-pegasus` (perfil `pegasus`): excluir `slf4j-simple`; `jboss-deployment-structure.xml`. | POMs | `-Ppegasus` despliega |
| J2.3 | `PegasusServerConfig` (URL de Ollama en el Mac mini, vector store, memoria, perfiles de casos de uso). | `adapter-pegasus` | unit test |
| J2.4 | `PegasusRuntimeProducer` (`RagApplicationBuilder`, adaptadores de la librería, `UseCaseRegistry`) + `RuntimeState` + sonda + `/admin/pegasus/reload`. | `adapter-pegasus` | READY/DEGRADED |
| J2.5 | `PegasusRagAdapter` (`@Alternative @Priority`): health, use cases y flows. | `adapter-pegasus` | `/rag/usecases` con datos |

### J3 · Consultas
**Objetivo:** `POST /rag/query`. **Depende de:** J2.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J3.1 | `PegasusResultMapper` y decisión O1 (alias de `orchestrationMode`). 👤 | `adapter-pegasus` | unit tests |
| J3.2 | `CurrentJobContext`, `StackflowObservabilityBridge`, `StackflowAuditSink`. | `adapter-pegasus` | unit tests |
| J3.3 | `FlowUseCaseExecutor` + `RagUseCaseDispatcher`. | `adapter-pegasus` | unit tests |
| J3.4 | 👤 Query extremo a extremo con el LLM del Mac mini (200, 422, 503, 504). | IT | verde |

### J4 · Casos de uso, memoria, logs e indexado
**Objetivo:** paridad funcional. **Depende de:** J3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J4.1 | **Spike:** inventario `useCaseId` → Runner (lectura del miniserver, sin tocarlo). | Umsetzungsplan | tabla |
| J4.2 | Executors por familia (document, exegesis, music, diagnostics, technical scenarios) + concurrencia. | `adapter-pegasus` | unit + IT |
| J4.3 | Conversaciones (MemoryPort; listado por Redis SCAN, 👤 confirmar prefijo) y run-logs completos. | `adapter-pegasus` | IT |
| J4.4 | **Spike** + implementación del indexado (`PEGASUS_INDEXING`). | `adapter-pegasus` | IT |

### J5 · Exportar resultados al stack (puente S5)
**Objetivo:** resultado de Pegasus → dataset/archivo → `LOAD` en un pipeline. **Depende de:** J3, F2. **Diseño:** R2 §9

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J5.1 | `RagResultExporter` (Java puro: `RagResult` → JSON/CSV tabular). | `application` | unit tests |
| J5.2 | `POST /rag/runs/{jobId}/export` → `DataManagerServiceBean.export` → dataset. | `adapter-jakarta` | IT |
| J5.3 | IT: exportar → pipeline con `LOAD dataset:…`. | `RagExportPipelineIT` | verde |

### J6 · Paridad y regresión
**Objetivo:** base mínima del miniserver de Pegasus. **Depende de:** J4, J5.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J6.1 | IT por cada fila Pegasus de RU §5.6 (`-Ppegasus`). | `PegasusParityIT` | verde |
| J6.2 | `NoPegasusIT` con `-P!pegasus` (sin regresión). | `NoPegasusIT` | verde |

**DoD del grupo J:** PegasusRAG opcional, separado y conectado al stack solo mediante datos.

---

## Grupo K · Observabilidad y operación

### K1 · Métricas y Monitor
**Objetivo:** Monitor v1. **Depende de:** D4, G3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K1.1 | **Spike:** métricas en WildFly 36 (Micrometer/Prometheus). | Umsetzungsplan | decisión |
| K1.2 | `MonitorServiceBean`: jobs, batch, streams, sesiones de stack, JVM. | `runtime` | unit tests |
| K1.3 | `GET /admin/metrics` + Prometheus. | `adapter-jakarta` | IT |
| K1.4 | Alertas de sistema (memoria, fallos, colas) → `AlertServiceBean`. | `runtime` | unit tests |

### K2 · Administración y logs
**Objetivo:** visión operativa. **Depende de:** K1.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K2.1 | `GET /admin/components` (MerLabCore, Pegasus, ModelForge, BD, almacenamiento, JMS). | `adapter-jakarta` | IT |
| K2.2 | Logging JSON en WildFly (CLI) + MDC con `correlationId`/`jobId` en los hilos de jobs. | `scripts/wildfly/logging.cli`, `jobs` | log JSON |
| K2.3 | Eventos para FlowScope (estados de la runtime, resultados, métricas) documentados. | `docs/v2/FLOWSCOPE.md` | — |

---

## Grupo L · Plugins (PluginManager v1)

### L1 · Plugins
**Objetivo:** extender el registry sin tocar el núcleo. **Depende de:** E3.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| L1.1 | Módulo `stackflow-plugin-api`: `StackflowPlugin`, `OperationPlugin`, `ImporterPlugin`, `PluginContext`. | nuevo módulo | compila |
| L1.2 | `V0xx__plugins.sql` + registro (checksum, estado). | `storage` | IT |
| L1.3 | `PluginManagerServiceBean`: carga aislada desde `stackflow.plugins.dir`, enable/disable/unload, solo checksums aprobados. | `registry` | unit tests |
| L1.4 | Las `OperationPlugin` aparecen en `/operations` (`source=PLUGIN`) y los `ImporterPlugin` en los formatos de importación. | `registry`, `storage` | IT |
| L1.5 | `/admin/plugins` + plugin de ejemplo `stackflow-plugin-example`. | `adapter-jakarta`, nuevo módulo | IT |

---

## Grupo M · Cierre y release 2.0

### M1 · Pruebas y rendimiento
| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M1.1 | Matriz endpoint → IT (desde OpenAPI); cubrir huecos. | `docs/v2/TEST_MATRIX.md` | 100 % |
| M1.2 | Ejecución completa con y sin `pegasus` y `modelforge`. | — | verde |
| M1.3 | Carga: jobs concurrentes, chunks, profundidad del stack, throughput de streams; límites recomendados. | `perf/`, `docs/v2/LIMITS.md` | informe |

### M2 · Documentación y seguridad
| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M2.1 | Colección Postman v2 + guía de API generada desde OpenAPI. | `docs/v2/postman/`, `docs/v2/API.md` | importable |
| M2.2 | Guía de instalación (NetBeans, WildFly, MariaDB, perfiles `pegasus`/`modelforge`, Mac mini). | `docs/v2/SETUP.md` | reproducible |
| M2.3 | Revisión de seguridad (autenticación en todo, límites de entrada y archivos, secretos fuera de logs). | `docs/v2/SECURITY_REVIEW.md` | sin hallazgos abiertos |

### M3 · Release
| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M3.1 | Versión `2.0.0`, README y CHANGELOG. | POMs | build |
| M3.2 | 👤 Tag `v2.0.0` y push (con aprobación). | git | tag remoto |
| M3.3 | 👤 Rama por defecto en GitHub. | GitHub | decisión |

---

## Grupo N · Merlynix (después de 2.0)

Merlynix es el **lenguaje del stack RPN** (S7). Empieza cuando StackFlow 2.0 esté integrado y en uso; su diseño dependerá de lo aprendido con audio y trading.

### N1 · Diseño del lenguaje
| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| N1.1 | 👤 **Spike:** requisitos de Merlynix a partir de los pipelines reales (U1, U2) y del repo `merlynix`; relación con `RPNParser` de MerLabCore. | `design/MERLYNIX.md` | documento aprobado |
| N1.2 | 👤 Gramática y semántica (pipelines, reglas, variables, control de flujo). | `design/MERLYNIX.md` | aprobado |

### N2 · Integración
| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| N2.1 | `MerlynixPort` + `stackflow-adapter-merlynix` (parser/intérprete en Java puro, en su propio proyecto o módulo). | nuevos módulos | unit tests |
| N2.2 | Programas Merlynix como pipelines (traducción a `PipelineDefinition`) y como jobs. | `pipeline`, `jobs` | IT |
| N2.3 | Endpoints `/merlynix/programs` (CRUD) y `/merlynix/run`. | `adapter-jakarta` | IT |
| N2.4 | Operaciones Merlynix en el registry (`source=MERLYNIX`). | `registry` | IT |
