# StackFlowServer v2: tracking

Estado de todos los milestones y micropasos del [ROADMAP.md](ROADMAP.md). Lo actualiza el agente al terminar cada micropaso (ver [README.md](README.md)).

**Estados:** `Pendiente` · `En proceso` · `Terminado` · `Bloqueado` (con motivo en Notas). 👤 = requiere al usuario. Spike = solo nota de decisión.

## Paso actual

| Campo | Valor |
|---|---|
| Siguiente micropaso | **0.1.1** |
| Último terminado | 0.5.3 |
| Última actualización | 15.10.2026 |

## Resumen por milestone

| Grupo | Milestone | Título | Pasos | Estado |
|---|---|---|---|---|
| 0 | 0.1 | Rama v2 y retirada del código v1 | 1/5 | En proceso |
| 0 | 0.2 | Proyecto Maven multi-módulo | 0/8 | Pendiente |
| 0 | 0.3 | NetBeans y WildFly | 0/5 | Pendiente |
| 0 | 0.4 | Guardarraíles de calidad | 0/4 | Pendiente |
| 0 | 0.5 | Documentación de trabajo | 3/3 | Terminado |
| A | A0 | Soporte de pruebas para DTOs | 0/2 | Pendiente |
| A | A1 | DTOs comunes | 0/5 | Pendiente |
| A | A2 | Auth, usuarios, ajustes, proyectos, config e historial | 0/6 | Pendiente |
| A | A3 | Jobs, schedules y post-proceso | 0/6 | Pendiente |
| A | A4 | ML (MerLabCore) y stack RPN | 0/6 | Pendiente |
| A | A5 | Datos, señales y BD externas | 0/6 | Pendiente |
| A | A6 | Pipelines, escenarios y archivo | 0/6 | Pendiente |
| A | A7 | Modelos | 0/4 | Pendiente |
| A | A8 | Streams y alertas | 0/5 | Pendiente |
| A | A9 | RAG | 0/6 | Pendiente |
| A | A10 | Administración, métricas, plugins y scripts | 0/4 | Pendiente |
| B | B1 | Aplicación JAX-RS y JSON | 0/2 | Pendiente |
| B | B2 | Modelo de errores | 0/4 | Pendiente |
| B | B3 | Filtros transversales | 0/3 | Pendiente |
| B | B4 | Health, info y OpenAPI | 0/5 | Pendiente |
| B | B5 | Tests de integración base | 0/2 | Pendiente |
| C | C1 | Base de datos y migraciones | 0/6 | Pendiente |
| C | C2 | Usuarios y roles | 0/5 | Pendiente |
| C | C3 | Autenticación (JWT y API key) | 0/5 | Pendiente |
| C | C4 | Ajustes de usuario y proyectos | 0/4 | Pendiente |
| C | C5 | Configuración global e historial | 0/4 | Pendiente |
| D | D1 | Dominio de jobs | 0/2 | Pendiente |
| D | D2 | Persistencia de jobs | 0/3 | Pendiente |
| D | D3 | Ejecución | 0/7 | Pendiente |
| D | D4 | API REST de jobs | 0/2 | Pendiente |
| D | D5 | Eventos en tiempo real | 0/2 | Pendiente |
| D | D6 | Tareas de post-proceso | 0/3 | Pendiente |
| D | D7 | Schedules (cron) | 0/3 | Pendiente |
| E | E1 | Integración de MerLabCore | 0/4 | Pendiente |
| E | E2 | Catálogo de algoritmos | 0/4 | Pendiente |
| E | E3 | Ejecución de algoritmos y ejemplos | 0/3 | Pendiente |
| E | E4 | Stack RPN | 0/6 | Pendiente |
| E | E5 | Paridad con el miniserver de MerLabCore | 0/1 | Pendiente |
| J | J1 | API RAG desacoplada (sin MerLabRAG) | 0/6 | Pendiente |
| J | J2 | Perfil `rag` y arranque de Pegasus | 0/6 | Pendiente |
| J | J3 | Consultas RAG | 0/4 | Pendiente |
| J | J4 | Casos de uso con Runner propio | 0/5 | Pendiente |
| J | J5 | Memoria de conversación y logs | 0/3 | Pendiente |
| J | J6 | Indexado | 0/2 | Pendiente |
| J | J7 | Paridad y regresión sin RAG | 0/2 | Pendiente |
| F | F1 | Señales y datos | 0/5 | Pendiente |
| F | F2 | Grupos de señales y datasets | 0/2 | Pendiente |
| F | F3 | Procesamiento por chunks | 0/2 | Pendiente |
| F | F4 | BD externas (DBAdapter v1) | 0/5 | Pendiente |
| G | G1 | Pipelines | 0/3 | Pendiente |
| G | G2 | Ejecución batch (BatchProcessor + Workers v1) | 0/3 | Pendiente |
| G | G3 | Escenarios (ScenarioManager v1) | 0/3 | Pendiente |
| G | G4 | Comparación y escenarios golden | 0/3 | Pendiente |
| G | G5 | Archivo (versiones y rollback) | 0/3 | Pendiente |
| H | H1 | Modelos y ficheros | 0/3 | Pendiente |
| H | H2 | Entrenamiento | 0/3 | Pendiente |
| H | H3 | Predicción | 0/2 | Pendiente |
| I | I1 | Definición de streams | 0/3 | Pendiente |
| I | I2 | Buffer, ventanas y StreamProcessor | 0/3 | Pendiente |
| I | I3 | Ingesta | 0/3 | Pendiente |
| I | I4 | Resultados y alertas | 0/3 | Pendiente |
| I | I5 | Fuentes externas (opcional) | 0/4 | Pendiente |
| K | K1 | Métricas | 0/4 | Pendiente |
| K | K2 | Alertas de sistema | 0/1 | Pendiente |
| K | K3 | Administración | 0/2 | Pendiente |
| K | K4 | Logging estructurado | 0/2 | Pendiente |
| L | L1 | Plugins (PluginManager v1) | 0/6 | Pendiente |
| L | L2 | Scripting (Merlynx / MCP) | 0/5 | Pendiente |
| M | M1 | Pruebas extremo a extremo | 0/2 | Pendiente |
| M | M2 | Rendimiento y memoria | 0/2 | Pendiente |
| M | M3 | Documentación y clientes | 0/4 | Pendiente |
| M | M4 | Seguridad final | 0/2 | Pendiente |
| M | M5 | Release 2.0.0 | 0/3 | Pendiente |

Total: 73 milestones, 275 micropasos.

---

## Grupo 0 · Fundamentos

### 0.1 · Rama v2 y retirada del código v1

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.1.1 | Crear el tag v1-legacy sobre master (commit 79d1e53). | 👤 | Pendiente |  |  |  |
| 0.1.2 | Crear la rama v2 desde master en el worktree stackflow-v2. |  | Terminado | 15.10.2026 |  | worktree stackflow-v2 |
| 0.1.3 | En v2: mover la documentación v1 a docs/v1/ (API.md, DATABASE_SCHEMA.md, SETUP_GUIDE.md, Setup Guide.docx,… |  | Pendiente |  |  |  |
| 0.1.4 | Crear .gitignore (target/, nbproject/private/, *.class, *.log, .idea/) y quitar target/ del índice (git rm… |  | Pendiente |  |  |  |
| 0.1.5 | README v2 provisional: qué es, cómo compilar y enlace a docs/v2/. |  | Pendiente |  |  |  |

### 0.2 · Proyecto Maven multi-módulo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.2.1 | POM padre com.merlab:stackflow-parent:2.0.0-SNAPSHOT (pom). |  | Pendiente |  |  |  |
| 0.2.2 | Módulo stackflow-api (jar) con package-info.java. |  | Pendiente |  |  |  |
| 0.2.3 | Módulo stackflow-service (jar), sin dependencia de api. |  | Pendiente |  |  |  |
| 0.2.4 | Módulo stackflow-persistence (jar) → depende de service. |  | Pendiente |  |  |  |
| 0.2.5 | Módulo stackflow-adapter-merlabcore (jar) → service (MerLabCore se añade en E1). |  | Pendiente |  |  |  |
| 0.2.6 | Módulo stackflow-adapter-merlabrag (jar) → service, declarado solo dentro del perfil rag. |  | Pendiente |  |  |  |
| 0.2.7 | Módulo stackflow-web (war, finalName stackflow) → api, service, persistence, adapter-merlabcore; adapter-me… |  | Pendiente |  |  |  |
| 0.2.8 | Build completo en ambos modos. |  | Pendiente |  |  |  |

### 0.3 · NetBeans y WildFly

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.3.1 | Abrir stackflow-v2 en NetBeans como proyecto padre y comprobar los módulos. | 👤 | Pendiente |  |  |  |
| 0.3.2 | Registrar WildFly 36 en NetBeans (puerto HTTP 8090). | 👤 | Pendiente |  |  |  |
| 0.3.3 | Comprobar o crear los DataSources java:/jdbc/StackflowDS (stackflowdb) y java:/jdbc/StackflowTestDS (stackf… | 👤 | Pendiente |  |  |  |
| 0.3.4 | Desplegar el WAR vacío. |  | Pendiente |  |  |  |
| 0.3.5 | Despliegue por Maven con wildfly-maven-plugin (mvn wildfly:deploy) y scripts scripts/build.ps1 y scripts/de… |  | Pendiente |  |  |  |

### 0.4 · Guardarraíles de calidad

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.4.1 | Reglas ArchUnit: service no importa jakarta.ws.rs.., jakarta.persistence.., pegasus.. |  | Pendiente |  |  |  |
| 0.4.2 | Convención de tests: *Test (surefire) y *IT (failsafe, perfil it). |  | Pendiente |  |  |  |
| 0.4.3 | Base Arquillian: dependencias, arquillian.xml (WildFly managed, jbossHome por propiedad), AbstractStackflow… |  | Pendiente |  |  |  |
| 0.4.4 | .editorconfig, formato NetBeans (4 espacios) y plantilla de cabecera de licencia (Apache 2.0, como v1). |  | Pendiente |  |  |  |

### 0.5 · Documentación de trabajo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| 0.5.1 | docs/v2/ con README, ROADMAP, TRACKING, UMSETZUNGSPLAN y design/. |  | Terminado | 15.10.2026 |  |  |
| 0.5.2 | AGENTS.md (Codex) y CLAUDE.md (Claude) en la raíz, con el protocolo ROGER. |  | Terminado | 15.10.2026 |  |  |
| 0.5.3 | Primer commit de la documentación en v2 (solo con aprobación). | 👤 | Terminado | 15.10.2026 | ver git log `[0.5.3]` | aprobado por el usuario |

## Grupo A · Contrato de la API (DTOs)

### A0 · Soporte de pruebas para DTOs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A0.1 | Dependencias de test en stackflow-api: jackson-databind (+ módulo de records), hibernate-validator + expres… |  | Pendiente |  |  |  |
| A0.2 | Utilidades JsonTestSupport.roundTrip(obj, Class) y ValidationTestSupport.violations(obj). |  | Pendiente |  |  |  |

### A1 · DTOs comunes

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A1.1 | ErrorResponse(code, status, message, correlationId, details) y ErrorDetail(field, message). |  | Pendiente |  |  |  |
| A1.2 | HealthResponse(status, components) y ComponentHealth(name, status, detail). |  | Pendiente |  |  |  |
| A1.3 | InfoResponse(version, buildTime, engines: Map, counts: Map). |  | Pendiente |  |  |  |
| A1.4 | PageResponse<T>(items, total, offset, limit) y PageRequestParams (offset ≥ 0, 1 ≤ limit ≤ 500). |  | Pendiente |  |  |  |
| A1.5 | ApiPaths: constantes de todas las rutas del diseño (incluido el Anexo A.3). |  | Pendiente |  |  |  |

### A2 · Auth, usuarios, ajustes, proyectos, config e historial

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A2.1 | LoginRequest, TokenResponse(token, expiresAt, roles), MeResponse. |  | Pendiente |  |  |  |
| A2.2 | ApiKeyCreateRequest(name, expiresAt), ApiKeyResponse(id, name, prefix, createdAt, expiresAt, lastUsedAt), A… |  | Pendiente |  |  |  |
| A2.3 | UserCreateRequest (email, contraseña ≥ 10 caracteres, roles), UserUpdateRequest, UserResponse (sin contrase… |  | Pendiente |  |  |  |
| A2.4 | UserSettingsDto(settings: Map<String,String>). |  | Pendiente |  |  |  |
| A2.5 | ProjectRequest, ProjectResponse, ProjectMemberRequest, ProjectMemberResponse. |  | Pendiente |  |  |  |
| A2.6 | ConfigEntryDto(key, value, description, updatedAt) y HistoryEntryResponse(id, entity, entityId, action, use… |  | Pendiente |  |  |  |

### A3 · Jobs, schedules y post-proceso

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A3.1 | CreateJobRequest(type, payload) (type validado contra la lista de A.4 + §5.5). |  | Pendiente |  |  |  |
| A3.2 | JobResponse, JobListResponse (= PageResponse<JobResponse>). |  | Pendiente |  |  |  |
| A3.3 | JobEventsResponse(jobId, status, nextSequence, events) y JobEventResponse. |  | Pendiente |  |  |  |
| A3.4 | JobResultResponse(jobId, status, result, completedAt) con result polimórfico (Object). |  | Pendiente |  |  |  |
| A3.5 | ScheduleRequest(name, cron, jobType, payload, enabled) y ScheduleResponse(…, nextFireAt, lastRunJobId). |  | Pendiente |  |  |  |
| A3.6 | PostProcessTaskRequest(jobType, payload, onStatus) y PostProcessTaskResponse. |  | Pendiente |  |  |  |

### A4 · ML (MerLabCore) y stack RPN

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A4.1 | AlgorithmResponse (campos de AlgorithmDescriptor) y AlgorithmListResponse. |  | Pendiente |  |  |  |
| A4.2 | ExampleResponse(key, category, description) y ExampleListResponse. |  | Pendiente |  |  |  |
| A4.3 | RunRequest(params, mode: sync/async) y MlRunResponse(success, consoleOutput, chartHtml, metrics). |  | Pendiente |  |  |  |
| A4.4 | RpnRequest(expression, inputs) y RpnResponse(values, size). |  | Pendiente |  |  |  |
| A4.5 | StackOperationResponse(name, arity, description, params). |  | Pendiente |  |  |  |
| A4.6 | StackSessionResponse(id, depth, top, createdAt, expiresAt), StackPushRequest(values / signalId), StackApply… |  | Pendiente |  |  |  |

### A5 · Datos, señales y BD externas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A5.1 | SignalRequest(name, projectId, unit, sampleRate, metadata) y SignalResponse. |  | Pendiente |  |  |  |
| A5.2 | SignalDataPageResponse(signalId, offset, limit, total, values). |  | Pendiente |  |  |  |
| A5.3 | SignalGroupRequest, SignalGroupResponse(…, signalIds). |  | Pendiente |  |  |  |
| A5.4 | DatasetRequest(name, description, location, format) y DatasetResponse. |  | Pendiente |  |  |  |
| A5.5 | DbConnectionRequest(name, type: MARIADB/POSTGRESQL, url, username, password) (password solo de escritura) y… |  | Pendiente |  |  |  |
| A5.6 | DbConnectionTestResponse(ok, latencyMs, message) y DataImportRequest(query, targetSignalName, chunkSize). |  | Pendiente |  |  |  |

### A6 · Pipelines, escenarios y archivo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A6.1 | PipelineRequest(name, description, projectId, steps), PipelineStepDto(order, operation, params) y PipelineR… |  | Pendiente |  |  |  |
| A6.2 | PipelineVersionResponse(version, createdAt, steps) y CurrentVersionRequest(version). |  | Pendiente |  |  |  |
| A6.3 | ScenarioRequest(name, description, stackSnapshot, parameters), ScenarioEntityDto(entityType, entityId) y Sc… |  | Pendiente |  |  |  |
| A6.4 | ScenarioRunResponse(runId, status, results, startedAt, finishedAt). |  | Pendiente |  |  |  |
| A6.5 | ScenarioCompareResponse(runA, runB, equal, differences[{key, a, b, delta}], tolerance). |  | Pendiente |  |  |  |
| A6.6 | ArchiveResponse(id, entityType, entityId, version, createdAt, createdBy) y RollbackResponse. |  | Pendiente |  |  |  |

### A7 · Modelos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A7.1 | ModelRequest(name, algorithm, projectId, description) y ModelResponse(…, status, metrics). |  | Pendiente |  |  |  |
| A7.2 | ModelFileResponse(id, fileName, sizeBytes, sha256, createdAt). |  | Pendiente |  |  |  |
| A7.3 | TrainRequest(datasetId / signalIds, params, mode). |  | Pendiente |  |  |  |
| A7.4 | PredictRequest(inputs, mode) y PredictResponse(outputs, modelVersion, latencyMs). |  | Pendiente |  |  |  |

### A8 · Streams y alertas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A8.1 | WindowSpecDto(type: TUMBLING/SLIDING, unit: COUNT/TIME, size, slide). |  | Pendiente |  |  |  |
| A8.2 | StreamRequest(name, sourceType: REST/WEBSOCKET/JMS/KAFKA/RABBITMQ, window, pipelineId, retention) y StreamR… |  | Pendiente |  |  |  |
| A8.3 | StreamEventRequest(points[{timestamp, values}]) (lote, máx. |  | Pendiente |  |  |  |
| A8.4 | AlertRuleRequest(name, streamId / metric, condition: GT/LT/RATE, threshold, severity) y AlertRuleResponse. |  | Pendiente |  |  |  |
| A8.5 | AlertResponse(id, ruleId, state: OPEN/ACK/CLOSED, value, raisedAt, ackBy). |  | Pendiente |  |  |  |

### A9 · RAG

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A9.1 | QueryRequest(useCaseId, sessionId, userInput, orchestrationMode). |  | Pendiente |  |  |  |
| A9.2 | QueryResponse + ArtifactResponse, QueryErrorResponse, TraceResponse, TraceEventResponse, AuditResponse. |  | Pendiente |  |  |  |
| A9.3 | UseCaseResponse, UseCaseItem, FlowListResponse. |  | Pendiente |  |  |  |
| A9.4 | ConversationResponse, ConversationListResponse, ConversationMessagesResponse, ConversationMessageResponse. |  | Pendiente |  |  |  |
| A9.5 | RunLogListResponse, RunLogResponse, RunLogEntryResponse, RunLogAnalysisResponse. |  | Pendiente |  |  |  |
| A9.6 | IndexingRequest(useCaseId, sources). |  | Pendiente |  |  |  |

### A10 · Administración, métricas, plugins y scripts

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| A10.1 | ComponentsResponse(components[{name, type, state, detail}]). |  | Pendiente |  |  |  |
| A10.2 | MetricsResponse(jvm{heapUsed, heapMax, cpu}, jobs{running, queued, failed24h}, streams{…}, stackSessions). |  | Pendiente |  |  |  |
| A10.3 | PluginResponse(id, name, version, type, state, checksum) y PluginRegisterRequest(fileName, checksum). |  | Pendiente |  |  |  |
| A10.4 | ScriptLanguageResponse(id, description), ScriptRunRequest(language, source, params, mode) (tamaño máx. |  | Pendiente |  |  |  |

## Grupo B · Infraestructura web

### B1 · Aplicación JAX-RS y JSON

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B1.1 | StackflowApplication con @ApplicationPath("api/v1") (una sola Application). |  | Pendiente |  |  |  |
| B1.2 | JacksonConfig (ContextResolver<ObjectMapper>): módulo de records, NON_NULL y fallo con propiedades desconoc… |  | Pendiente |  |  |  |

### B2 · Modelo de errores

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B2.1 | ErrorCode con estado HTTP: todos los códigos de §3.8 + RAG_NOT_INSTALLED, CAPABILITY_NOT_SUPPORTED y ALGORI… |  | Pendiente |  |  |  |
| B2.2 | Jerarquía StackflowException (ValidationFailed, NotFound, Conflict, JobNotFinished, AlgorithmNotExecutable,… |  | Pendiente |  |  |  |
| B2.3 | ErrorDetailPolicy (stackflow.errors.details=dev/prod). |  | Pendiente |  |  |  |
| B2.4 | StackflowExceptionMapper, ConstraintViolationMapper y WebApplicationExceptionMapper (404/405/415 → ErrorRes… |  | Pendiente |  |  |  |

### B3 · Filtros transversales

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B3.1 | CorrelationIdFilter: lee o genera X-Correlation-Id, lo pone en el MDC y en la respuesta. |  | Pendiente |  |  |  |
| B3.2 | RequestLoggingFilter: método, ruta, estado, ms y correlationId (sin cuerpos ni secretos). |  | Pendiente |  |  |  |
| B3.3 | CorsFilter con orígenes configurables (por defecto, FlowScope dev http://localhost:5173). |  | Pendiente |  |  |  |

### B4 · Health, info y OpenAPI

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B4.1 | Dominio de salud: HealthState (UP, DEGRADED, DOWN, NOT_INSTALLED), EngineHealth, SystemHealth, port Databas… |  | Pendiente |  |  |  |
| B4.2 | HealthService (agregación §5.5; NOT_INSTALLED no cuenta como fallo). |  | Pendiente |  |  |  |
| B4.3 | HealthResource (/health/live, /health/ready) e InfoResource (/info, versión del MANIFEST). |  | Pendiente |  |  |  |
| B4.4 | Spike: MicroProfile OpenAPI de WildFly frente a swagger-core. | Spike | Pendiente |  |  |  |
| B4.5 | Aplicar la decisión: anotaciones OpenAPI en resources y DTOs base, y Swagger UI accesible. |  | Pendiente |  |  |  |

### B5 · Tests de integración base

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| B5.1 | HealthIT (live, ready). |  | Pendiente |  |  |  |
| B5.2 | ErrorFormatIT: ruta inexistente → 404 ErrorResponse con correlationId; JSON inválido → 400. |  | Pendiente |  |  |  |

## Grupo C · Persistencia, seguridad y administración

### C1 · Base de datos y migraciones

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C1.1 | Flyway (core + mysql) en stackflow-persistence, ejecutado por un @Singleton @Startup (FlywayMigrator) sobre… |  | Pendiente |  |  |  |
| C1.2 | V001__baseline.sql a partir de docs/v1/sqlscript_stackflowdb.sql: revisar InnoDB, utf8mb4, FKs e índices. |  | Pendiente |  |  |  |
| C1.3 | V002__jobs.sql: ampliar job_queue (§6 + event_seq) y crear job_events. |  | Pendiente |  |  |  |
| C1.4 | V003__security.sql: api_keys; semilla de roles ADMIN, ANALYST, VIEWER y AUDITOR. |  | Pendiente |  |  |  |
| C1.5 | persistence.xml (StackflowPU, JTA, StackflowDS) y DatabaseHealthAdapter (implementa DatabaseHealthPort). |  | Pendiente |  |  |  |
| C1.6 | Usuario administrador inicial: creado al arrancar si no existe ninguno, con contraseña de una variable de e… | 👤 | Pendiente |  |  |  |

### C2 · Usuarios y roles

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C2.1 | Dominio User, Role (enum de 4 roles) y port UserRepository (findById, findByUsername, page, save, delete, e… |  | Pendiente |  |  |  |
| C2.2 | Entidades UserEntity, RoleEntity (+ user_roles), JpaUserRepository y su mapper. |  | Pendiente |  |  |  |
| C2.3 | Port PasswordHasher + BCryptPasswordHasher. |  | Pendiente |  |  |  |
| C2.4 | UserService: crear (hash), actualizar, borrar, paginar, asignar roles; 409 si el usuario o el email ya exis… |  | Pendiente |  |  |  |
| C2.5 | UserApiMapper y UserResource (/users CRUD, /users/{id}/roles). |  | Pendiente |  |  |  |

### C3 · Autenticación (JWT y API key)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C3.1 | JwtService (HS256, emisor, caducidad y secreto de la configuración). |  | Pendiente |  |  |  |
| C3.2 | AuthResource: POST /auth/login y GET /auth/me. |  | Pendiente |  |  |  |
| C3.3 | AuthFilter: Authorization: Bearer o X-API-Key → StackflowPrincipal + SecurityContext; rutas públicas: /heal… |  | Pendiente |  |  |  |
| C3.4 | Activar @RolesAllowed (resteasy.role.based.security=true) y anotar los resources existentes. |  | Pendiente |  |  |  |
| C3.5 | API keys: dominio, entidad (hash SHA-256 + prefijo visible), ApiKeyService y ApiKeyResource (/api-keys). |  | Pendiente |  |  |  |

### C4 · Ajustes de usuario y proyectos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C4.1 | Ajustes: entidad, repositorio, UserSettingsService y GET/PUT /users/me/settings. |  | Pendiente |  |  |  |
| C4.2 | Proyectos: dominio, entidad, repositorio, ProjectService (el owner gestiona; ADMIN todo) y ProjectResource. |  | Pendiente |  |  |  |
| C4.3 | Miembros: GET/POST/DELETE /projects/{id}/members. |  | Pendiente |  |  |  |
| C4.4 | Port ProjectAccessPolicy (¿puede el usuario X leer o escribir en el proyecto Y?), reutilizado por F, G y H. |  | Pendiente |  |  |  |

### C5 · Configuración global e historial

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| C5.1 | Port ConfigProvider con capas: variables de entorno / propiedades del sistema > tabla config > valores por… |  | Pendiente |  |  |  |
| C5.2 | ConfigResource (/admin/config, /admin/config/{key}); claves marcadas como secretas nunca se devuelven. |  | Pendiente |  |  |  |
| C5.3 | Historial: evento CDI EntityChangedEvent, HistoryRecorder (observer → tabla history) y emisión desde los se… |  | Pendiente |  |  |  |
| C5.4 | HistoryResource (/history con filtros y paginación, rol AUDITOR). |  | Pendiente |  |  |  |

## Grupo D · Sistema de jobs (Orchestrator v1)

### D1 · Dominio de jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D1.1 | JobId, JobType (§5.5 + Anexo A.4), ExecutionLane y JobStatus con canTransitionTo (tabla §4.1). |  | Pendiente |  |  |  |
| D1.2 | Job (con transitionTo, lanza ConflictException), JobPayload, JobEvent, JobResult y JobQuery. |  | Pendiente |  |  |  |

### D2 · Persistencia de jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D2.1 | Port JobRepository (§3.4). |  | Pendiente |  |  |  |
| D2.2 | JobEntity (@Version, event_seq), JobEventEntity y JobEntityMapper. |  | Pendiente |  |  |  |
| D2.3 | JpaJobRepository (transiciones válidas, appendEvent con secuencia atómica, findByStatus). |  | Pendiente |  |  |  |

### D3 · Ejecución

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D3.1 | JobHandler, JobContext, DefaultJobContext y JobHandlerRegistry (Instance<JobHandler>; falla si hay tipos du… |  | Pendiente |  |  |  |
| D3.2 | JobEventPublisher (suscriptores en memoria + appendEvent). |  | Pendiente |  |  |  |
| D3.3 | JobExecutor: carril PARALLEL (ManagedExecutorService) y carril SERIAL (un hilo de ManagedThreadFactory); es… |  | Pendiente |  |  |  |
| D3.4 | Cancelación (requestCancel, CANCELLING) y timeouts por tipo (stackflow.jobs.timeout.<TYPE>). |  | Pendiente |  |  |  |
| D3.5 | Reintentos por tipo (stackflow.jobs.retries.<TYPE>, backoff), como el "reintenta jobs si hay fallos" de v1. |  | Pendiente |  |  |  |
| D3.6 | recoverOrphans() al arrancar (@Startup): RUNNING/CANCELLING → FAILED SERVER_RESTART. |  | Pendiente |  |  |  |
| D3.7 | TestEchoJobHandler (TEST_ECHO), activo solo con stackflow.jobs.testEcho=true. |  | Pendiente |  |  |  |

### D4 · API REST de jobs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D4.1 | JobService (submit, get, list, events, result, cancel) con control de acceso (owner o ADMIN). |  | Pendiente |  |  |  |
| D4.2 | JobApiMapper + JobResource (POST 202 + Location; GET list/id; events?after; result 409; DELETE). |  | Pendiente |  |  |  |

### D5 · Eventos en tiempo real

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D5.1 | JobEventsSocket (@ServerEndpoint), autenticación por token en el handshake y suscripción al publisher. |  | Pendiente |  |  |  |
| D5.2 | Límite de mensajes en cola por sesión y cierre limpio al terminar el job. |  | Pendiente |  |  |  |

### D6 · Tareas de post-proceso

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D6.1 | Dominio, entidad y repositorio de PostProcessTask. |  | Pendiente |  |  |  |
| D6.2 | PostProcessService: observa JobFinishedEvent y lanza las tareas que coinciden (onStatus). |  | Pendiente |  |  |  |
| D6.3 | GET/POST /jobs/{id}/post-process-tasks. |  | Pendiente |  |  |  |

### D7 · Schedules (cron)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| D7.1 | Dominio, entidad y repositorio de Schedule; validación de cron (cron-utils). |  | Pendiente |  |  |  |
| D7.2 | ScheduleService con ManagedScheduledExecutorService: calcula el siguiente disparo, no solapa ejecuciones y… |  | Pendiente |  |  |  |
| D7.3 | ScheduleResource (CRUD, pause y resume). |  | Pendiente |  |  |  |

## Grupo E · MerLabCore y stack RPN

### E1 · Integración de MerLabCore

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E1.1 | mvn install de MerLabCore (sin cambiar código) y anotar la versión instalada. | 👤 | Pendiente |  |  |  |
| E1.2 | Dependencia en stackflow-adapter-merlabcore con exclusiones (JavaFX, XChart, waffle-jna, driver MariaDB) y… |  | Pendiente |  |  |  |
| E1.3 | Port MlEnginePort y dominio (AlgorithmInfo, ExampleInfo, MlRunResult, SignalData, EngineHealth). |  | Pendiente |  |  |  |
| E1.4 | MerLabCoreEngineAdapter (esqueleto + health(): el catálogo carga) e integración en HealthService. |  | Pendiente |  |  |  |

### E2 · Catálogo de algoritmos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E2.1 | AlgorithmCatalogReader + MerLabCoreMapper (AlgorithmDescriptor → AlgorithmInfo). |  | Pendiente |  |  |  |
| E2.2 | Lista de ejemplos a partir de los descriptores (exampleClass/cliRoute); ExampleRegistry solo expone texto f… |  | Pendiente |  |  |  |
| E2.3 | MlService (list, filtro por q/status/category, get, components, catalog, examples). |  | Pendiente |  |  |  |
| E2.4 | MlAlgorithmResource y MlExampleResource (GETs) + recuentos en /info. |  | Pendiente |  |  |  |

### E3 · Ejecución de algoritmos y ejemplos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E3.1 | ExampleExecutor (envuelve ExampleRunner.run; carril SERIAL). |  | Pendiente |  |  |  |
| E3.2 | MlAlgorithmJobHandler y MlExampleJobHandler. |  | Pendiente |  |  |  |
| E3.3 | POST /ml/algorithms/{id}/runs y /ml/examples/{key}/runs (202; mode=sync con timeout); 404 y 409. |  | Pendiente |  |  |  |

### E4 · Stack RPN

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E4.1 | Spike: API de rpn / SignalStack / SignalPipelineFacade de MerLabCore (operaciones, aridad, serialización). | Spike | Pendiente |  |  |  |
| E4.2 | StackOperationCatalog + GET /stack/operations. |  | Pendiente |  |  |  |
| E4.3 | RpnEvaluator + POST /stack/evaluate (stateless). |  | Pendiente |  |  |  |
| E4.4 | StackSessionService: sesiones en memoria con TTL, profundidad máxima y máximo de sesiones por usuario (lími… |  | Pendiente |  |  |  |
| E4.5 | Endpoints de sesión: crear, ver, push, pop, apply, snapshot y borrar. |  | Pendiente |  |  |  |
| E4.6 | Snapshot serializable (JSON) del stack, reutilizado por los escenarios (G3). |  | Pendiente |  |  |  |

### E5 · Paridad con el miniserver de MerLabCore

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| E5.1 | Un IT por cada fila MerLabCore de la tabla §5.6. |  | Pendiente |  |  |  |

## Grupo J · RAG (MerLabRAG, opcional)

### J1 · API RAG desacoplada (sin MerLabRAG)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J1.1 | Port RagEnginePort completo (incluido isInstalled()) y dominio RAG (RagQuery, RagResult, RagMode, Conversat… |  | Pendiente |  |  |  |
| J1.2 | UnavailableRagEngine (bean por defecto) y RagNotInstalledException. |  | Pendiente |  |  |  |
| J1.3 | RagService + guarda en JobService para los tipos RAG_*. |  | Pendiente |  |  |  |
| J1.4 | RagApiMapper + todos los resources RAG (query, usecases, flows, conversations ×4, scenarios, run-logs ×4, i… |  | Pendiente |  |  |  |
| J1.5 | Handlers RagQueryJobHandler, RagScenarioJobHandler y RagIndexingJobHandler. |  | Pendiente |  |  |  |
| J1.6 | IT con -P!rag: todo /rag/* → 503 RAG_NOT_INSTALLED; /health/ready UP con merlabrag: NOT_INSTALLED. |  | Pendiente |  |  |  |

### J2 · Perfil `rag` y arranque de Pegasus

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J2.1 | mvn install de MerLabRAG (sin cambiar código) y anotar la versión. | 👤 | Pendiente |  |  |  |
| J2.2 | Dependencias del adaptador (merlab-rag, excluir slf4j-simple) y jboss-deployment-structure.xml (conflictos… |  | Pendiente |  |  |  |
| J2.3 | PegasusServerConfig (desde ConfigProvider). |  | Pendiente |  |  |  |
| J2.4 | PegasusRuntimeProducer: RagApplicationBuilder, Ollama, vector store, memoria, perfiles de casos de uso y fl… |  | Pendiente |  |  |  |
| J2.5 | Sonda periódica de salud y POST /admin/rag/reload. |  | Pendiente |  |  |  |
| J2.6 | MerLabRagEngineAdapter (@Alternative @Priority(100)): health, useCases, flows. |  | Pendiente |  |  |  |

### J3 · Consultas RAG

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J3.1 | PegasusResultMapper (§7.1) y RagMode ↔ OrchestrationMode. |  | Pendiente |  |  |  |
| J3.2 | CurrentJobContext, StackflowObservabilityBridge y StackflowAuditSink. |  | Pendiente |  |  |  |
| J3.3 | FlowUseCaseExecutor y RagUseCaseDispatcher (fallback a defaultFlow). |  | Pendiente |  |  |  |
| J3.4 | Query síncrona extremo a extremo: 200, 422, 503 y 504 según §7.2. |  | Pendiente |  |  |  |

### J4 · Casos de uso con Runner propio

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J4.1 | Spike: inventario de useCaseId → Runner (lectura del cableado del miniserver, sin tocarlo). | Spike | Pendiente |  |  |  |
| J4.2 | DocumentUseCaseExecutor y ExegesisUseCaseExecutor. |  | Pendiente |  |  |  |
| J4.3 | MusicUseCaseExecutor y DiagnosticsUseCaseExecutor. |  | Pendiente |  |  |  |
| J4.4 | TechnicalScenarioExecutor + POST /rag/scenarios/{name}/runs. |  | Pendiente |  |  |  |
| J4.5 | Prueba de concurrencia de Pegasus → decidir el carril de los handlers RAG y anotarlo. |  | Pendiente |  |  |  |

### J5 · Memoria de conversación y logs

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J5.1 | Get, messages y delete vía MemoryPort. |  | Pendiente |  |  |  |
| J5.2 | Confirmar el prefijo de claves de Redis; RedisConversationLister (SCAN) y UnsupportedConversationLister. | 👤 | Pendiente |  |  |  |
| J5.3 | RunLogReader: list, get (completo), raw y analysis. |  | Pendiente |  |  |  |

### J6 · Indexado

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J6.1 | Spike: API de pegasus.runtime.indexing. | Spike | Pendiente |  |  |  |
| J6.2 | Indexado en el adaptador + RagIndexingJobHandler (carril SERIAL). |  | Pendiente |  |  |  |

### J7 · Paridad y regresión sin RAG

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| J7.1 | Un IT por cada fila Pegasus de la tabla §5.6 (-Prag). |  | Pendiente |  |  |  |
| J7.2 | Repetir NoRagIT con -P!rag (no hay regresión). |  | Pendiente |  |  |  |

## Grupo F · Datos, señales y BD externas

### F1 · Señales y datos

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F1.1 | Dominio, entidades y repositorio de Signal y SignalData. |  | Pendiente |  |  |  |
| F1.2 | SignalService (acceso por proyecto, ProjectAccessPolicy). |  | Pendiente |  |  |  |
| F1.3 | SignalResource CRUD. |  | Pendiente |  |  |  |
| F1.4 | Subida de datos JSON y CSV (EntityPart/multipart, límite de tamaño) a /signals/{id}/data. |  | Pendiente |  |  |  |
| F1.5 | Lectura paginada GET /signals/{id}/data?offset&limit. |  | Pendiente |  |  |  |

### F2 · Grupos de señales y datasets

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F2.1 | Grupos: entidad, servicio, CRUD y añadir/quitar señales. |  | Pendiente |  |  |  |
| F2.2 | Datasets: entidad, servicio y CRUD (metadatos y ubicación). |  | Pendiente |  |  |  |

### F3 · Procesamiento por chunks

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F3.1 | Port ChunkReader<T> + implementación JPA con paginación por clave (keyset). |  | Pendiente |  |  |  |
| F3.2 | ChunkConfig (tamaño por defecto y máximo, desde config) y MemoryGuard (límite de elementos en memoria). |  | Pendiente |  |  |  |

### F4 · BD externas (DBAdapter v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| F4.1 | Port SecretCipher (AES-GCM, clave de la configuración) para las contraseñas guardadas. |  | Pendiente |  |  |  |
| F4.2 | DbConnection: dominio, entidad, servicio y CRUD (la respuesta nunca incluye la contraseña). |  | Pendiente |  |  |  |
| F4.3 | POST /db-connections/{id}/test (JDBC con timeout; MariaDB y PostgreSQL). |  | Pendiente |  |  |  |
| F4.4 | Job DATA_IMPORT: solo SELECT, lectura por chunks hacia signal_data. |  | Pendiente |  |  |  |
| F4.5 | Spike: NoSQL (MongoDB) como fuente futura; anotar enfoque. | Spike | Pendiente |  |  |  |

## Grupo G · Pipelines, batch, escenarios y archivo

### G1 · Pipelines

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G1.1 | Dominio y entidades de pipelines, pasos y versiones. |  | Pendiente |  |  |  |
| G1.2 | PipelineService: los pasos se validan contra StackOperationCatalog; se guardan las últimas N versiones (con… |  | Pendiente |  |  |  |
| G1.3 | PipelineResource (CRUD, steps, versions, current-version). |  | Pendiente |  |  |  |

### G2 · Ejecución batch (BatchProcessor + Workers v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G2.1 | PipelineRunner: aplica los pasos (RPN vía MlEnginePort) a un chunk. |  | Pendiente |  |  |  |
| G2.2 | BatchProcessor: divide en chunks, reparte a workers del carril PARALLEL, emite eventos de progreso y aplica… |  | Pendiente |  |  |  |
| G2.3 | MlPipelineJobHandler + POST /pipelines/{id}/runs; el resultado se guarda como señal de salida o JSON. |  | Pendiente |  |  |  |

### G3 · Escenarios (ScenarioManager v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G3.1 | Dominio y entidades. |  | Pendiente |  |  |  |
| G3.2 | ScenarioService: crear un snapshot (stack, parámetros y entidades referenciadas), cargar y listar. |  | Pendiente |  |  |  |
| G3.3 | MlScenarioJobHandler + POST /scenarios/{id}/runs + historial GET /scenarios/{id}/runs. |  | Pendiente |  |  |  |

### G4 · Comparación y escenarios golden

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G4.1 | ScenarioComparator (diferencias por clave con tolerancia). |  | Pendiente |  |  |  |
| G4.2 | GET /scenarios/{id}/runs/{a}/compare/{b}. |  | Pendiente |  |  |  |
| G4.3 | Marca golden (PUT /scenarios/{id}/golden) y job SCENARIO_REGRESSION (POST /scenarios/golden/runs) con informe. |  | Pendiente |  |  |  |

### G5 · Archivo (versiones y rollback)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| G5.1 | V0xx__archives.sql + dominio, entidad y repositorio de Archive. |  | Pendiente |  |  |  |
| G5.2 | ArchiveService: guarda versiones de resultados, escenarios y pipelines al cambiar; retención configurable. |  | Pendiente |  |  |  |
| G5.3 | ArchiveResource (list, get y rollback). |  | Pendiente |  |  |  |

## Grupo H · Modelos (MLCore v1)

### H1 · Modelos y ficheros

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H1.1 | Dominio, entidades y repositorio. |  | Pendiente |  |  |  |
| H1.2 | ModelService + ModelResource CRUD. |  | Pendiente |  |  |  |
| H1.3 | Subida y descarga de ficheros (streaming, límite de tamaño, SHA-256). |  | Pendiente |  |  |  |

### H2 · Entrenamiento

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H2.1 | Spike: API de entrenamiento de MerLabCore (nn/trainer, ml): qué algoritmos se entrenan y cómo se serializa… | Spike | Pendiente |  |  |  |
| H2.2 | MlEnginePort.train(...) en el adaptador. |  | Pendiente |  |  |  |
| H2.3 | Job ML_TRAIN + POST /models/{id}/train: guarda el fichero y las métricas. |  | Pendiente |  |  |  |

### H3 · Predicción

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| H3.1 | MlEnginePort.predict(...) + caché de modelos cargados (LRU con límite de memoria). |  | Pendiente |  |  |  |
| H3.2 | POST /models/{id}/predict síncrono y asíncrono (ML_PREDICT). |  | Pendiente |  |  |  |

## Grupo I · Streaming y alertas

### I1 · Definición de streams

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I1.1 | V0xx__streams_alerts.sql. |  | Pendiente |  |  |  |
| I1.2 | Dominio Stream con estados CREATED, RUNNING, STOPPED y FAILED (añadir su diagrama de estado al diseño). |  | Pendiente |  |  |  |
| I1.3 | StreamService + StreamResource CRUD. |  | Pendiente |  |  |  |

### I2 · Buffer, ventanas y StreamProcessor

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I2.1 | StreamBuffer acotado con política de descarte. |  | Pendiente |  |  |  |
| I2.2 | WindowAssembler (tumbling y sliding, por cantidad o por tiempo). |  | Pendiente |  |  |  |
| I2.3 | StreamProcessor: ejecuta el pipeline o RPN por ventana en el carril PARALLEL; start y stop. |  | Pendiente |  |  |  |

### I3 · Ingesta

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I3.1 | POST /streams/{id}/events (lotes; se admite API key). |  | Pendiente |  |  |  |
| I3.2 | WS /ws/streams/{id}/ingest. |  | Pendiente |  |  |  |
| I3.3 | Cola JMS en WildFly (java:/jms/queue/StackflowStreamQueue) + StreamIngestMDB (la idea de SignalProcessorMDB… | 👤 | Pendiente |  |  |  |

### I4 · Resultados y alertas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I4.1 | WS /ws/streams/{id}/results. |  | Pendiente |  |  |  |
| I4.2 | AlertRuleService (GT, LT, RATE) + AlertRuleResource. |  | Pendiente |  |  |  |
| I4.3 | AlertService (persistir, /alerts, ack, WS /ws/alerts). |  | Pendiente |  |  |  |

### I5 · Fuentes externas (opcional)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| I5.1 | Port StreamSourcePort (start, stop, onEvent). |  | Pendiente |  |  |  |
| I5.2 | Adaptador Kafka en el módulo stackflow-adapter-kafka (perfil kafka). |  | Pendiente |  |  |  |
| I5.3 | Adaptador RabbitMQ (perfil rabbit). |  | Pendiente |  |  |  |
| I5.4 | Spike: fuente JForex (SDK frente a proxy REST/WS); anotar decisión. | Spike | Pendiente |  |  |  |

## Grupo K · Observabilidad y operación

### K1 · Métricas

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K1.1 | Spike: métricas en WildFly 36 (subsistema Micrometer / Prometheus); anotar. | Spike | Pendiente |  |  |  |
| K1.2 | Port MetricsPort + instrumentar jobs (contadores, duración, cola) y sesiones de stack. |  | Pendiente |  |  |  |
| K1.3 | Instrumentar streams (latencia, eventos/s) y JVM (heap, CPU). |  | Pendiente |  |  |  |
| K1.4 | GET /admin/metrics (resumen JSON). |  | Pendiente |  |  |  |

### K2 · Alertas de sistema

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K2.1 | SystemAlertMonitor (memoria %, tasa de fallos de jobs, longitud de cola) → AlertService. |  | Pendiente |  |  |  |

### K3 · Administración

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K3.1 | GET /admin/components (motores, BD, Ollama, Qdrant, Redis, Neo4j, JMS, plugins). |  | Pendiente |  |  |  |
| K3.2 | Recarga de configuración (ConfigProvider.reload) desde /admin/config. |  | Pendiente |  |  |  |

### K4 · Logging estructurado

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| K4.1 | Formato JSON en WildFly (script CLI) y documentación. |  | Pendiente |  |  |  |
| K4.2 | Propagar correlationId y jobId al MDC de los hilos de jobs y streams. |  | Pendiente |  |  |  |

## Grupo L · Extensibilidad

### L1 · Plugins (PluginManager v1)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| L1.1 | Módulo stackflow-plugin-api: StackflowPlugin, AlgorithmPlugin, DataSourcePlugin, VisualizationPlugin y Plug… |  | Pendiente |  |  |  |
| L1.2 | V0xx__plugins.sql + registro de plugins (checksum y estado). |  | Pendiente |  |  |  |
| L1.3 | PluginManager: carga desde stackflow.plugins.dir con URLClassLoader aislado + ServiceLoader; enable, disabl… |  | Pendiente |  |  |  |
| L1.4 | /admin/plugins (list, register, enable, disable, delete). |  | Pendiente |  |  |  |
| L1.5 | Los AlgorithmPlugin aparecen en /ml/algorithms (source=plugin) y los DataSourcePlugin como StreamSourcePort. |  | Pendiente |  |  |  |
| L1.6 | Plugin de ejemplo stackflow-plugin-example para tests. |  | Pendiente |  |  |  |

### L2 · Scripting (Merlynx / MCP)

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| L2.1 | Spike: revisar el repo mabondano/merlynx y la nota "MCP approach" del diseño v1; decidir lenguaje y alcance. | 👤 Spike | Pendiente |  |  |  |
| L2.2 | Port ScriptEnginePort + primer lenguaje: script RPN (secuencia de operaciones del stack). |  | Pendiente |  |  |  |
| L2.3 | Sandbox: límites de tiempo, de operaciones y de memoria. |  | Pendiente |  |  |  |
| L2.4 | GET /scripts/languages y POST /scripts/run (job SCRIPT). |  | Pendiente |  |  |  |
| L2.5 | Spike (opcional): exponer StackFlow como servidor MCP (herramientas = endpoints). | Spike | Pendiente |  |  |  |

## Grupo M · Cierre

### M1 · Pruebas extremo a extremo

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M1.1 | Matriz endpoint → IT (generada desde OpenAPI) y huecos cubiertos. |  | Pendiente |  |  |  |
| M1.2 | Ejecución completa con -P!rag y con -Prag. |  | Pendiente |  |  |  |

### M2 · Rendimiento y memoria

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M2.1 | Pruebas de carga: concurrencia de jobs, tamaños de chunk, profundidad del stack y throughput de streams. |  | Pendiente |  |  |  |
| M2.2 | Documentar tamaños de datos recomendados y límites por defecto. |  | Pendiente |  |  |  |

### M3 · Documentación y clientes

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M3.1 | Colección Postman v2. |  | Pendiente |  |  |  |
| M3.2 | Guía de la API generada desde OpenAPI. |  | Pendiente |  |  |  |
| M3.3 | Contrato para FlowScope (CORS, /info.engines, WebSockets). |  | Pendiente |  |  |  |
| M3.4 | Guía de instalación v2 (NetBeans, WildFly, MariaDB, perfil RAG opcional con Ollama, Qdrant y Redis). |  | Pendiente |  |  |  |

### M4 · Seguridad final

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M4.1 | Revisión tipo OWASP: autenticación en todos los endpoints, límites de entrada, secretos fuera de los logs,… |  | Pendiente |  |  |  |
| M4.2 | Spike (opcional): OAuth/OIDC (Keycloak o el OIDC de WildFly), del roadmap v1. | Spike | Pendiente |  |  |  |

### M5 · Release 2.0.0

| ID | Micropaso | Marca | Estado | Fecha | Commit | Notas |
|---|---|---|---|---|---|---|
| M5.1 | Versión 2.0.0, README final y CHANGELOG. |  | Pendiente |  |  |  |
| M5.2 | Tag v2.0.0 y push (con aprobación). | 👤 | Pendiente |  |  |  |
| M5.3 | Decidir la rama por defecto en GitHub (main, master, v2). | 👤 | Pendiente |  |  |  |
