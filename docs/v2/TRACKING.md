# StackFlowServer v2: tracking

Estado de todos los milestones y micropasos del [ROADMAP.md](ROADMAP.md). Lo actualiza el agente al terminar cada micropaso (ver [README.md](README.md)).

**Estados:** `Pendiente` · `En proceso` · `Terminado` · `Bloqueado` (con motivo en Notas). 👤 = requiere al usuario. Spike = solo nota de decisión.

## Paso actual

| Campo | Valor |
|---|---|
| Siguiente micropaso | **0.3.3** |
| Último terminado | 0.3.2 |
| Última actualización | 16.10.2026 |

## Resumen por milestone

| Grupo | Milestone | Título | Pasos | Estado |
|---|---|---|---|---|
| 0 | 0.1 | Rama v2 y retirada del código v1 | 5/5 | Terminado |
| 0 | 0.2 | Proyecto Maven multi-módulo | 6/6 | Terminado |
| 0 | 0.3 | NetBeans y WildFly | 2/5 | En proceso |
| 0 | 0.4 | Guardarraíles | 0/4 | Pendiente |
| 0 | 0.5 | Documentación de trabajo | 4/4 | Terminado |
| A | A0 | Soporte de pruebas | 0/2 | Pendiente |
| A | A1 | Comunes | 0/5 | Pendiente |
| A | A2 | Seguridad y administración | 0/6 | Pendiente |
| A | A3 | Jobs | 0/6 | Pendiente |
| A | A4 | Operaciones, stack RPN y ML | 0/6 | Pendiente |
| A | A5 | Archivos y datos | 0/5 | Pendiente |
| A | A6 | Pipelines, escenarios y archivo | 0/5 | Pendiente |
| A | A7 | Streaming y alertas | 0/4 | Pendiente |
| A | A8 | Modelos y ModelForge | 0/4 | Pendiente |
| A | A9 | PegasusRAG | 0/6 | Pendiente |
| A | A10 | Administración y plugins | 0/2 | Pendiente |
| B | B1 | Aplicación JAX-RS | 0/2 | Pendiente |
| B | B2 | Modelo de errores | 0/4 | Pendiente |
| B | B3 | Filtros | 0/3 | Pendiente |
| B | B4 | Health, info y OpenAPI | 0/4 | Pendiente |
| B | B5 | ITs base | 0/2 | Pendiente |
| C | C1 | Base de datos y migraciones | 0/7 | Pendiente |
| C | C2 | Usuarios y roles | 0/3 | Pendiente |
| C | C3 | Autenticación | 0/5 | Pendiente |
| C | C4 | Ajustes y proyectos | 0/4 | Pendiente |
| C | C5 | Configuración e historial | 0/4 | Pendiente |
| D | D1 | Dominio de jobs | 0/2 | Pendiente |
| D | D2 | Persistencia | 0/2 | Pendiente |
| D | D3 | Ejecución | 0/7 | Pendiente |
| D | D4 | REST de jobs | 0/2 | Pendiente |
| D | D5 | Eventos en tiempo real | 0/1 | Pendiente |
| D | D6 | Post-proceso y schedules | 0/4 | Pendiente |
| E | E1 | Integración de MerLabCore | 0/5 | Pendiente |
| E | E2 | Motor RPN y sesiones | 0/5 | Pendiente |
| E | E3 | Registry de operaciones | 0/4 | Pendiente |
| E | E4 | Algoritmos ML y ejemplos | 0/4 | Pendiente |
| F | F1 | Archivos | 0/3 | Pendiente |
| F | F2 | Señales y datasets | 0/4 | Pendiente |
| F | F3 | Importadores | 0/6 | Pendiente |
| F | F4 | BD externas (DBAdapter v1) | 0/4 | Pendiente |
| G | G1 | Pipelines | 0/4 | Pendiente |
| G | G2 | Ejecución de pipelines | 0/3 | Pendiente |
| G | G3 | Batch (BatchProcessor + Worker v1) | 0/3 | Pendiente |
| G | G4 | Escenarios (ScenarioManager v1) | 0/4 | Pendiente |
| G | G5 | Archivo (Archiving v1) | 0/3 | Pendiente |
| U | U1 | Audio | 0/4 | Pendiente |
| U | U2 | Trading | 0/5 | Pendiente |
| I | I1 | Streams | 0/4 | Pendiente |
| I | I2 | Ingesta | 0/3 | Pendiente |
| I | I3 | Alertas | 0/2 | Pendiente |
| I | I4 | Fuentes externas (opcional) | 0/4 | Pendiente |
| H | H1 | Modelos y archivos | 0/2 | Pendiente |
| H | H2 | Inferencia local (MerLabCore) | 0/3 | Pendiente |
| H | H3 | ModelForge remoto (Mac mini) | 0/5 | Pendiente |
| J | J1 | API Pegasus sin Pegasus | 0/5 | Pendiente |
| J | J2 | Perfil `pegasus` y arranque | 0/5 | Pendiente |
| J | J3 | Consultas | 0/4 | Pendiente |
| J | J4 | Casos de uso, memoria, logs e indexado | 0/4 | Pendiente |
| J | J5 | Exportar resultados al stack (puente S5) | 0/3 | Pendiente |
| J | J6 | Paridad y regresión | 0/2 | Pendiente |
| K | K1 | Métricas y Monitor | 0/4 | Pendiente |
| K | K2 | Administración y logs | 0/3 | Pendiente |
| L | L1 | Plugins | 0/5 | Pendiente |
| M | M1 | Pruebas y rendimiento | 0/3 | Pendiente |
| M | M2 | Documentación y seguridad | 0/3 | Pendiente |
| M | M3 | Release | 0/3 | Pendiente |
| N | N1 | Diseño del lenguaje | 0/2 | Pendiente |
| N | N2 | Integración | 0/4 | Pendiente |

Total: 68 milestones, 265 micropasos.

---

## Grupo 0 · Fundamentos

### 0.1 · Rama v2 y retirada del código v1

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.1.1 | Crear el tag v1-legacy sobre el último commit de master (53ef018). | 👤 | Terminado | 15.10.2026 | tag | v1-legacy → 53ef018; subido a GitHub |
| 0.1.2 | Crear la rama v2 desde master y cambiar a ella en la carpeta stackflow. |  | Terminado | 15.10.2026 |  | carpeta stackflow |
| 0.1.3 | En v2: mover la documentación v1 a docs/v1/ (API.md, DATABASE_SCHEMA.md, SETUP_GUIDE.md, Setup Guide.docx,… |  | Terminado | 15.10.2026 | [0.1.3] | doc v1 en docs/v1; src, pom y nb-configuration v1 retirados |
| 0.1.4 | .gitignore (target/, nbproject/private/, *.class, *.log) y git rm -r --cached target. |  | Terminado | 15.10.2026 | [0.1.4] | .gitignore; 4 archivos de target/ fuera del índice |
| 0.1.5 | README v2 provisional (qué es, cómo compilar, enlace a docs/v2/). |  | Terminado | 15.10.2026 | [0.1.5] | README v2 (inglés) |

### 0.2 · Proyecto Maven multi-módulo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.2.1 | POM padre com.merlab:stackflow-parent:2.0.0-SNAPSHOT: Java 17, UTF-8, Jakarta 10, dependencyManagement (jak… |  | Terminado | 15.10.2026 | [0.2.1] | Maven con Windows-ROOT (certificados) |
| 0.2.2 | Módulos iniciales: stackflow-domain, stackflow-ports (Java puro) y stackflow-api-contract. |  | Terminado | 15.10.2026 | [0.2.2] | domain, ports, api-contract |
| 0.2.3 | Módulo stackflow-adapter-jakarta (jar, META-INF/beans.xml). |  | Terminado | 16.10.2026 | [0.2.3] | beans.xml annotated |
| 0.2.4 | Módulo stackflow-host-wildfly (war, finalName stackflow, jboss-web.xml con context root /stackflow, WEB-INF… |  | Terminado | 16.10.2026 | [0.2.4] | stackflow.war (8 KB) |
| 0.2.5 | Perfiles pegasus y modelforge declarados en el padre (vacíos de momento). |  | Terminado | 16.10.2026 | [0.2.5] | pegasus inactivo (merlab-rag no instalado) |
| 0.2.6 | Regla documentada en el POM padre: los demás módulos (rpn, registry, runtime, jobs, pipeline, storage, secu… |  | Terminado | 16.10.2026 | [0.2.6] | tabla de 19 módulos en el POM |

### 0.3 · NetBeans y WildFly

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.3.1 | Abrir stackflow (rama v2) en NetBeans como proyecto padre; nb-configuration.xml en host-wildfly con el hint… | 👤 | Terminado | 16.10.2026 | [0.3.1] | NetBeans: 5 módulos, Clean and Build OK |
| 0.3.2 | Decidir la configuración del servidor (decidido: standalone-microprofile.xml, DA-004) y dejarla en HTTP 8090 con un script CLI. | 👤 | Terminado | 16.10.2026 | [0.3.2] | microprofile, HTTP 8090 (DA-004) |
| 0.3.3 | DataSources StackflowDS y StackflowTestDS con un script CLI versionado. | 👤 | Pendiente |  |  |  |
| 0.3.4 | Registrar WildFly 36 en NetBeans (standalone-microprofile.xml) y desplegar el WAR vacío desde NetBeans. | 👤 | Pendiente |  |  |  |
| 0.3.5 | wildfly-maven-plugin + scripts/build.ps1 y scripts/deploy.ps1. |  | Pendiente |  |  |  |

### 0.4 · Guardarraíles

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.4.1 | Módulo de test stackflow-architecture-tests con ArchUnit: domain/ports sin jakarta.* (R1); solo adapter-mer… |  | Pendiente |  |  |  |
| 0.4.2 | Convención *Test (surefire) / *IT (failsafe, perfil it). |  | Pendiente |  |  |  |
| 0.4.3 | Base Arquillian (WildFly managed, jbossHome por propiedad) + AbstractStackflowIT con cliente HTTP de caja n… |  | Pendiente |  |  |  |
| 0.4.4 | .editorconfig y cabecera de licencia Apache 2.0. |  | Pendiente |  |  |  |

### 0.5 · Documentación de trabajo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.5.1 | docs/v2/ con README, ROADMAP, TRACKING, UMSETZUNGSPLAN y design/. |  | Terminado | 15.10.2026 |  |  |
| 0.5.2 | AGENTS.md y CLAUDE.md en la raíz, con el protocolo ROGER. |  | Terminado | 15.10.2026 |  |  |
| 0.5.3 | Primer commit de la documentación. | 👤 | Terminado | 15.10.2026 |  | c371172 |
| 0.5.4 | Registro de decisiones (DA-001), notas para Spring y Revisión 2 del diseño y del roadmap. |  | Terminado | 15.10.2026 |  | Revisión 2 |

## Grupo A · Contrato de la API (DTOs)

### A0 · Soporte de pruebas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A0.1 | Dependencias de test: jackson-databind (+ records), hibernate-validator + expressly. |  | Pendiente |  |  |  |
| A0.2 | JsonTestSupport y ValidationTestSupport. |  | Pendiente |  |  |  |

### A1 · Comunes

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A1.1 | ErrorDTO(code, status, message, correlationId, details) y ErrorDetailDTO. |  | Pendiente |  |  |  |
| A1.2 | HealthDTO(status, components) y ComponentHealthDTO (estados UP, DEGRADED, DOWN, NOT_INSTALLED). |  | Pendiente |  |  |  |
| A1.3 | InfoDTO(version, buildTime, engines, counts). |  | Pendiente |  |  |  |
| A1.4 | PageDTO<T>(items, total, offset, limit) + límites (1–500). |  | Pendiente |  |  |  |
| A1.5 | ApiPaths: todas las rutas (RU §5, Anexo A.3, R2 §12). |  | Pendiente |  |  |  |

### A2 · Seguridad y administración

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A2.1 | LoginDTO, TokenDTO(token, expiresAt, roles), MeDTO. |  | Pendiente |  |  |  |
| A2.2 | ApiKeyDTO, ApiKeyCreateDTO, ApiKeyCreatedDTO(+secret). |  | Pendiente |  |  |  |
| A2.3 | UserDTO (sin contraseña), UserCreateDTO (contraseña ≥ 10), UserUpdateDTO, RoleAssignmentDTO. |  | Pendiente |  |  |  |
| A2.4 | UserSettingDTO. |  | Pendiente |  |  |  |
| A2.5 | ProjectDTO, ProjectMemberDTO. |  | Pendiente |  |  |  |
| A2.6 | ConfigEntryDTO, HistoryDTO. |  | Pendiente |  |  |  |

### A3 · Jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A3.1 | CreateJobDTO(type, payload) con validación del tipo. |  | Pendiente |  |  |  |
| A3.2 | JobDTO y PageDTO<JobDTO>. |  | Pendiente |  |  |  |
| A3.3 | JobEventsDTO(jobId, status, nextSequence, events) y JobEventDTO. |  | Pendiente |  |  |  |
| A3.4 | JobResultDTO(jobId, status, result, completedAt) (result polimórfico). |  | Pendiente |  |  |  |
| A3.5 | ScheduleDTO(name, cron, jobType, payload, enabled, nextFireAt). |  | Pendiente |  |  |  |
| A3.6 | PostProcessTaskDTO(jobType, payload, onStatus). |  | Pendiente |  |  |  |

### A4 · Operaciones, stack RPN y ML

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A4.1 | OperationDTO(token, name, category, arity, description, example, source). |  | Pendiente |  |  |  |
| A4.2 | OperationExecuteDTO(inputs[StackElementDTO], params) y OperationResultDTO(outputs). |  | Pendiente |  |  |  |
| A4.3 | StackElementDTO(type, ref, value, meta) (ref tipo signal:12; value en línea para escalares y vectores peque… |  | Pendiente |  |  |  |
| A4.4 | RpnEvaluateDTO(expression, inputs) y RpnResultDTO(stack). |  | Pendiente |  |  |  |
| A4.5 | StackSessionDTO(id, depth, top, expiresAt), StackPushDTO, StackApplyDTO(token, params), StackSnapshotDTO. |  | Pendiente |  |  |  |
| A4.6 | AlgorithmDTO (campos de AlgorithmDescriptor), AlgorithmRunDTO(params, mode) y AlgorithmResultDTO(success, c… |  | Pendiente |  |  |  |

### A5 · Archivos y datos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A5.1 | StoredFileDTO(id, fileName, mediaType, sizeBytes, sha256, createdAt, origin). |  | Pendiente |  |  |  |
| A5.2 | ImportDTO(fileId, format, options) con formatos WAV, CSV, OHLCV_CSV, JSON, HDF5 e IMAGE. |  | Pendiente |  |  |  |
| A5.3 | SignalDTO(id, name, projectId, unit, sampleRate, length, meta) y SignalDataPageDTO. |  | Pendiente |  |  |  |
| A5.4 | SignalGroupDTO, DatasetDTO(id, name, columns, rows, format, origin). |  | Pendiente |  |  |  |
| A5.5 | DbConnectionDTO (password solo de escritura), DbConnectionTestDTO, DataImportQueryDTO. |  | Pendiente |  |  |  |

### A6 · Pipelines, escenarios y archivo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A6.1 | PipelineStepDTO(kind: LOAD/RPN/ALGORITHM/SAVE, operation, params) y PipelineDTO(id, name, projectId, steps,… |  | Pendiente |  |  |  |
| A6.2 | PipelineVersionDTO, CurrentVersionDTO, BatchConfigDTO(chunkSize, parallelism). |  | Pendiente |  |  |  |
| A6.3 | ScenarioDTO(…, stackSnapshot, parameters, golden), ScenarioEntityDTO, ScenarioRunDTO. |  | Pendiente |  |  |  |
| A6.4 | ScenarioCompareDTO(runA, runB, equal, differences, tolerance). |  | Pendiente |  |  |  |
| A6.5 | ArchiveDTO, RollbackDTO. |  | Pendiente |  |  |  |

### A7 · Streaming y alertas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A7.1 | WindowSpecDTO(type, unit, size, slide). |  | Pendiente |  |  |  |
| A7.2 | StreamDTO(name, sourceType, window, pipelineId, retention, state). |  | Pendiente |  |  |  |
| A7.3 | StreamEventBatchDTO(points[{timestamp, values}]) y StreamResultDTO. |  | Pendiente |  |  |  |
| A7.4 | AlertRuleDTO, AlertDTO. |  | Pendiente |  |  |  |

### A8 · Modelos y ModelForge

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A8.1 | ModelDTO(id, name, origin: MERLABCORE/MODELFORGE, algorithm, status, metrics) y ModelFileDTO. |  | Pendiente |  |  |  |
| A8.2 | TrainRequestDTO(datasetRef, algorithm, params). |  | Pendiente |  |  |  |
| A8.3 | PredictRequestDTO(inputs) y PredictResultDTO(outputs, modelVersion, latencyMs). |  | Pendiente |  |  |  |
| A8.4 | ModelForgeStatusDTO(installed, reachable, version, queue). |  | Pendiente |  |  |  |

### A9 · PegasusRAG

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A9.1 | RagRequestDTO(useCaseId, sessionId, userInput, orchestrationMode). |  | Pendiente |  |  |  |
| A9.2 | RagResponseDTO + RagArtifactDTO, RagErrorDTO, RagTraceDTO, RagTraceEventDTO, RagAuditDTO. |  | Pendiente |  |  |  |
| A9.3 | UseCaseDTO, FlowListDTO. |  | Pendiente |  |  |  |
| A9.4 | ConversationDTO, ConversationMessageDTO (lista y mensajes). |  | Pendiente |  |  |  |
| A9.5 | RunLogDTO, RunLogEntryDTO, RunLogAnalysisDTO. |  | Pendiente |  |  |  |
| A9.6 | RagIndexingDTO, RagExportDTO(format: JSON/CSV, target). |  | Pendiente |  |  |  |

### A10 · Administración y plugins

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A10.1 | ComponentDTO (lista de componentes) y MetricsDTO. |  | Pendiente |  |  |  |
| A10.2 | PluginDTO, PluginRegisterDTO. |  | Pendiente |  |  |  |

## Grupo B · Infraestructura web

### B1 · Aplicación JAX-RS

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B1.1 | StackflowApplication (@ApplicationPath("api/v1"), una sola) en host-wildfly. |  | Pendiente |  |  |  |
| B1.2 | JacksonConfig (ContextResolver<ObjectMapper>): records, NON_NULL. |  | Pendiente |  |  |  |

### B2 · Modelo de errores

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B2.1 | ErrorCode (con estado HTTP; incluye ENGINE_NOT_INSTALLED, CAPABILITY_NOT_SUPPORTED, ALGORITHM_NOT_EXECUTABL… |  | Pendiente |  |  |  |
| B2.2 | Excepciones StackflowException y subclases (NotFound, Conflict, Validation, JobNotFinished, EngineNotInstal… |  | Pendiente |  |  |  |
| B2.3 | ErrorDTOFactory (Java puro, política de detalle dev/prod). |  | Pendiente |  |  |  |
| B2.4 | StackflowExceptionMapper, ConstraintViolationExceptionMapper, WebApplicationExceptionMapper. |  | Pendiente |  |  |  |

### B3 · Filtros

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B3.1 | CorrelationIdFilter (header, MDC, respuesta). |  | Pendiente |  |  |  |
| B3.2 | RequestLoggingFilter (sin cuerpos ni secretos). |  | Pendiente |  |  |  |
| B3.3 | CorsFilter (FlowScope dev http://localhost:5173 por defecto). |  | Pendiente |  |  |  |

### B4 · Health, info y OpenAPI

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B4.1 | Crear stackflow-runtime: EngineHealth, HealthState (en domain) y MonitorServiceLocal/MonitorServiceBean.hea… |  | Pendiente |  |  |  |
| B4.2 | HealthResource (/health/live, /health/ready) e InfoResource (/info). |  | Pendiente |  |  |  |
| B4.3 | Spike: MicroProfile OpenAPI de WildFly frente a swagger-core. | Spike | Pendiente |  |  |  |
| B4.4 | Aplicar la decisión: anotaciones OpenAPI y Swagger UI. |  | Pendiente |  |  |  |

### B5 · ITs base

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B5.1 | HealthIT. |  | Pendiente |  |  |  |
| B5.2 | ErrorFormatIT (404 y JSON inválido → ErrorDTO con correlationId). |  | Pendiente |  |  |  |

## Grupo C · Storage, seguridad y administración

### C1 · Base de datos y migraciones

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C1.1 | Crear stackflow-storage; Flyway ejecutado por MigrationStartupBean (@Singleton @Startup). |  | Pendiente |  |  |  |
| C1.2 | V001__baseline.sql desde docs/v1/sqlscript_stackflowdb.sql (InnoDB, utf8mb4, FKs, índices; baselineOnMigrate). |  | Pendiente |  |  |  |
| C1.3 | V002__jobs.sql (job_queue ampliada + job_events). |  | Pendiente |  |  |  |
| C1.4 | V003__security.sql (api_keys + roles ADMIN, ANALYST, VIEWER, AUDITOR). |  | Pendiente |  |  |  |
| C1.5 | V004__files.sql (stored_files). |  | Pendiente |  |  |  |
| C1.6 | persistence.xml (StackflowPU) + DatabaseHealthBean en MonitorServiceBean. |  | Pendiente |  |  |  |
| C1.7 | Admin inicial desde STACKFLOW_ADMIN_PASSWORD (AdminBootstrapBean). | 👤 | Pendiente |  |  |  |

### C2 · Usuarios y roles

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C2.1 | Entidades Users, Roles, UserRoles; UserDaoLocal/UserDaoBean, RoleDaoLocal/RoleDaoBean. |  | Pendiente |  |  |  |
| C2.2 | Crear stackflow-security; UserServiceLocal/UserServiceBean (BCrypt; 409 por duplicados). |  | Pendiente |  |  |  |
| C2.3 | UserMapper + UserResource (/users CRUD, /users/{id}/roles). |  | Pendiente |  |  |  |

### C3 · Autenticación

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C3.1 | JwtServiceBean (HS256, secreto y caducidad de la config). |  | Pendiente |  |  |  |
| C3.2 | AuthResource (POST /auth/login, GET /auth/me). |  | Pendiente |  |  |  |
| C3.3 | AuthFilter (Bearer o X-API-Key → SecurityContext); rutas públicas: health, login, openapi. |  | Pendiente |  |  |  |
| C3.4 | @RolesAllowed activo y aplicado a los resources existentes. |  | Pendiente |  |  |  |
| C3.5 | API keys: entidad ApiKeys, DAO, ApiKeyServiceBean (hash SHA-256) y ApiKeyResource. |  | Pendiente |  |  |  |

### C4 · Ajustes y proyectos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C4.1 | Ajustes: entidad, DAO, UserSettingServiceBean, /users/me/settings. |  | Pendiente |  |  |  |
| C4.2 | Crear stackflow-application; proyectos: entidad, DAO, ProjectServiceBean, ProjectResource. |  | Pendiente |  |  |  |
| C4.3 | Miembros /projects/{id}/members. |  | Pendiente |  |  |  |
| C4.4 | ProjectAccessPolicy (Java puro) reutilizada por F, G y H. |  | Pendiente |  |  |  |

### C5 · Configuración e historial

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C5.1 | ConfigProvider (env/sistema > tabla config > defaults) + ConfigKeys + ConfigServiceBean con caché y reload. |  | Pendiente |  |  |  |
| C5.2 | ConfigResource (/admin/config, claves secretas ocultas). |  | Pendiente |  |  |  |
| C5.3 | Historial: evento CDI EntityChangedEvent → HistoryServiceBean (@Observes) → tabla history. |  | Pendiente |  |  |  |
| C5.4 | HistoryResource (/history, AUDITOR). |  | Pendiente |  |  |  |

## Grupo D · Jobs: Orchestrator y Worker

### D1 · Dominio de jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D1.1 | JobId, JobType (PIPELINE, ALGORITHM, EXAMPLE, SCENARIO, SCENARIO_REGRESSION, DATA_IMPORT, POST_PROCESS, PEG… |  | Pendiente |  |  |  |
| D1.2 | Job, JobEvent, JobResult, JobQuery, JobContext (interfaz). |  | Pendiente |  |  |  |

### D2 · Persistencia

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D2.1 | Entidades JobQueue (@Version, event_seq) y JobEvents. |  | Pendiente |  |  |  |
| D2.2 | JobQueueDaoLocal/JobQueueDaoBean (transiciones válidas, appendEvent atómico, findByStatus). |  | Pendiente |  |  |  |

### D3 · Ejecución

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D3.1 | Crear stackflow-jobs; JobHandler + DefaultJobContext (Java puro). |  | Pendiente |  |  |  |
| D3.2 | WorkerServiceBean.execute (@Asynchronous): elige el handler, gestiona los estados y los eventos. |  | Pendiente |  |  |  |
| D3.3 | SerialExecutionBean (@Singleton @Lock(WRITE)) para el trabajo no paralelizable. |  | Pendiente |  |  |  |
| D3.4 | OrchestratorServiceBean: scheduleJob, cancel (CANCELLING), triggerEvent. |  | Pendiente |  |  |  |
| D3.5 | Timeouts y reintentos por tipo (config). |  | Pendiente |  |  |  |
| D3.6 | JobRecoveryBean (@Startup): huérfanos → FAILED SERVER_RESTART. |  | Pendiente |  |  |  |
| D3.7 | TestEchoJobHandler (solo con stackflow.jobs.testEcho=true). |  | Pendiente |  |  |  |

### D4 · REST de jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D4.1 | JobQueueServiceBean (get, list, events, result 409) con control de acceso. |  | Pendiente |  |  |  |
| D4.2 | JobMapper + JobResource (POST 202, GET, events, result, DELETE). |  | Pendiente |  |  |  |

### D5 · Eventos en tiempo real

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D5.1 | JobEventBus (Java puro, suscriptores) + JobSocketEndpoint (token en el handshake). |  | Pendiente |  |  |  |

### D6 · Post-proceso y schedules

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D6.1 | Post-proceso: entidad, DAO, PostProcessServiceBean (@Observes JobFinishedEvent) y /jobs/{id}/post-process-t… |  | Pendiente |  |  |  |
| D6.2 | Schedules: entidad, DAO y validación de cron (cron-utils). |  | Pendiente |  |  |  |
| D6.3 | ScheduleServiceBean con TimerService (timers programáticos persistentes), sin solapamientos. |  | Pendiente |  |  |  |
| D6.4 | ScheduleResource (CRUD, pause, resume). |  | Pendiente |  |  |  |

## Grupo E · MerLabCore, motor RPN y registry

### E1 · Integración de MerLabCore

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E1.1 | mvn install de MerLabCore (sin cambios) y anotar la versión. | 👤 | Pendiente |  |  |  |
| E1.2 | Crear stackflow-adapter-merlabcore; dependencia con exclusiones (JavaFX, XChart, waffle-jna, driver MariaDB… |  | Pendiente |  |  |  |
| E1.3 | Spike: inventario de RPNEngine/RPNOperationProfiles (tokens, aridad, categorías; cuáles tienen efectos secu… | Spike | Pendiente |  |  |  |
| E1.4 | StackElement, ElementType, OperationDescriptor, OperationSource (domain) y MerLabCorePort (ports). |  | Pendiente |  |  |  |
| E1.5 | MerLabCoreAdapter (Java puro) + producer CDI + health(); integrar en MonitorServiceBean. |  | Pendiente |  |  |  |

### E2 · Motor RPN y sesiones

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E2.1 | Crear stackflow-rpn; StackElementCodec (StackElement ↔ objetos de MerLabCore, en el adaptador) con tests de… |  | Pendiente |  |  |  |
| E2.2 | StackSession (Java puro): push, pop, peek, snapshot, profundidad máxima. |  | Pendiente |  |  |  |
| E2.3 | MerLabCoreAdapter.applyRpn (vía RPNEngine, bloqueando operaciones con efectos secundarios). |  | Pendiente |  |  |  |
| E2.4 | RPNStackServiceBean: sesiones en memoria con TTL y límite por usuario; evaluate(expression) con RPNParser. |  | Pendiente |  |  |  |
| E2.5 | StackResource: /stack/evaluate y /stack/sessions… (crear, push, pop, apply, snapshot, borrar). |  | Pendiente |  |  |  |

### E3 · Registry de operaciones

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E3.1 | Crear stackflow-registry; OperationRegistryBean (@Singleton @Startup): carga las operaciones RPN y los algo… |  | Pendiente |  |  |  |
| E3.2 | OperationResource: GET /operations, GET /operations/{token}. |  | Pendiente |  |  |  |
| E3.3 | POST /operations/{token}/execute (entradas en línea o refs; respuesta con salidas). |  | Pendiente |  |  |  |
| E3.4 | Recuentos en /info (operaciones RPN, algoritmos). |  | Pendiente |  |  |  |

### E4 · Algoritmos ML y ejemplos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E4.1 | MerLabCoreAdapter.listAlgorithms / runAlgorithm (lectura de AlgorithmCatalog; ExampleRunner en SerialExecut… |  | Pendiente |  |  |  |
| E4.2 | MLCoreServiceBean + AlgorithmJobHandler y ExampleJobHandler. |  | Pendiente |  |  |  |
| E4.3 | AlgorithmResource (/ml/algorithms…, /ml/examples…, runs 202 o mode=sync). |  | Pendiente |  |  |  |
| E4.4 | IT de paridad con cada fila MerLabCore de RU §5.6. |  | Pendiente |  |  |  |

## Grupo F · Archivos y datos

### F1 · Archivos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F1.1 | StoredFiles (entidad) + StoredFileDaoBean. |  | Pendiente |  |  |  |
| F1.2 | FileStorageServiceBean: disco (stackflow.storage.dir), SHA-256, límites de tamaño, sin rutas peligrosas. |  | Pendiente |  |  |  |
| F1.3 | FileResource: POST /files (multipart), GET /files, /files/{id}, /files/{id}/content, DELETE. |  | Pendiente |  |  |  |

### F2 · Señales y datasets

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F2.1 | Entidades y DAOs de señales y datos de señal. |  | Pendiente |  |  |  |
| F2.2 | Entidades y DAOs de grupos y datasets. |  | Pendiente |  |  |  |
| F2.3 | DataManagerServiceBean.load/save (refs signal:, dataset:, file:) con lectura por chunks (keyset) y Resource… |  | Pendiente |  |  |  |
| F2.4 | SignalResource, SignalGroupResource, DatasetResource (CRUD + datos paginados). |  | Pendiente |  |  |  |

### F3 · Importadores

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F3.1 | FileImporter + WavImporter (javax.sound.sampled; mono/estéreo, sampleRate en meta). |  | Pendiente |  |  |  |
| F3.2 | CsvImporter y JsonImporter (con los loaders de MerLabCore vía MerLabCorePort si aplica). |  | Pendiente |  |  |  |
| F3.3 | OhlcvImporter (timestamp, open, high, low, close, volume → dataset + una señal por columna). |  | Pendiente |  |  |  |
| F3.4 | Spike: HDF5 (HDF5DataLoader de MerLabCore) e imágenes (qué operaciones de imagen ofrece MerLabCore). | Spike | Pendiente |  |  |  |
| F3.5 | ImageImporter y HDF5 según el spike. |  | Pendiente |  |  |  |
| F3.6 | POST /signals/import, POST /datasets/import (job DATA_IMPORT si es grande) y GET /datasets/{id}/export. |  | Pendiente |  |  |  |

### F4 · BD externas (DBAdapter v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F4.1 | SecretCipher (Java puro, AES-GCM, clave de la config). |  | Pendiente |  |  |  |
| F4.2 | DbConnections: entidad, DAO, DbConnectionServiceBean, DbConnectionResource (sin devolver la contraseña). |  | Pendiente |  |  |  |
| F4.3 | POST /db-connections/{id}/test (MariaDB, PostgreSQL). |  | Pendiente |  |  |  |
| F4.4 | Importación por consulta (SELECT solamente, por chunks) como job DATA_IMPORT. |  | Pendiente |  |  |  |

## Grupo G · Pipelines, batch, escenarios y archivo

### G1 · Pipelines

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G1.1 | Crear stackflow-pipeline; PipelineDefinition, PipelineStep, StepKind (domain) + PipelineValidator (Java pur… |  | Pendiente |  |  |  |
| G1.2 | Entidades y DAOs Pipelines, PipelineSteps, PipelineVersions, PipelineCurrentVersion. |  | Pendiente |  |  |  |
| G1.3 | PipelineServiceBean (CRUD, últimas N versiones, versión actual). |  | Pendiente |  |  |  |
| G1.4 | PipelineResource (CRUD, steps, versions, current-version). |  | Pendiente |  |  |  |

### G2 · Ejecución de pipelines

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G2.1 | PipelineRunner (Java puro): ejecuta los pasos sobre una StackSession con MerLabCorePort, emitiendo eventos… |  | Pendiente |  |  |  |
| G2.2 | PipelineExecutorBean (LOAD/SAVE con DataManagerServiceBean) + PipelineJobHandler. |  | Pendiente |  |  |  |
| G2.3 | POST /pipelines/{id}/execute (alias /runs) → 202 + eventos por WebSocket. |  | Pendiente |  |  |  |

### G3 · Batch (BatchProcessor + Worker v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G3.1 | ChunkPlanner (Java puro: tamaño, solapamiento para filtros). |  | Pendiente |  |  |  |
| G3.2 | BatchProcessorServiceBean: reparte chunks a WorkerServiceBean (@Asynchronous), une resultados y aplica la p… |  | Pendiente |  |  |  |
| G3.3 | OrchestratorServiceBean.runBatch + BatchConfigDTO en /pipelines/{id}/execute. |  | Pendiente |  |  |  |

### G4 · Escenarios (ScenarioManager v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G4.1 | Entidades y DAOs Scenarios, ScenarioEntities, ScenarioRuns. |  | Pendiente |  |  |  |
| G4.2 | ScenarioManagerBean (snapshot de stack + parámetros + entidades; cargar; listar) + ScenarioJobHandler. |  | Pendiente |  |  |  |
| G4.3 | ScenarioComparator (Java puro, tolerancia) + golden + job SCENARIO_REGRESSION. |  | Pendiente |  |  |  |
| G4.4 | ScenarioResource (CRUD, runs, compare, golden). |  | Pendiente |  |  |  |

### G5 · Archivo (Archiving v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G5.1 | V0xx__archives.sql, entidad Archives, DAO. |  | Pendiente |  |  |  |
| G5.2 | ArchivingServiceBean (versiones de resultados, escenarios y pipelines; retención). |  | Pendiente |  |  |  |
| G5.3 | ArchiveResource (list, get, rollback). |  | Pendiente |  |  |  |

## Grupo U · Primeras aplicaciones: audio y trading

### U1 · Audio

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| U1.1 | Spike: qué operaciones de audio ofrece MerLabCore hoy (normalize, filtros, FFT, ventanas, detección de pico… | 👤 Spike | Pendiente |  |  |  |
| U1.2 | Pipeline de ejemplo versionado (docs/v2/examples/audio-spectrum.json) y WAV de prueba pequeño. |  | Pendiente |  |  |  |
| U1.3 | IT extremo a extremo: subir WAV → importar → ejecutar → descargar el espectro. |  | Pendiente |  |  |  |
| U1.4 | Guía corta "audio con StackFlow" (curl/Postman). |  | Pendiente |  |  |  |

### U2 · Trading

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| U2.1 | Spike: indicadores disponibles en MerLabCore (ma, wma, deriv, stddev…) y algoritmos ML útiles; lista de los… | 👤 Spike | Pendiente |  |  |  |
| U2.2 | Pipeline de ejemplo (docs/v2/examples/trading-ma-ml.json) y CSV OHLCV de prueba (datos sintéticos o públicos). |  | Pendiente |  |  |  |
| U2.3 | IT extremo a extremo: importar OHLCV → ejecutar → señal de predicción. |  | Pendiente |  |  |  |
| U2.4 | Escenario golden del pipeline de trading (regresión). |  | Pendiente |  |  |  |
| U2.5 | Guía corta "trading con StackFlow". |  | Pendiente |  |  |  |

## Grupo I · Streaming y alertas

### I1 · Streams

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I1.1 | V0xx__streams_alerts.sql; entidades y DAOs Streams, AlertRules, AlertEvents. |  | Pendiente |  |  |  |
| I1.2 | Buffer (Java puro, acotado, política de descarte) y WindowAssembler (tumbling/sliding, por cantidad o tiempo). |  | Pendiente |  |  |  |
| I1.3 | StreamProcessorServiceBean: ejecuta el pipeline por ventana (@Asynchronous); estados CREATED, RUNNING, STOP… |  | Pendiente |  |  |  |
| I1.4 | StreamResource (CRUD, start, stop). |  | Pendiente |  |  |  |

### I2 · Ingesta

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I2.1 | POST /streams/{id}/events (lotes; API key permitida). |  | Pendiente |  |  |  |
| I2.2 | StreamSocketEndpoint (/ws/streams/{id}/ingest y /results). |  | Pendiente |  |  |  |
| I2.3 | Cola JMS en WildFly + SignalProcessorMDB (nombre v1, reescrito). | 👤 | Pendiente |  |  |  |

### I3 · Alertas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I3.1 | AlertRuleEvaluator (Java puro: GT, LT, RATE). |  | Pendiente |  |  |  |
| I3.2 | AlertServiceBean + AlertResource + AlertSocketEndpoint (/ws/alerts, ack). |  | Pendiente |  |  |  |

### I4 · Fuentes externas (opcional)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I4.1 | StreamSourcePort (ports). |  | Pendiente |  |  |  |
| I4.2 | Spike: fuente de trading en vivo (JForex SDK, proxy REST/WS u otra); decidir. | Spike | Pendiente |  |  |  |
| I4.3 | Adaptador elegido en un módulo con perfil propio. |  | Pendiente |  |  |  |
| I4.4 | Kafka / RabbitMQ (perfiles kafka / rabbit), si se necesitan. |  | Pendiente |  |  |  |

## Grupo H · Modelos: inferencia local y ModelForge remoto

### H1 · Modelos y archivos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H1.1 | Entidades y DAOs Models, ModelFiles (archivos en FileStorageServiceBean). |  | Pendiente |  |  |  |
| H1.2 | ModelServiceBean + ModelResource (CRUD, archivos). |  | Pendiente |  |  |  |

### H2 · Inferencia local (MerLabCore)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H2.1 | Spike: qué algoritmos de MerLabCore permiten guardar/cargar un modelo entrenado y predecir. | Spike | Pendiente |  |  |  |
| H2.2 | MLCoreServiceBean.predictLocal + caché LRU con límite de memoria. |  | Pendiente |  |  |  |
| H2.3 | POST /models/{id}/predict (sync o job PREDICT). |  | Pendiente |  |  |  |

### H3 · ModelForge remoto (Mac mini)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H3.1 | ModelForgePort (ports) + UnavailableModelForge (por defecto, 503 ENGINE_NOT_INSTALLED). |  | Pendiente |  |  |  |
| H3.2 | Spike: contrato de la API de ModelForge con el usuario (endpoints, autenticación, formatos); se documenta e… | 👤 Spike | Pendiente |  |  |  |
| H3.3 | Crear stackflow-adapter-modelforge (perfil modelforge): ModelForgeRemoteAdapter (cliente HTTP con timeouts,… |  | Pendiente |  |  |  |
| H3.4 | ModelForgeTrainJobHandler (envía la orden, consulta el estado y descarga el modelo al terminar) + POST /mod… |  | Pendiente |  |  |  |
| H3.5 | Prueba real contra ModelForge en el Mac mini (cuando exista). | 👤 | Pendiente |  |  |  |

## Grupo J · PegasusRAG (opcional y separado)

### J1 · API Pegasus sin Pegasus

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J1.1 | PegasusRagPort + dominio RAG (RagQuery, RagResult, ConversationInfo…). |  | Pendiente |  |  |  |
| J1.2 | UnavailablePegasusRag + PegasusRagServiceBean + guarda de jobs PEGASUS_*. |  | Pendiente |  |  |  |
| J1.3 | PegasusRagMapper + todos los resources RAG (query, usecases, flows, conversations ×4, scenarios, run-logs ×… |  | Pendiente |  |  |  |
| J1.4 | Handlers PegasusQueryJobHandler, PegasusScenarioJobHandler, PegasusIndexingJobHandler. |  | Pendiente |  |  |  |
| J1.5 | IT sin Pegasus: todo /rag/* → 503 ENGINE_NOT_INSTALLED; /health/ready UP. |  | Pendiente |  |  |  |

### J2 · Perfil `pegasus` y arranque

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J2.1 | mvn install de PegasusRAG (sin cambios). | 👤 | Pendiente |  |  |  |
| J2.2 | Crear stackflow-adapter-pegasus (perfil pegasus): excluir slf4j-simple; jboss-deployment-structure.xml. |  | Pendiente |  |  |  |
| J2.3 | PegasusServerConfig (URL de Ollama en el Mac mini, vector store, memoria, perfiles de casos de uso). |  | Pendiente |  |  |  |
| J2.4 | PegasusRuntimeProducer (RagApplicationBuilder, adaptadores de la librería, UseCaseRegistry) + RuntimeState… |  | Pendiente |  |  |  |
| J2.5 | PegasusRagAdapter (@Alternative @Priority): health, use cases y flows. |  | Pendiente |  |  |  |

### J3 · Consultas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J3.1 | PegasusResultMapper y decisión O1 (alias de orchestrationMode). | 👤 | Pendiente |  |  |  |
| J3.2 | CurrentJobContext, StackflowObservabilityBridge, StackflowAuditSink. |  | Pendiente |  |  |  |
| J3.3 | FlowUseCaseExecutor + RagUseCaseDispatcher. |  | Pendiente |  |  |  |
| J3.4 | Query extremo a extremo con el LLM del Mac mini (200, 422, 503, 504). | 👤 | Pendiente |  |  |  |

### J4 · Casos de uso, memoria, logs e indexado

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J4.1 | Spike: inventario useCaseId → Runner (lectura del miniserver, sin tocarlo). | Spike | Pendiente |  |  |  |
| J4.2 | Executors por familia (document, exegesis, music, diagnostics, technical scenarios) + concurrencia. |  | Pendiente |  |  |  |
| J4.3 | Conversaciones (MemoryPort; listado por Redis SCAN,  confirmar prefijo) y run-logs completos. | 👤 | Pendiente |  |  |  |
| J4.4 | Spike + implementación del indexado (PEGASUS_INDEXING). | Spike | Pendiente |  |  |  |

### J5 · Exportar resultados al stack (puente S5)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J5.1 | RagResultExporter (Java puro: RagResult → JSON/CSV tabular). |  | Pendiente |  |  |  |
| J5.2 | POST /rag/runs/{jobId}/export → DataManagerServiceBean.export → dataset. |  | Pendiente |  |  |  |
| J5.3 | IT: exportar → pipeline con LOAD dataset:…. |  | Pendiente |  |  |  |

### J6 · Paridad y regresión

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J6.1 | IT por cada fila Pegasus de RU §5.6 (-Ppegasus). |  | Pendiente |  |  |  |
| J6.2 | NoPegasusIT con -P!pegasus (sin regresión). |  | Pendiente |  |  |  |

## Grupo K · Observabilidad y operación

### K1 · Métricas y Monitor

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K1.1 | Spike: métricas en WildFly 36 (Micrometer/Prometheus). | Spike | Pendiente |  |  |  |
| K1.2 | MonitorServiceBean: jobs, batch, streams, sesiones de stack, JVM. |  | Pendiente |  |  |  |
| K1.3 | GET /admin/metrics + Prometheus. |  | Pendiente |  |  |  |
| K1.4 | Alertas de sistema (memoria, fallos, colas) → AlertServiceBean. |  | Pendiente |  |  |  |

### K2 · Administración y logs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K2.1 | GET /admin/components (MerLabCore, Pegasus, ModelForge, BD, almacenamiento, JMS). |  | Pendiente |  |  |  |
| K2.2 | Logging JSON en WildFly (CLI) + MDC con correlationId/jobId en los hilos de jobs. |  | Pendiente |  |  |  |
| K2.3 | Eventos para FlowScope (estados de la runtime, resultados, métricas) documentados. |  | Pendiente |  |  |  |

## Grupo L · Plugins (PluginManager v1)

### L1 · Plugins

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| L1.1 | Módulo stackflow-plugin-api: StackflowPlugin, OperationPlugin, ImporterPlugin, PluginContext. |  | Pendiente |  |  |  |
| L1.2 | V0xx__plugins.sql + registro (checksum, estado). |  | Pendiente |  |  |  |
| L1.3 | PluginManagerServiceBean: carga aislada desde stackflow.plugins.dir, enable/disable/unload, solo checksums… |  | Pendiente |  |  |  |
| L1.4 | Las OperationPlugin aparecen en /operations (source=PLUGIN) y los ImporterPlugin en los formatos de importa… |  | Pendiente |  |  |  |
| L1.5 | /admin/plugins + plugin de ejemplo stackflow-plugin-example. |  | Pendiente |  |  |  |

## Grupo M · Cierre y release 2.0

### M1 · Pruebas y rendimiento

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M1.1 | Matriz endpoint → IT (desde OpenAPI); cubrir huecos. |  | Pendiente |  |  |  |
| M1.2 | Ejecución completa con y sin pegasus y modelforge. |  | Pendiente |  |  |  |
| M1.3 | Carga: jobs concurrentes, chunks, profundidad del stack, throughput de streams; límites recomendados. |  | Pendiente |  |  |  |

### M2 · Documentación y seguridad

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M2.1 | Colección Postman v2 + guía de API generada desde OpenAPI. |  | Pendiente |  |  |  |
| M2.2 | Guía de instalación (NetBeans, WildFly, MariaDB, perfiles pegasus/modelforge, Mac mini). |  | Pendiente |  |  |  |
| M2.3 | Revisión de seguridad (autenticación en todo, límites de entrada y archivos, secretos fuera de logs). |  | Pendiente |  |  |  |

### M3 · Release

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M3.1 | Versión 2.0.0, README y CHANGELOG. |  | Pendiente |  |  |  |
| M3.2 | Tag v2.0.0 y push (con aprobación). | 👤 | Pendiente |  |  |  |
| M3.3 | Rama por defecto en GitHub. | 👤 | Pendiente |  |  |  |

## Grupo N · Merlynix (después de 2.0)

### N1 · Diseño del lenguaje

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| N1.1 | Spike: requisitos de Merlynix a partir de los pipelines reales (U1, U2) y del repo merlynix; relación con R… | 👤 Spike | Pendiente |  |  |  |
| N1.2 | Gramática y semántica (pipelines, reglas, variables, control de flujo). | 👤 | Pendiente |  |  |  |

### N2 · Integración

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| N2.1 | MerlynixPort + stackflow-adapter-merlynix (parser/intérprete en Java puro, en su propio proyecto o módulo). |  | Pendiente |  |  |  |
| N2.2 | Programas Merlynix como pipelines (traducción a PipelineDefinition) y como jobs. |  | Pendiente |  |  |  |
| N2.3 | Endpoints /merlynix/programs (CRUD) y /merlynix/run. |  | Pendiente |  |  |  |
| N2.4 | Operaciones Merlynix en el registry (source=MERLYNIX). |  | Pendiente |  |  |  |
