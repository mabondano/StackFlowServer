# StackFlowServer v2: roadmap de implementación

Versión 1 · 15.10.2026 · Rama `v2` · NetBeans + WildFly 36 + MariaDB

Este roadmap describe **toda** la implementación del REST unificado (StackFlow + MerLabCore + MerLabRAG opcional), desde cero y separada del código v1. Está escrito para que un agente (Codex o Claude) implemente **un micropaso cada vez** y espere `ROGER` antes de seguir (ver [README.md](README.md)).

- **Estado:** [TRACKING.md](TRACKING.md)
- **Registro de lo hecho:** [UMSETZUNGSPLAN.md](UMSETZUNGSPLAN.md)
- **Diseño:** [design/DISENO_REST_UNIFICADO.md](design/DISENO_REST_UNIFICADO.md) (decisiones, endpoints, Anexo A con los módulos v1) y [design/DISENO_CLASES_REST.md](design/DISENO_CLASES_REST.md) (clases, estados, secuencias)

---

## 1. Convenciones (válidas para todos los pasos)

### 1.1 Entorno

| Elemento | Valor |
|---|---|
| Carpeta de trabajo (rama `v2`) | `C:\merLab\projects\StackFlowServer\stackflow-v2` (worktree del repo `stackflow`) |
| Código v1 | rama `master` + tag `v1-legacy`. **No se toca y no se mezcla.** |
| IDE | NetBeans (proyecto Maven multi-módulo) |
| Java | 17 (`maven.compiler.release=17`) |
| Plataforma | Jakarta EE 10 (`jakarta.jakartaee-api` 10.0.0, `provided`) |
| Servidor | WildFly 36.0.1.Final en `C:\merLab\projects\StackFlowServer\wildfly-36.0.1.Final` |
| URL base | `http://localhost:8090/stackflow/api/v1` (context root `/stackflow`, `@ApplicationPath("api/v1")`) |
| Base de datos | MariaDB `localhost:3307`, BD `stackflowdb`, DataSource `java:/jdbc/StackflowDS`. Tests: `stackflowdb_test`, `java:/jdbc/StackflowTestDS` |
| JSON | Jackson (proveedor por defecto de RESTEasy en WildFly) |
| Tests | JUnit 5 + Mockito (unit, `*Test`, surefire), Arquillian + WildFly managed (`*IT`, failsafe, perfil `it`), ArchUnit |
| Librerías propias | `com.merlab:merlab-core` (obligatoria), `com.merlab.rag:merlab-rag` (**opcional**, perfil `rag`). **Nunca se modifican.** |

### 1.2 Módulos y paquetes

| Módulo | Paquete raíz | Contenido |
|---|---|---|
| `stackflow-api` | `com.merlab.stackflow.api.*` | DTOs (records), sin lógica, sin dependencias internas |
| `stackflow-service` | `com.merlab.stackflow.{domain,port,service}.*` | dominio, ports, servicios, sistema de jobs, errores |
| `stackflow-persistence` | `com.merlab.stackflow.persistence.*` | entidades JPA, repositorios (implementan ports), migraciones |
| `stackflow-adapter-merlabcore` | `com.merlab.stackflow.adapter.merlabcore` | `MlEnginePort` con MerLabCore |
| `stackflow-adapter-merlabrag` | `com.merlab.stackflow.adapter.merlabrag` | `RagEnginePort` con MerLabRAG (solo perfil `rag`) |
| `stackflow-web` (WAR) | `com.merlab.stackflow.web.*` | resources, filtros, mappers DTO↔dominio, seguridad, websockets, MDB |
| `stackflow-plugin-api` (grupo L) | `com.merlab.stackflow.plugin.api` | SPI para plugins de terceros |

Regla de dependencias (ArchUnit, paso 0.4.1): `web → api, service`; `persistence/adapters → service`; `service` no importa `jakarta.ws.rs`, `pegasus..` ni `com.merlab.signals..`.

### 1.3 Roles

`VIEWER` (lectura), `ANALYST` (crear y ejecutar), `AUDITOR` (lectura + logs, historial, conversaciones), `ADMIN` (todo). Detalle en el Anexo A.5 del diseño.

### 1.4 Reglas de implementación

1. **Un micropaso = un cambio pequeño y verificable.** No adelantes trabajo de otros pasos.
2. **Verificación mínima de cada paso:** `mvn -q -P!rag clean verify` en verde. Desde J2 también `mvn -q -Prag clean verify`. Si el paso pide un IT, `mvn -Pit verify` con WildFly local.
3. **Tests:** cada clase con lógica lleva su test unitario en el mismo paso.
4. **Prohibido:** modificar MerLabCore, MerLabRAG o sus miniservers; tocar `master` o el tag `v1-legacy`; copiar código del proyecto de la empresa (`genai-java-rest-api`); hacer push sin que el usuario lo pida.
5. **Secretos** (JWT, contraseñas, claves de BD) nunca en el código ni en git: vienen de la configuración de WildFly o de variables de entorno.
6. **Si el diseño y el paso no coinciden**, manda el diseño. Si falta algo, anótalo en el Umsetzungsplan como "Decisión" y pregunta al usuario.
7. Los pasos marcados con 👤 requieren una acción o una decisión del usuario. El agente prepara todo, explica qué hay que hacer y se detiene.
8. Los pasos **Spike** no producen código de producción: el resultado es una nota de decisión en el Umsetzungsplan.

### 1.5 Orden de los grupos

| Orden | Grupo | Tema | Por qué en este orden |
|---|---|---|---|
| 1 | **0** | Fundamentos | rama, módulos, NetBeans, WildFly, guardarraíles |
| 2 | **A** | Contrato de la API (DTOs) | por aquí empezaste; fija la forma de todo lo demás |
| 3 | **B** | Infraestructura web | errores, filtros, health, OpenAPI |
| 4 | **C** | Persistencia, seguridad y administración | BD, usuarios, JWT/API key, config, historial |
| 5 | **D** | Sistema de jobs (Orchestrator v1) | base de todo lo asíncrono |
| 6 | **E** | MerLabCore y stack RPN | primer motor; paridad con su miniserver |
| 7 | **J** | RAG (opcional) | segundo motor; J1 no necesita MerLabRAG |
| 8 | **F** | Datos, señales y BD externas | entrada de datos para pipelines |
| 9 | **G** | Pipelines, batch, escenarios y archivo | núcleo "StackFlow" del diseño v1 |
| 10 | **H** | Modelos (MLCore) | entrenar y predecir |
| 11 | **I** | Streaming y alertas | tiempo real |
| 12 | **K** | Observabilidad y operación | métricas, monitor, logs |
| 13 | **L** | Extensibilidad | plugins, Merlynx/scripting |
| 14 | **M** | Cierre | E2E, rendimiento, documentación, release |

Formato de cada milestone: **Objetivo**, **Depende de**, **Diseño** (secciones), tabla de micropasos (**ID · Micropaso · Clases / archivos · Verificación**) y **DoD** (definición de terminado).

---

## Grupo 0 · Fundamentos

### 0.1 · Rama v2 y retirada del código v1
**Objetivo:** trabajar en una rama limpia sin perder el v1. **Depende de:** —. **Diseño:** —

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.1.1 | 👤 Crear el tag `v1-legacy` sobre `master` (commit `79d1e53`). Push del tag solo si el usuario lo aprueba. No tocar el merge pendiente de `master`. | git | `git tag -l v1-legacy` |
| 0.1.2 | Crear la rama `v2` desde `master` en el worktree `stackflow-v2`. | git worktree | `git -C stackflow-v2 branch --show-current` → `v2` |
| 0.1.3 | En `v2`: mover la documentación v1 a `docs/v1/` (`API.md`, `DATABASE_SCHEMA.md`, `SETUP_GUIDE.md`, `Setup Guide.docx`, `sqlscript_stackflowdb.sql`, `postman/`). Borrar `src/`, `class_list.txt` y el `pom.xml` v1. | `docs/v1/` | `git status`; no queda `src/` en la raíz |
| 0.1.4 | Crear `.gitignore` (`target/`, `nbproject/private/`, `*.class`, `*.log`, `.idea/`) y quitar `target/` del índice (`git rm -r --cached target`). | `.gitignore` | `git ls-files target` vacío |
| 0.1.5 | README v2 provisional: qué es, cómo compilar y enlace a `docs/v2/`. | `README.md` | se ve en NetBeans y GitHub |

**DoD:** `v2` sin código v1; v1 recuperable por tag y rama.

### 0.2 · Proyecto Maven multi-módulo
**Objetivo:** esqueleto que compila con los módulos del diseño. **Depende de:** 0.1. **Diseño:** DISENO_REST_UNIFICADO §3.1; DISENO_CLASES_REST §2, §3.10

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.2.1 | POM padre `com.merlab:stackflow-parent:2.0.0-SNAPSHOT` (`pom`). Propiedades (Java 17, UTF-8, Jakarta 10), `dependencyManagement` (jakartaee-api provided, junit-bom, mockito, archunit) y `pluginManagement` (compiler, surefire, failsafe, war, enforcer: Java ≥ 17 y Maven ≥ 3.9). | `pom.xml` | `mvn -q validate` |
| 0.2.2 | Módulo `stackflow-api` (jar) con `package-info.java`. | `stackflow-api/pom.xml` | compila |
| 0.2.3 | Módulo `stackflow-service` (jar), sin dependencia de `api`. | `stackflow-service/pom.xml` | compila |
| 0.2.4 | Módulo `stackflow-persistence` (jar) → depende de `service`. | `stackflow-persistence/pom.xml` | compila |
| 0.2.5 | Módulo `stackflow-adapter-merlabcore` (jar) → `service` (MerLabCore se añade en E1). | `stackflow-adapter-merlabcore/pom.xml` | compila |
| 0.2.6 | Módulo `stackflow-adapter-merlabrag` (jar) → `service`, declarado **solo** dentro del perfil `rag`. Activación por fichero: `${user.home}/.m2/repository/com/merlab/rag/merlab-rag/0.1.0-SNAPSHOT/merlab-rag-0.1.0-SNAPSHOT.jar`. | `pom.xml` (perfil), módulo | `mvn -P!rag` no lo construye |
| 0.2.7 | Módulo `stackflow-web` (war, `finalName` `stackflow`) → api, service, persistence, adapter-merlabcore; adapter-merlabrag solo en perfil `rag`. `WEB-INF/beans.xml` (`annotated`), `jboss-web.xml` (context root `/stackflow`), `index.html` mínimo. | `stackflow-web/…` | `target/stackflow.war` existe |
| 0.2.8 | Build completo en ambos modos. | — | `mvn -P!rag clean verify` y `mvn clean verify` en verde |

**DoD:** 6 módulos compilan; el módulo RAG solo con el perfil.

### 0.3 · NetBeans y WildFly
**Objetivo:** abrir, desplegar y ver la app en el servidor local. **Depende de:** 0.2. **Diseño:** DISENO_CLASES_REST §1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.3.1 | 👤 Abrir `stackflow-v2` en NetBeans como proyecto padre y comprobar los módulos. Añadir `nb-configuration.xml` en `stackflow-web` con el hint de despliegue a WildFly. | `stackflow-web/nb-configuration.xml` | NetBeans muestra los módulos |
| 0.3.2 | 👤 Registrar WildFly 36 en NetBeans (puerto HTTP 8090). Decidir el perfil del servidor: se recomienda `standalone-microprofile.xml` (OpenAPI, métricas, config). Anotar la decisión. | Umsetzungsplan | servidor arranca desde NetBeans |
| 0.3.3 | 👤 Comprobar o crear los DataSources `java:/jdbc/StackflowDS` (stackflowdb) y `java:/jdbc/StackflowTestDS` (stackflowdb_test) con un script CLI versionado. | `scripts/wildfly/datasources.cli` | `jboss-cli` → `test-connection-in-pool` OK |
| 0.3.4 | Desplegar el WAR vacío. | — | `GET http://localhost:8090/stackflow/` → 200 |
| 0.3.5 | Despliegue por Maven con `wildfly-maven-plugin` (`mvn wildfly:deploy`) y scripts `scripts/build.ps1` y `scripts/deploy.ps1`. | `stackflow-web/pom.xml`, `scripts/` | `scripts/deploy.ps1` despliega |

**DoD:** la app se despliega desde NetBeans y desde Maven.

### 0.4 · Guardarraíles de calidad
**Objetivo:** que la arquitectura no se rompa con el tiempo. **Depende de:** 0.2. **Diseño:** DISENO_CLASES_REST §2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.4.1 | Reglas ArchUnit: `service` no importa `jakarta.ws.rs..`, `jakarta.persistence..`, `pegasus..` ni `com.merlab.signals..`; `api` no importa nada interno; solo los adaptadores importan las librerías; `web` no importa `persistence` salvo producers. | `stackflow-web/src/test/.../ArchitectureTest.java` | test verde |
| 0.4.2 | Convención de tests: `*Test` (surefire) y `*IT` (failsafe, perfil `it`). | POMs | `mvn verify` ejecuta solo `*Test` |
| 0.4.3 | Base Arquillian: dependencias, `arquillian.xml` (WildFly managed, `jbossHome` por propiedad), `AbstractStackflowIT` (despliegue del WAR) y `IndexIT` de humo. | `stackflow-web/src/test/...` | `mvn -Pit verify` verde |
| 0.4.4 | `.editorconfig`, formato NetBeans (4 espacios) y plantilla de cabecera de licencia (Apache 2.0, como v1). | `.editorconfig`, `docs/v2/LICENSE_HEADER.txt` | — |

**DoD:** reglas activas y un IT de humo que despliega en WildFly.

### 0.5 · Documentación de trabajo
**Objetivo:** que cualquier agente sepa qué hacer y cómo informar. **Depende de:** —. **Diseño:** —

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| 0.5.1 | `docs/v2/` con README, ROADMAP, TRACKING, UMSETZUNGSPLAN y `design/`. | `docs/v2/` | existen |
| 0.5.2 | `AGENTS.md` (Codex) y `CLAUDE.md` (Claude) en la raíz, con el protocolo ROGER. | raíz | existen |
| 0.5.3 | 👤 Primer commit de la documentación en `v2` (solo con aprobación). | git | `git log -1` |

**DoD:** documentación versionada en `v2`.

---

## Grupo A · Contrato de la API (DTOs)

Todos los DTOs son `record`s en `stackflow-api`, con Bean Validation y sin lógica. Las fechas viajan como `String` ISO-8601 y los enums como `String`. Cada milestone incluye un test de ida y vuelta JSON y un test de validación.

### A0 · Soporte de pruebas para DTOs
**Objetivo:** probar serialización y validación sin servidor. **Depende de:** 0.2. **Diseño:** DISENO_CLASES_REST §3.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A0.1 | Dependencias de test en `stackflow-api`: jackson-databind (+ módulo de records), hibernate-validator + expressly. | `stackflow-api/pom.xml` | compila |
| A0.2 | Utilidades `JsonTestSupport.roundTrip(obj, Class)` y `ValidationTestSupport.violations(obj)`. | `api/src/test/.../support/` | test de ejemplo verde |

**DoD:** cualquier DTO se prueba en una línea.

### A1 · DTOs comunes
**Objetivo:** errores, salud, info y paginación. **Depende de:** A0. **Diseño:** DISENO_CLASES_REST §3.2, §3.8; DISENO_REST_UNIFICADO §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A1.1 | `ErrorResponse(code, status, message, correlationId, details)` y `ErrorDetail(field, message)`. | `api.common` | round-trip + validación |
| A1.2 | `HealthResponse(status, components)` y `ComponentHealth(name, status, detail)`. | `api.common` | round-trip |
| A1.3 | `InfoResponse(version, buildTime, engines: Map, counts: Map)`. | `api.common` | round-trip |
| A1.4 | `PageResponse<T>(items, total, offset, limit)` y `PageRequestParams` (offset ≥ 0, 1 ≤ limit ≤ 500). | `api.common` | validación de límites |
| A1.5 | `ApiPaths`: constantes de todas las rutas del diseño (incluido el Anexo A.3). | `api.common.ApiPaths` | compila |

**DoD:** DTOs comunes con tests.

### A2 · Auth, usuarios, ajustes, proyectos, config e historial
**Objetivo:** contrato del grupo C. **Depende de:** A1. **Diseño:** Anexo A.3 (Auth, Usuarios, Proyectos, Config, Historial)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A2.1 | `LoginRequest`, `TokenResponse(token, expiresAt, roles)`, `MeResponse`. | `api.auth` | validación (`@NotBlank`) |
| A2.2 | `ApiKeyCreateRequest(name, expiresAt)`, `ApiKeyResponse(id, name, prefix, createdAt, expiresAt, lastUsedAt)`, `ApiKeyCreatedResponse(+secret)`. | `api.auth` | round-trip |
| A2.3 | `UserCreateRequest` (email, contraseña ≥ 10 caracteres, roles), `UserUpdateRequest`, `UserResponse` (sin contraseña), `RoleAssignmentRequest`. | `api.user` | validación de email y contraseña |
| A2.4 | `UserSettingsDto(settings: Map<String,String>)`. | `api.user` | round-trip |
| A2.5 | `ProjectRequest`, `ProjectResponse`, `ProjectMemberRequest`, `ProjectMemberResponse`. | `api.project` | validación |
| A2.6 | `ConfigEntryDto(key, value, description, updatedAt)` y `HistoryEntryResponse(id, entity, entityId, action, user, timestamp, details)`. | `api.admin` | round-trip |

**DoD:** contrato completo de seguridad y administración.

### A3 · Jobs, schedules y post-proceso
**Objetivo:** contrato del grupo D. **Depende de:** A1. **Diseño:** DISENO_CLASES_REST §3.2 (jobs); Anexo A.3 y A.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A3.1 | `CreateJobRequest(type, payload)` (`type` validado contra la lista de A.4 + §5.5). | `api.job` | validación |
| A3.2 | `JobResponse`, `JobListResponse` (= `PageResponse<JobResponse>`). | `api.job` | round-trip |
| A3.3 | `JobEventsResponse(jobId, status, nextSequence, events)` y `JobEventResponse`. | `api.job` | round-trip |
| A3.4 | `JobResultResponse(jobId, status, result, completedAt)` con `result` polimórfico (`Object`). | `api.job` | round-trip con 2 tipos |
| A3.5 | `ScheduleRequest(name, cron, jobType, payload, enabled)` y `ScheduleResponse(…, nextFireAt, lastRunJobId)`. | `api.job` | validación de cron no vacío |
| A3.6 | `PostProcessTaskRequest(jobType, payload, onStatus)` y `PostProcessTaskResponse`. | `api.job` | round-trip |

**DoD:** contrato de jobs completo.

### A4 · ML (MerLabCore) y stack RPN
**Objetivo:** contrato de los grupos E y G. **Depende de:** A1. **Diseño:** DISENO_CLASES_REST §3.2 (ML); Anexo A.3 (Stack)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A4.1 | `AlgorithmResponse` (campos de `AlgorithmDescriptor`) y `AlgorithmListResponse`. | `api.ml` | round-trip |
| A4.2 | `ExampleResponse(key, category, description)` y `ExampleListResponse`. | `api.ml` | round-trip |
| A4.3 | `RunRequest(params, mode: sync\|async)` y `MlRunResponse(success, consoleOutput, chartHtml, metrics)`. | `api.ml` | validación de `mode` |
| A4.4 | `RpnRequest(expression, inputs)` y `RpnResponse(values, size)`. | `api.stack` | validación |
| A4.5 | `StackOperationResponse(name, arity, description, params)`. | `api.stack` | round-trip |
| A4.6 | `StackSessionResponse(id, depth, top, createdAt, expiresAt)`, `StackPushRequest(values \| signalId)`, `StackApplyRequest(operation, params)`, `StackSnapshotResponse(elements)`. | `api.stack` | validación (`values` xor `signalId`) |

**DoD:** contrato ML y stack.

### A5 · Datos, señales y BD externas
**Objetivo:** contrato del grupo F. **Depende de:** A1. **Diseño:** Anexo A.2, A.3 (Señales, Grupos, BD externas)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A5.1 | `SignalRequest(name, projectId, unit, sampleRate, metadata)` y `SignalResponse`. | `api.data` | validación |
| A5.2 | `SignalDataPageResponse(signalId, offset, limit, total, values)`. | `api.data` | round-trip |
| A5.3 | `SignalGroupRequest`, `SignalGroupResponse(…, signalIds)`. | `api.data` | round-trip |
| A5.4 | `DatasetRequest(name, description, location, format)` y `DatasetResponse`. | `api.data` | validación |
| A5.5 | `DbConnectionRequest(name, type: MARIADB\|POSTGRESQL, url, username, password)` (password solo de escritura) y `DbConnectionResponse` (sin password). | `api.data` | el JSON de respuesta no contiene `password` |
| A5.6 | `DbConnectionTestResponse(ok, latencyMs, message)` y `DataImportRequest(query, targetSignalName, chunkSize)`. | `api.data` | validación |

**DoD:** contrato de datos.

### A6 · Pipelines, escenarios y archivo
**Objetivo:** contrato del grupo G. **Depende de:** A4. **Diseño:** Anexo A.3 (Pipelines, Escenarios, Archivo)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A6.1 | `PipelineRequest(name, description, projectId, steps)`, `PipelineStepDto(order, operation, params)` y `PipelineResponse`. | `api.pipeline` | validación (≥ 1 paso) |
| A6.2 | `PipelineVersionResponse(version, createdAt, steps)` y `CurrentVersionRequest(version)`. | `api.pipeline` | round-trip |
| A6.3 | `ScenarioRequest(name, description, stackSnapshot, parameters)`, `ScenarioEntityDto(entityType, entityId)` y `ScenarioResponse(…, golden)`. | `api.scenario` | round-trip |
| A6.4 | `ScenarioRunResponse(runId, status, results, startedAt, finishedAt)`. | `api.scenario` | round-trip |
| A6.5 | `ScenarioCompareResponse(runA, runB, equal, differences[{key, a, b, delta}], tolerance)`. | `api.scenario` | round-trip |
| A6.6 | `ArchiveResponse(id, entityType, entityId, version, createdAt, createdBy)` y `RollbackResponse`. | `api.archive` | round-trip |

**DoD:** contrato de pipelines, escenarios y archivo.

### A7 · Modelos
**Objetivo:** contrato del grupo H. **Depende de:** A1. **Diseño:** Anexo A.3 (Modelos)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A7.1 | `ModelRequest(name, algorithm, projectId, description)` y `ModelResponse(…, status, metrics)`. | `api.model` | validación |
| A7.2 | `ModelFileResponse(id, fileName, sizeBytes, sha256, createdAt)`. | `api.model` | round-trip |
| A7.3 | `TrainRequest(datasetId \| signalIds, params, mode)`. | `api.model` | validación (datasetId xor signalIds) |
| A7.4 | `PredictRequest(inputs, mode)` y `PredictResponse(outputs, modelVersion, latencyMs)`. | `api.model` | round-trip |

**DoD:** contrato de modelos.

### A8 · Streams y alertas
**Objetivo:** contrato del grupo I. **Depende de:** A1. **Diseño:** Anexo A.3 (Streams, Alertas)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A8.1 | `WindowSpecDto(type: TUMBLING\|SLIDING, unit: COUNT\|TIME, size, slide)`. | `api.stream` | validación (slide ≤ size) |
| A8.2 | `StreamRequest(name, sourceType: REST\|WEBSOCKET\|JMS\|KAFKA\|RABBITMQ, window, pipelineId, retention)` y `StreamResponse(…, state)`. | `api.stream` | validación |
| A8.3 | `StreamEventRequest(points[{timestamp, values}])` (lote, máx. configurable) y `StreamResultMessage(streamId, windowStart, windowEnd, values)`. | `api.stream` | validación de tamaño |
| A8.4 | `AlertRuleRequest(name, streamId \| metric, condition: GT\|LT\|RATE, threshold, severity)` y `AlertRuleResponse`. | `api.alert` | validación |
| A8.5 | `AlertResponse(id, ruleId, state: OPEN\|ACK\|CLOSED, value, raisedAt, ackBy)`. | `api.alert` | round-trip |

**DoD:** contrato de tiempo real.

### A9 · RAG
**Objetivo:** contrato del grupo J (misma forma que el contrato de referencia de la empresa, código propio). **Depende de:** A1. **Diseño:** DISENO_CLASES_REST §3.2, §3.9

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A9.1 | `QueryRequest(useCaseId, sessionId, userInput, orchestrationMode)`. | `api.rag` | validación (`@NotBlank` ×3) |
| A9.2 | `QueryResponse` + `ArtifactResponse`, `QueryErrorResponse`, `TraceResponse`, `TraceEventResponse`, `AuditResponse`. | `api.rag` | round-trip completo |
| A9.3 | `UseCaseResponse`, `UseCaseItem`, `FlowListResponse`. | `api.rag` | round-trip |
| A9.4 | `ConversationResponse`, `ConversationListResponse`, `ConversationMessagesResponse`, `ConversationMessageResponse`. | `api.rag` | round-trip |
| A9.5 | `RunLogListResponse`, `RunLogResponse`, `RunLogEntryResponse`, `RunLogAnalysisResponse`. | `api.rag` | round-trip |
| A9.6 | `IndexingRequest(useCaseId, sources)`. | `api.rag` | validación |

**DoD:** contrato RAG completo (13 endpoints).

### A10 · Administración, métricas, plugins y scripts
**Objetivo:** contrato de los grupos K y L. **Depende de:** A1. **Diseño:** Anexo A.3 (Monitor, Plugins, Scripting)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| A10.1 | `ComponentsResponse(components[{name, type, state, detail}])`. | `api.admin` | round-trip |
| A10.2 | `MetricsResponse(jvm{heapUsed, heapMax, cpu}, jobs{running, queued, failed24h}, streams{…}, stackSessions)`. | `api.admin` | round-trip |
| A10.3 | `PluginResponse(id, name, version, type, state, checksum)` y `PluginRegisterRequest(fileName, checksum)`. | `api.plugin` | validación |
| A10.4 | `ScriptLanguageResponse(id, description)`, `ScriptRunRequest(language, source, params, mode)` (tamaño máx. del script). | `api.script` | validación |

**DoD:** contrato completo de la API; `stackflow-api` terminado.

---

## Grupo B · Infraestructura web

### B1 · Aplicación JAX-RS y JSON
**Objetivo:** arrancar el REST. **Depende de:** A1, 0.3. **Diseño:** DISENO_CLASES_REST §3.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B1.1 | `StackflowApplication` con `@ApplicationPath("api/v1")` (**una sola** `Application`). | `web.StackflowApplication` | despliega |
| B1.2 | `JacksonConfig` (`ContextResolver<ObjectMapper>`): módulo de records, `NON_NULL` y fallo con propiedades desconocidas. | `web.config.JacksonConfig` | unit test del mapper |

**DoD:** la aplicación REST responde (404 en rutas inexistentes).

### B2 · Modelo de errores
**Objetivo:** todos los errores como `ErrorResponse`. **Depende de:** B1. **Diseño:** DISENO_CLASES_REST §3.8, §7.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B2.1 | `ErrorCode` con estado HTTP: todos los códigos de §3.8 + `RAG_NOT_INSTALLED`, `CAPABILITY_NOT_SUPPORTED` y `ALGORITHM_NOT_EXECUTABLE`. | `service.error.ErrorCode` | unit test de códigos HTTP |
| B2.2 | Jerarquía `StackflowException` (ValidationFailed, NotFound, Conflict, JobNotFinished, AlgorithmNotExecutable, CapabilityNotSupported, UnprocessableResult, EngineUnavailable, RagNotInstalled, UpstreamTimeout). | `service.error.*` | unit tests |
| B2.3 | `ErrorDetailPolicy` (`stackflow.errors.details=dev\|prod`). | `web.error.ErrorDetailPolicy` | unit test |
| B2.4 | `StackflowExceptionMapper`, `ConstraintViolationMapper` y `WebApplicationExceptionMapper` (404/405/415 → `ErrorResponse`). `UnprocessableResultException` devuelve su cuerpo propio. | `web.error.*` | unit tests |

**DoD:** ningún error sale con otro formato.

### B3 · Filtros transversales
**Objetivo:** correlación, logging y CORS. **Depende de:** B2. **Diseño:** DISENO_CLASES_REST §4.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B3.1 | `CorrelationIdFilter`: lee o genera `X-Correlation-Id`, lo pone en el MDC y en la respuesta. | `web.filter` | unit test |
| B3.2 | `RequestLoggingFilter`: método, ruta, estado, ms y correlationId (sin cuerpos ni secretos). | `web.filter` | log visible |
| B3.3 | `CorsFilter` con orígenes configurables (por defecto, FlowScope dev `http://localhost:5173`). | `web.filter` | preflight OPTIONS → 204 |

**DoD:** toda respuesta lleva `X-Correlation-Id`.

### B4 · Health, info y OpenAPI
**Objetivo:** endpoints comunes y documentación viva. **Depende de:** B2. **Diseño:** DISENO_REST_UNIFICADO §5.1; DISENO_CLASES_REST §5.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B4.1 | Dominio de salud: `HealthState` (UP, DEGRADED, DOWN, NOT_INSTALLED), `EngineHealth`, `SystemHealth`, port `DatabaseHealthPort`. | `domain.health`, `port` | unit test |
| B4.2 | `HealthService` (agregación §5.5; NOT_INSTALLED no cuenta como fallo). | `service.HealthService` | unit tests de agregación |
| B4.3 | `HealthResource` (`/health/live`, `/health/ready`) e `InfoResource` (`/info`, versión del MANIFEST). | `web.resource.common` | `curl` 200 |
| B4.4 | **Spike:** MicroProfile OpenAPI de WildFly frente a swagger-core. Decidir y anotar. | Umsetzungsplan | decisión escrita |
| B4.5 | Aplicar la decisión: anotaciones OpenAPI en resources y DTOs base, y Swagger UI accesible. | `web`, `api` | `/openapi` y UI visibles |

**DoD:** salud, info y OpenAPI funcionando.

### B5 · Tests de integración base
**Objetivo:** proteger lo común con ITs. **Depende de:** B4, 0.4.3. **Diseño:** DISENO_CLASES_REST §4.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| B5.1 | `HealthIT` (live, ready). | `*IT` | `mvn -Pit verify` |
| B5.2 | `ErrorFormatIT`: ruta inexistente → 404 `ErrorResponse` con `correlationId`; JSON inválido → 400. | `*IT` | verde |

**DoD:** base web cubierta por ITs.

---

## Grupo C · Persistencia, seguridad y administración

### C1 · Base de datos y migraciones
**Objetivo:** esquema versionado. **Depende de:** 0.3.3, B4. **Diseño:** Anexo A.2; DISENO_CLASES_REST §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C1.1 | Flyway (core + mysql) en `stackflow-persistence`, ejecutado por un `@Singleton @Startup` (`FlywayMigrator`) sobre `StackflowDS`. | `persistence.migration` | arranca sin errores |
| C1.2 | `V001__baseline.sql` a partir de `docs/v1/sqlscript_stackflowdb.sql`: revisar InnoDB, utf8mb4, FKs e índices. Baseline sobre una BD existente (`baselineOnMigrate`). | `db/migration/V001__baseline.sql` | 27 tablas |
| C1.3 | `V002__jobs.sql`: ampliar `job_queue` (§6 + `event_seq`) y crear `job_events`. | `V002` | `DESCRIBE job_events` |
| C1.4 | `V003__security.sql`: `api_keys`; semilla de roles ADMIN, ANALYST, VIEWER y AUDITOR. | `V003` | roles presentes |
| C1.5 | `persistence.xml` (`StackflowPU`, JTA, `StackflowDS`) y `DatabaseHealthAdapter` (implementa `DatabaseHealthPort`). | `META-INF/persistence.xml`, `persistence.health` | `/health/ready` muestra la BD |
| C1.6 | 👤 Usuario administrador inicial: creado al arrancar si no existe ninguno, con contraseña de una variable de entorno (`STACKFLOW_ADMIN_PASSWORD`). | `persistence.bootstrap.AdminBootstrap` | login posible tras C3 |

**DoD:** BD migrada automáticamente al desplegar.

### C2 · Usuarios y roles
**Objetivo:** CRUD de usuarios v2. **Depende de:** C1, A2. **Diseño:** Anexo A.3 (Usuarios), A.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C2.1 | Dominio `User`, `Role` (enum de 4 roles) y port `UserRepository` (findById, findByUsername, page, save, delete, existsByEmail). | `domain.user`, `port` | compila |
| C2.2 | Entidades `UserEntity`, `RoleEntity` (+ `user_roles`), `JpaUserRepository` y su mapper. | `persistence.user` | IT del repositorio |
| C2.3 | Port `PasswordHasher` + `BCryptPasswordHasher`. | `port`, `web.security` | unit test |
| C2.4 | `UserService`: crear (hash), actualizar, borrar, paginar, asignar roles; 409 si el usuario o el email ya existen. | `service.user` | unit tests con `InMemoryUserRepository` |
| C2.5 | `UserApiMapper` y `UserResource` (`/users` CRUD, `/users/{id}/roles`). | `web.resource.user` | IT CRUD |

**DoD:** `/users` completo con tests.

### C3 · Autenticación (JWT y API key)
**Objetivo:** proteger la API. **Depende de:** C2. **Diseño:** DISENO_CLASES_REST §4.5; Anexo A.3 (Auth)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C3.1 | `JwtService` (HS256, emisor, caducidad y secreto de la configuración). | `web.security` | unit tests (válido, caducado, firma mala) |
| C3.2 | `AuthResource`: `POST /auth/login` y `GET /auth/me`. | `web.resource.auth` | IT login |
| C3.3 | `AuthFilter`: `Authorization: Bearer` o `X-API-Key` → `StackflowPrincipal` + `SecurityContext`; rutas públicas: `/health/*`, `/auth/login`, `/openapi`. | `web.security` | 401 sin credenciales |
| C3.4 | Activar `@RolesAllowed` (`resteasy.role.based.security=true`) y anotar los resources existentes. | `web.xml`, resources | 403 con un rol insuficiente |
| C3.5 | API keys: dominio, entidad (hash SHA-256 + prefijo visible), `ApiKeyService` y `ApiKeyResource` (`/api-keys`). | `domain.auth`, `persistence.auth`, `web.resource.auth` | IT crear, usar y revocar |

**DoD:** toda la API exige credenciales salvo las rutas públicas.

### C4 · Ajustes de usuario y proyectos
**Objetivo:** tablas `user_settings`, `projects` y `project_users`. **Depende de:** C3, A2. **Diseño:** Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C4.1 | Ajustes: entidad, repositorio, `UserSettingsService` y `GET/PUT /users/me/settings`. | `*.settings` | IT |
| C4.2 | Proyectos: dominio, entidad, repositorio, `ProjectService` (el owner gestiona; ADMIN todo) y `ProjectResource`. | `*.project` | unit + IT |
| C4.3 | Miembros: `GET/POST/DELETE /projects/{id}/members`. | `ProjectResource` | IT |
| C4.4 | Port `ProjectAccessPolicy` (¿puede el usuario X leer o escribir en el proyecto Y?), reutilizado por F, G y H. | `service.project` | unit tests |

**DoD:** proyectos y ajustes con control de acceso.

### C5 · Configuración global e historial
**Objetivo:** `config` + `ConfigProvider` (idea v1) y `history`. **Depende de:** C3. **Diseño:** DISENO_REST_UNIFICADO §6; Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| C5.1 | Port `ConfigProvider` con capas: variables de entorno / propiedades del sistema > tabla `config` > valores por defecto; caché y `reload()`. | `port`, `service.config`, `persistence.config` | unit tests de precedencia |
| C5.2 | `ConfigResource` (`/admin/config`, `/admin/config/{key}`); claves marcadas como secretas nunca se devuelven. | `web.resource.admin` | IT |
| C5.3 | Historial: evento CDI `EntityChangedEvent`, `HistoryRecorder` (observer → tabla `history`) y emisión desde los servicios de C2, C4 y C5. | `service.history`, `persistence.history` | unit test |
| C5.4 | `HistoryResource` (`/history` con filtros y paginación, rol AUDITOR). | `web.resource.admin` | IT |

**DoD:** configuración por capas y auditoría de cambios.

---

## Grupo D · Sistema de jobs (Orchestrator v1)

### D1 · Dominio de jobs
**Objetivo:** modelo y reglas de estado. **Depende de:** B2. **Diseño:** DISENO_CLASES_REST §3.3, §4.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D1.1 | `JobId`, `JobType` (§5.5 + Anexo A.4), `ExecutionLane` y `JobStatus` con `canTransitionTo` (tabla §4.1). | `domain.job` | unit test de toda la tabla |
| D1.2 | `Job` (con `transitionTo`, lanza `ConflictException`), `JobPayload`, `JobEvent`, `JobResult` y `JobQuery`. | `domain.job` | unit tests |

**DoD:** reglas de estado probadas.

### D2 · Persistencia de jobs
**Objetivo:** `job_queue` + `job_events`. **Depende de:** D1, C1.3. **Diseño:** DISENO_CLASES_REST §3.7

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D2.1 | Port `JobRepository` (§3.4). | `port` | compila |
| D2.2 | `JobEntity` (`@Version`, `event_seq`), `JobEventEntity` y `JobEntityMapper`. | `persistence.job` | compila |
| D2.3 | `JpaJobRepository` (transiciones válidas, `appendEvent` con secuencia atómica, `findByStatus`). | `persistence.job` | IT del repositorio |

**DoD:** jobs y eventos persistentes.

### D3 · Ejecución
**Objetivo:** ejecutar handlers en carriles. **Depende de:** D2. **Diseño:** DISENO_CLASES_REST §3.3, §5.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D3.1 | `JobHandler`, `JobContext`, `DefaultJobContext` y `JobHandlerRegistry` (`Instance<JobHandler>`; falla si hay tipos duplicados). | `service.job` | unit test |
| D3.2 | `JobEventPublisher` (suscriptores en memoria + `appendEvent`). | `service.job` | unit test |
| D3.3 | `JobExecutor`: carril PARALLEL (`ManagedExecutorService`) y carril SERIAL (un hilo de `ManagedThreadFactory`); estados RUNNING → final. | `service.job` | unit test con executor falso |
| D3.4 | Cancelación (`requestCancel`, `CANCELLING`) y timeouts por tipo (`stackflow.jobs.timeout.<TYPE>`). | `JobExecutor` | unit tests |
| D3.5 | Reintentos por tipo (`stackflow.jobs.retries.<TYPE>`, backoff), como el "reintenta jobs si hay fallos" de v1. | `JobExecutor` | unit test |
| D3.6 | `recoverOrphans()` al arrancar (`@Startup`): RUNNING/CANCELLING → FAILED `SERVER_RESTART`. | `service.job.JobRecovery` | IT |
| D3.7 | `TestEchoJobHandler` (`TEST_ECHO`), activo solo con `stackflow.jobs.testEcho=true`. | `service.job.test` | unit test |

**DoD:** un job TEST_ECHO recorre todos los estados.

### D4 · API REST de jobs
**Objetivo:** `/jobs…`. **Depende de:** D3, A3, C3. **Diseño:** DISENO_REST_UNIFICADO §5.5; DISENO_CLASES_REST §5.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D4.1 | `JobService` (submit, get, list, events, result, cancel) con control de acceso (owner o ADMIN). | `service.job.JobService` | unit tests |
| D4.2 | `JobApiMapper` + `JobResource` (POST 202 + Location; GET list/id; events?after; result 409; DELETE). | `web.resource.job` | IT con TEST_ECHO |

**DoD:** ciclo completo vía HTTP.

### D5 · Eventos en tiempo real
**Objetivo:** `/ws/jobs/{id}`. **Depende de:** D4. **Diseño:** DISENO_CLASES_REST §3.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D5.1 | `JobEventsSocket` (`@ServerEndpoint`), autenticación por token en el handshake y suscripción al publisher. | `web.websocket` | IT con cliente WS |
| D5.2 | Límite de mensajes en cola por sesión y cierre limpio al terminar el job. | `JobEventsSocket` | unit test |

**DoD:** eventos en vivo por WebSocket.

### D6 · Tareas de post-proceso
**Objetivo:** tabla `post_process_tasks` (encadenar jobs). **Depende de:** D4, A3. **Diseño:** Anexo A.2, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D6.1 | Dominio, entidad y repositorio de `PostProcessTask`. | `*.postprocess` | IT del repositorio |
| D6.2 | `PostProcessService`: observa `JobFinishedEvent` y lanza las tareas que coinciden (`onStatus`). | `service.job` | unit test |
| D6.3 | `GET/POST /jobs/{id}/post-process-tasks`. | `JobResource` | IT |

**DoD:** jobs encadenados.

### D7 · Schedules (cron)
**Objetivo:** tabla `schedules` (scheduleJob de v1). **Depende de:** D4, A3. **Diseño:** Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| D7.1 | Dominio, entidad y repositorio de `Schedule`; validación de cron (`cron-utils`). | `*.schedule` | unit test |
| D7.2 | `ScheduleService` con `ManagedScheduledExecutorService`: calcula el siguiente disparo, no solapa ejecuciones y rearma al arrancar. | `service.schedule` | unit test con reloj falso |
| D7.3 | `ScheduleResource` (CRUD, pause y resume). | `web.resource.job` | IT |

**DoD:** jobs programados.

---

## Grupo E · MerLabCore y stack RPN

### E1 · Integración de MerLabCore
**Objetivo:** usar la librería sin modificarla. **Depende de:** B4, D3. **Diseño:** DISENO_REST_UNIFICADO §8.2; DISENO_CLASES_REST §3.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E1.1 | 👤 `mvn install` de MerLabCore (sin cambiar código) y anotar la versión instalada. | — | jar en `~/.m2` |
| E1.2 | Dependencia en `stackflow-adapter-merlabcore` con exclusiones (JavaFX, XChart, waffle-jna, driver MariaDB) y comprobación del tamaño y el despliegue del WAR. | `pom.xml` | despliega |
| E1.3 | Port `MlEnginePort` y dominio (`AlgorithmInfo`, `ExampleInfo`, `MlRunResult`, `SignalData`, `EngineHealth`). | `port`, `domain.ml` | compila |
| E1.4 | `MerLabCoreEngineAdapter` (esqueleto + `health()`: el catálogo carga) e integración en `HealthService`. | `adapter.merlabcore` | `/health/ready` muestra merlabcore |

**DoD:** MerLabCore dentro del WAR, sano.

### E2 · Catálogo de algoritmos
**Objetivo:** `/ml/algorithms…` y `/ml/examples`. **Depende de:** E1, A4. **Diseño:** DISENO_REST_UNIFICADO §5.2; DISENO_CLASES_REST §3.5

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E2.1 | `AlgorithmCatalogReader` + `MerLabCoreMapper` (`AlgorithmDescriptor` → `AlgorithmInfo`). | `adapter.merlabcore` | unit test (recuento = catálogo) |
| E2.2 | Lista de ejemplos a partir de los descriptores (`exampleClass`/`cliRoute`); `ExampleRegistry` solo expone texto formateado. | `adapter.merlabcore` | unit test |
| E2.3 | `MlService` (list, filtro por `q`/`status`/`category`, get, components, catalog, examples). | `service.ml` | unit tests con port falso |
| E2.4 | `MlAlgorithmResource` y `MlExampleResource` (GETs) + recuentos en `/info`. | `web.resource.ml` | IT |

**DoD:** catálogo navegable por REST.

### E3 · Ejecución de algoritmos y ejemplos
**Objetivo:** `run algorithm` / `run <key>` por REST. **Depende de:** E2, D4. **Diseño:** DISENO_CLASES_REST §5.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E3.1 | `ExampleExecutor` (envuelve `ExampleRunner.run`; carril SERIAL). | `adapter.merlabcore` | unit test |
| E3.2 | `MlAlgorithmJobHandler` y `MlExampleJobHandler`. | `service.ml` | unit tests |
| E3.3 | `POST /ml/algorithms/{id}/runs` y `/ml/examples/{key}/runs` (202; `mode=sync` con timeout); 404 y 409. | `web.resource.ml` | IT con un algoritmo real (p. ej. KMeans) |

**DoD:** ejecución asíncrona con resultado y gráfico.

### E4 · Stack RPN
**Objetivo:** RPNStack v1 (push, pop, apply, serialize) sobre MerLabCore. **Depende de:** E1, A4. **Diseño:** Anexo A.1, A.3 (Stack)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E4.1 | **Spike:** API de `rpn` / `SignalStack` / `SignalPipelineFacade` de MerLabCore (operaciones, aridad, serialización). | Umsetzungsplan | tabla de operaciones |
| E4.2 | `StackOperationCatalog` + `GET /stack/operations`. | `adapter.merlabcore`, `web.resource.stack` | IT |
| E4.3 | `RpnEvaluator` + `POST /stack/evaluate` (stateless). | `adapter.merlabcore`, `service.stack` | unit + IT |
| E4.4 | `StackSessionService`: sesiones en memoria con TTL, profundidad máxima y máximo de sesiones por usuario (límites de memoria v1). | `service.stack` | unit tests de límites |
| E4.5 | Endpoints de sesión: crear, ver, push, pop, apply, snapshot y borrar. | `web.resource.stack` | IT |
| E4.6 | Snapshot serializable (JSON) del stack, reutilizado por los escenarios (G3). | `service.stack` | round-trip |

**DoD:** stack con estado utilizable por REST.

### E5 · Paridad con el miniserver de MerLabCore
**Objetivo:** base mínima garantizada. **Depende de:** E3, E4. **Diseño:** DISENO_REST_UNIFICADO §5.6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| E5.1 | Un IT por cada fila MerLabCore de la tabla §5.6. | `MerLabCoreParityIT` | verde |

**DoD:** todo lo del miniserver existe en el REST.

---

## Grupo J · RAG (MerLabRAG, opcional)

### J1 · API RAG desacoplada (sin MerLabRAG)
**Objetivo:** los 13 endpoints `/rag/*` existen y responden 503 sin RAG. **Depende de:** D4, A9. **Diseño:** DISENO_CLASES_REST §3.4, §3.9, §3.10, §4.6, §5.6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J1.1 | Port `RagEnginePort` completo (incluido `isInstalled()`) y dominio RAG (`RagQuery`, `RagResult`, `RagMode`, `ConversationInfo`, `ConversationMessage`, `RunLogDetail`…). | `port`, `domain.rag` | compila |
| J1.2 | `UnavailableRagEngine` (bean por defecto) y `RagNotInstalledException`. | `service.rag` | unit test |
| J1.3 | `RagService` + guarda en `JobService` para los tipos `RAG_*`. | `service.rag`, `service.job` | unit tests |
| J1.4 | `RagApiMapper` + todos los resources RAG (query, usecases, flows, conversations ×4, scenarios, run-logs ×4, indexing). | `web.resource.rag` | compila |
| J1.5 | Handlers `RagQueryJobHandler`, `RagScenarioJobHandler` y `RagIndexingJobHandler`. | `service.rag` | unit tests |
| J1.6 | IT con `-P!rag`: todo `/rag/*` → 503 `RAG_NOT_INSTALLED`; `/health/ready` UP con `merlabrag: NOT_INSTALLED`. | `NoRagIT` | verde |

**DoD:** StackFlow funciona completo sin MerLabRAG.

### J2 · Perfil `rag` y arranque de Pegasus
**Objetivo:** Pegasus dentro de WildFly. **Depende de:** J1. **Diseño:** DISENO_REST_UNIFICADO §8.3; DISENO_CLASES_REST §3.6, §4.3, §5.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J2.1 | 👤 `mvn install` de MerLabRAG (sin cambiar código) y anotar la versión. | — | jar en `~/.m2` |
| J2.2 | Dependencias del adaptador (merlab-rag, excluir `slf4j-simple`) y `jboss-deployment-structure.xml` (conflictos Jackson, Netty/gRPC). | POMs, `WEB-INF` | `-Prag` despliega |
| J2.3 | `PegasusServerConfig` (desde `ConfigProvider`). | `adapter.merlabrag` | unit test |
| J2.4 | `PegasusRuntimeProducer`: `RagApplicationBuilder`, Ollama, vector store, memoria, perfiles de casos de uso y flows; `RuntimeState`. | `adapter.merlabrag` | arranca en READY o DEGRADED |
| J2.5 | Sonda periódica de salud y `POST /admin/rag/reload`. | `adapter.merlabrag`, `web.resource.admin` | estados de §4.3 |
| J2.6 | `MerLabRagEngineAdapter` (`@Alternative @Priority(100)`): `health`, `useCases`, `flows`. | `adapter.merlabrag` | `/rag/usecases` con datos reales |

**DoD:** con `-Prag` el motor arranca; sin él, nada cambia.

### J3 · Consultas RAG
**Objetivo:** `POST /rag/query` real. **Depende de:** J2. **Diseño:** DISENO_CLASES_REST §4.2, §5.1, §7.1, §7.2

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J3.1 | `PegasusResultMapper` (§7.1) y `RagMode` ↔ `OrchestrationMode`. | `adapter.merlabrag` | unit tests |
| J3.2 | `CurrentJobContext`, `StackflowObservabilityBridge` y `StackflowAuditSink`. | `adapter.merlabrag` | unit tests |
| J3.3 | `FlowUseCaseExecutor` y `RagUseCaseDispatcher` (fallback a `defaultFlow`). | `adapter.merlabrag` | unit tests |
| J3.4 | Query síncrona extremo a extremo: 200, 422, 503 y 504 según §7.2. | adapter + web | 👤 IT con Ollama en marcha |

**DoD:** consulta real con traza y eventos.

### J4 · Casos de uso con Runner propio
**Objetivo:** document, exegesis, music, diagnostics y escenarios técnicos. **Depende de:** J3. **Diseño:** DISENO_CLASES_REST §3.6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J4.1 | **Spike:** inventario de `useCaseId` → Runner (lectura del cableado del miniserver, sin tocarlo). | Umsetzungsplan | tabla completa |
| J4.2 | `DocumentUseCaseExecutor` y `ExegesisUseCaseExecutor`. | `adapter.merlabrag` | unit tests |
| J4.3 | `MusicUseCaseExecutor` y `DiagnosticsUseCaseExecutor`. | `adapter.merlabrag` | unit tests |
| J4.4 | `TechnicalScenarioExecutor` + `POST /rag/scenarios/{name}/runs`. | adapter + web | IT |
| J4.5 | Prueba de concurrencia de Pegasus → decidir el carril de los handlers RAG y anotarlo. | Umsetzungsplan | decisión escrita |

**DoD:** todos los casos de uso ejecutables por REST.

### J5 · Memoria de conversación y logs
**Objetivo:** los 4 endpoints añadidos. **Depende de:** J3. **Diseño:** DISENO_CLASES_REST §3.9, §4.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J5.1 | Get, messages y delete vía `MemoryPort`. | `adapter.merlabrag` | IT |
| J5.2 | 👤 Confirmar el prefijo de claves de Redis; `RedisConversationLister` (SCAN) y `UnsupportedConversationLister`. | `adapter.merlabrag` | IT con Redis / 409 sin Redis |
| J5.3 | `RunLogReader`: list, get (completo), raw y analysis. | `adapter.merlabrag` | IT |

**DoD:** conversaciones y logs gestionables.

### J6 · Indexado
**Objetivo:** `POST /rag/indexing`. **Depende de:** J3. **Diseño:** DISENO_REST_UNIFICADO §5.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J6.1 | **Spike:** API de `pegasus.runtime.indexing`. | Umsetzungsplan | decisión |
| J6.2 | Indexado en el adaptador + `RagIndexingJobHandler` (carril SERIAL). | adapter | IT |

**DoD:** indexado asíncrono por REST.

### J7 · Paridad y regresión sin RAG
**Objetivo:** base mínima del miniserver de Pegasus. **Depende de:** J4, J5. **Diseño:** DISENO_REST_UNIFICADO §5.6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| J7.1 | Un IT por cada fila Pegasus de la tabla §5.6 (`-Prag`). | `PegasusParityIT` | verde |
| J7.2 | Repetir `NoRagIT` con `-P!rag` (no hay regresión). | `NoRagIT` | verde |

**DoD:** RAG completo y opcional demostrado.

---

## Grupo F · Datos, señales y BD externas

### F1 · Señales y datos
**Objetivo:** `signals` y `signal_data`. **Depende de:** C4, A5. **Diseño:** Anexo A.2, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F1.1 | Dominio, entidades y repositorio de `Signal` y `SignalData`. | `*.signal` | IT del repositorio |
| F1.2 | `SignalService` (acceso por proyecto, `ProjectAccessPolicy`). | `service.signal` | unit tests |
| F1.3 | `SignalResource` CRUD. | `web.resource.data` | IT |
| F1.4 | Subida de datos JSON y CSV (`EntityPart`/multipart, límite de tamaño) a `/signals/{id}/data`. | `web.resource.data`, `service.signal` | IT |
| F1.5 | Lectura paginada `GET /signals/{id}/data?offset&limit`. | idem | IT |

**DoD:** señales con datos subidas y leídas por REST.

### F2 · Grupos de señales y datasets
**Objetivo:** `signal_groups`, `signal_group_signals` y `datasets`. **Depende de:** F1. **Diseño:** Anexo A.2, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F2.1 | Grupos: entidad, servicio, CRUD y añadir/quitar señales. | `*.signalgroup` | IT |
| F2.2 | Datasets: entidad, servicio y CRUD (metadatos y ubicación). | `*.dataset` | IT |

**DoD:** datos organizados.

### F3 · Procesamiento por chunks
**Objetivo:** requisito de memoria v1 (no cargar todo en RAM). **Depende de:** F1. **Diseño:** Anexo A.1 (BatchProcessor)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F3.1 | Port `ChunkReader<T>` + implementación JPA con paginación por clave (keyset). | `port`, `persistence.chunk` | IT con 100 000 puntos |
| F3.2 | `ChunkConfig` (tamaño por defecto y máximo, desde config) y `MemoryGuard` (límite de elementos en memoria). | `service.chunk` | unit tests |

**DoD:** lectura en bloques con memoria acotada.

### F4 · BD externas (DBAdapter v1)
**Objetivo:** `db_connections`, prueba e importación. **Depende de:** F3, C5. **Diseño:** Anexo A.1, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| F4.1 | Port `SecretCipher` (AES-GCM, clave de la configuración) para las contraseñas guardadas. | `port`, `web.security` | unit test |
| F4.2 | `DbConnection`: dominio, entidad, servicio y CRUD (la respuesta nunca incluye la contraseña). | `*.dbconnection` | IT |
| F4.3 | `POST /db-connections/{id}/test` (JDBC con timeout; MariaDB y PostgreSQL). | `persistence.external` | IT con la BD local |
| F4.4 | Job `DATA_IMPORT`: solo `SELECT`, lectura por chunks hacia `signal_data`. | `service.data`, `persistence.external` | IT |
| F4.5 | **Spike:** NoSQL (MongoDB) como fuente futura; anotar enfoque. | Umsetzungsplan | nota |

**DoD:** importar datos de una BD externa.

---

## Grupo G · Pipelines, batch, escenarios y archivo

### G1 · Pipelines
**Objetivo:** `pipelines`, `pipeline_steps`, `pipeline_versions` y `pipeline_current_version`. **Depende de:** E4, F1, A6. **Diseño:** DISENO_REST_UNIFICADO §5.3; Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G1.1 | Dominio y entidades de pipelines, pasos y versiones. | `*.pipeline` | IT del repositorio |
| G1.2 | `PipelineService`: los pasos se validan contra `StackOperationCatalog`; se guardan las últimas N versiones (configurable). | `service.pipeline` | unit tests |
| G1.3 | `PipelineResource` (CRUD, steps, versions, current-version). | `web.resource.pipeline` | IT |

**DoD:** pipelines versionados.

### G2 · Ejecución batch (BatchProcessor + Workers v1)
**Objetivo:** ejecutar un pipeline sobre datos grandes. **Depende de:** G1, F3, D4. **Diseño:** Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G2.1 | `PipelineRunner`: aplica los pasos (RPN vía `MlEnginePort`) a un chunk. | `service.pipeline` | unit test |
| G2.2 | `BatchProcessor`: divide en chunks, reparte a workers del carril PARALLEL, emite eventos de progreso y aplica una política de fallo parcial. | `service.batch` | unit tests |
| G2.3 | `MlPipelineJobHandler` + `POST /pipelines/{id}/runs`; el resultado se guarda como señal de salida o JSON. | `service.pipeline`, web | IT con datos grandes |

**DoD:** batch por chunks con memoria acotada.

### G3 · Escenarios (ScenarioManager v1)
**Objetivo:** `scenarios`, `scenario_entities` y `scenario_runs`. **Depende de:** G2, E4.6. **Diseño:** Anexo A.1, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G3.1 | Dominio y entidades. | `*.scenario` | IT del repositorio |
| G3.2 | `ScenarioService`: crear un snapshot (stack, parámetros y entidades referenciadas), cargar y listar. | `service.scenario` | unit tests |
| G3.3 | `MlScenarioJobHandler` + `POST /scenarios/{id}/runs` + historial `GET /scenarios/{id}/runs`. | service + web | IT |

**DoD:** escenarios reproducibles.

### G4 · Comparación y escenarios golden
**Objetivo:** compare + regresión (v1). **Depende de:** G3. **Diseño:** Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G4.1 | `ScenarioComparator` (diferencias por clave con tolerancia). | `service.scenario` | unit tests |
| G4.2 | `GET /scenarios/{id}/runs/{a}/compare/{b}`. | web | IT |
| G4.3 | Marca golden (`PUT /scenarios/{id}/golden`) y job `SCENARIO_REGRESSION` (`POST /scenarios/golden/runs`) con informe. | service + web | IT |

**DoD:** regresión automática con escenarios golden.

### G5 · Archivo (versiones y rollback)
**Objetivo:** módulo Archiving v1. **Depende de:** G3. **Diseño:** Anexo A.1, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| G5.1 | `V0xx__archives.sql` + dominio, entidad y repositorio de `Archive`. | `*.archive` | IT |
| G5.2 | `ArchiveService`: guarda versiones de resultados, escenarios y pipelines al cambiar; retención configurable. | `service.archive` | unit tests |
| G5.3 | `ArchiveResource` (list, get y rollback). | web | IT |

**DoD:** cualquier resultado se puede recuperar.

---

## Grupo H · Modelos (MLCore v1)

### H1 · Modelos y ficheros
**Objetivo:** `models` y `model_files`. **Depende de:** C4, A7. **Diseño:** Anexo A.2, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H1.1 | Dominio, entidades y repositorio. | `*.model` | IT |
| H1.2 | `ModelService` + `ModelResource` CRUD. | service + web | IT |
| H1.3 | Subida y descarga de ficheros (streaming, límite de tamaño, SHA-256). | web + service | IT |

**DoD:** modelos con sus binarios.

### H2 · Entrenamiento
**Objetivo:** train v1. **Depende de:** H1, E3. **Diseño:** Anexo A.3, A.4

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H2.1 | **Spike:** API de entrenamiento de MerLabCore (`nn/trainer`, `ml`): qué algoritmos se entrenan y cómo se serializa el modelo. | Umsetzungsplan | tabla |
| H2.2 | `MlEnginePort.train(...)` en el adaptador. | port + adapter | unit test |
| H2.3 | Job `ML_TRAIN` + `POST /models/{id}/train`: guarda el fichero y las métricas. | service + web | IT |

**DoD:** entrenar por REST.

### H3 · Predicción
**Objetivo:** predict v1. **Depende de:** H2. **Diseño:** Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| H3.1 | `MlEnginePort.predict(...)` + caché de modelos cargados (LRU con límite de memoria). | adapter | unit tests |
| H3.2 | `POST /models/{id}/predict` síncrono y asíncrono (`ML_PREDICT`). | service + web | IT |

**DoD:** predecir con un modelo guardado.

---

## Grupo I · Streaming y alertas

### I1 · Definición de streams
**Objetivo:** `streams`, `alert_rules` y `alert_events`. **Depende de:** G1, A8. **Diseño:** Anexo A.1, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I1.1 | `V0xx__streams_alerts.sql`. | migración | tablas creadas |
| I1.2 | Dominio `Stream` con estados CREATED, RUNNING, STOPPED y FAILED (añadir su diagrama de estado al diseño). | `domain.stream`, `design/` | unit test |
| I1.3 | `StreamService` + `StreamResource` CRUD. | service + web | IT |

**DoD:** streams definibles.

### I2 · Buffer, ventanas y StreamProcessor
**Objetivo:** procesamiento en ventana con memoria acotada. **Depende de:** I1. **Diseño:** Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I2.1 | `StreamBuffer` acotado con política de descarte. | `service.stream` | unit tests |
| I2.2 | `WindowAssembler` (tumbling y sliding, por cantidad o por tiempo). | `service.stream` | unit tests |
| I2.3 | `StreamProcessor`: ejecuta el pipeline o RPN por ventana en el carril PARALLEL; start y stop. | `service.stream`, web | IT |

**DoD:** un stream procesa ventanas.

### I3 · Ingesta
**Objetivo:** EventListener v1 (REST, WebSocket, JMS). **Depende de:** I2. **Diseño:** Anexo A.1, A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I3.1 | `POST /streams/{id}/events` (lotes; se admite API key). | web | IT |
| I3.2 | `WS /ws/streams/{id}/ingest`. | `web.websocket` | IT |
| I3.3 | 👤 Cola JMS en WildFly (`java:/jms/queue/StackflowStreamQueue`) + `StreamIngestMDB` (la idea de `SignalProcessorMDB` v1, reescrita). | `web.jms`, `scripts/wildfly/jms.cli` | IT |

**DoD:** tres vías de entrada.

### I4 · Resultados y alertas
**Objetivo:** Alerting v1. **Depende de:** I3. **Diseño:** Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I4.1 | `WS /ws/streams/{id}/results`. | `web.websocket` | IT |
| I4.2 | `AlertRuleService` (GT, LT, RATE) + `AlertRuleResource`. | service + web | unit + IT |
| I4.3 | `AlertService` (persistir, `/alerts`, ack, `WS /ws/alerts`). | service + web | IT |

**DoD:** alertas en tiempo real.

### I5 · Fuentes externas (opcional)
**Objetivo:** Kafka, RabbitMQ y JForex del diseño v1. **Depende de:** I3. **Diseño:** Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| I5.1 | Port `StreamSourcePort` (start, stop, onEvent). | `port` | compila |
| I5.2 | Adaptador Kafka en el módulo `stackflow-adapter-kafka` (perfil `kafka`). | nuevo módulo | IT con Kafka local |
| I5.3 | Adaptador RabbitMQ (perfil `rabbit`). | nuevo módulo | IT |
| I5.4 | **Spike:** fuente JForex (SDK frente a proxy REST/WS); anotar decisión. | Umsetzungsplan | nota |

**DoD:** fuentes externas enchufables.

---

## Grupo K · Observabilidad y operación

### K1 · Métricas
**Objetivo:** Monitor v1 (metrics). **Depende de:** D4, I2. **Diseño:** Anexo A.3 (Monitor)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K1.1 | **Spike:** métricas en WildFly 36 (subsistema Micrometer / Prometheus); anotar. | Umsetzungsplan | decisión |
| K1.2 | Port `MetricsPort` + instrumentar jobs (contadores, duración, cola) y sesiones de stack. | port + adapter | unit test |
| K1.3 | Instrumentar streams (latencia, eventos/s) y JVM (heap, CPU). | service | métricas visibles |
| K1.4 | `GET /admin/metrics` (resumen JSON). | web | IT |

**DoD:** métricas en JSON y Prometheus.

### K2 · Alertas de sistema
**Objetivo:** alertas de umbral del Monitor v1. **Depende de:** K1, I4. **Diseño:** Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K2.1 | `SystemAlertMonitor` (memoria %, tasa de fallos de jobs, longitud de cola) → `AlertService`. | service | unit tests |

**DoD:** el sistema avisa antes de saturarse.

### K3 · Administración
**Objetivo:** estado de componentes. **Depende de:** K1. **Diseño:** DISENO_REST_UNIFICADO §5.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K3.1 | `GET /admin/components` (motores, BD, Ollama, Qdrant, Redis, Neo4j, JMS, plugins). | web + service | IT |
| K3.2 | Recarga de configuración (`ConfigProvider.reload`) desde `/admin/config`. | web | IT |

**DoD:** visión operativa completa.

### K4 · Logging estructurado
**Objetivo:** logs útiles y correlacionados. **Depende de:** B3. **Diseño:** DISENO_REST_UNIFICADO §6

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| K4.1 | Formato JSON en WildFly (script CLI) y documentación. | `scripts/wildfly/logging.cli` | log JSON |
| K4.2 | Propagar `correlationId` y `jobId` al MDC de los hilos de jobs y streams. | `service.job` | unit test |

**DoD:** cada log se puede seguir de punta a punta.

---

## Grupo L · Extensibilidad

### L1 · Plugins (PluginManager v1)
**Objetivo:** cargar, descargar y listar plugins. **Depende de:** E2, I5.1. **Diseño:** Anexo A.1, A.3 (Plugins)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| L1.1 | Módulo `stackflow-plugin-api`: `StackflowPlugin`, `AlgorithmPlugin`, `DataSourcePlugin`, `VisualizationPlugin` y `PluginContext`. | nuevo módulo | compila |
| L1.2 | `V0xx__plugins.sql` + registro de plugins (checksum y estado). | persistence | IT |
| L1.3 | `PluginManager`: carga desde `stackflow.plugins.dir` con `URLClassLoader` aislado + `ServiceLoader`; enable, disable y unload; solo checksums aprobados. | `service.plugin` | unit tests |
| L1.4 | `/admin/plugins` (list, register, enable, disable, delete). | web | IT |
| L1.5 | Los `AlgorithmPlugin` aparecen en `/ml/algorithms` (`source=plugin`) y los `DataSourcePlugin` como `StreamSourcePort`. | service | IT |
| L1.6 | Plugin de ejemplo `stackflow-plugin-example` para tests. | nuevo módulo | IT carga y ejecuta |

**DoD:** extender sin tocar el núcleo.

### L2 · Scripting (Merlynx / MCP)
**Objetivo:** `runScript` del APILayer v1. **Depende de:** E4, D4. **Diseño:** Anexo A.1, A.3 (Scripting)

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| L2.1 | 👤 **Spike:** revisar el repo `mabondano/merlynx` y la nota "MCP approach" del diseño v1; decidir lenguaje y alcance. | Umsetzungsplan | decisión |
| L2.2 | Port `ScriptEnginePort` + primer lenguaje: script RPN (secuencia de operaciones del stack). | port + service | unit tests |
| L2.3 | Sandbox: límites de tiempo, de operaciones y de memoria. | service | unit tests |
| L2.4 | `GET /scripts/languages` y `POST /scripts/run` (job `SCRIPT`). | web | IT |
| L2.5 | **Spike (opcional):** exponer StackFlow como servidor MCP (herramientas = endpoints). | Umsetzungsplan | nota |

**DoD:** pipelines por script.

---

## Grupo M · Cierre

### M1 · Pruebas extremo a extremo
**Objetivo:** cada endpoint del diseño tiene su IT. **Depende de:** todos. **Diseño:** DISENO_REST_UNIFICADO §5; Anexo A.3

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M1.1 | Matriz endpoint → IT (generada desde OpenAPI) y huecos cubiertos. | `docs/v2/TEST_MATRIX.md` | 100 % de endpoints |
| M1.2 | Ejecución completa con `-P!rag` y con `-Prag`. | — | ambos en verde |

### M2 · Rendimiento y memoria
**Objetivo:** requisitos de memoria del diseño v1. **Depende de:** G2, I2. **Diseño:** Anexo A.1

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M2.1 | Pruebas de carga: concurrencia de jobs, tamaños de chunk, profundidad del stack y throughput de streams. | `perf/` | informe |
| M2.2 | Documentar tamaños de datos recomendados y límites por defecto. | `docs/v2/LIMITS.md` | — |

### M3 · Documentación y clientes
**Objetivo:** lista para usar. **Depende de:** M1.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M3.1 | Colección Postman v2. | `docs/v2/postman/` | importable |
| M3.2 | Guía de la API generada desde OpenAPI. | `docs/v2/API.md` | — |
| M3.3 | Contrato para FlowScope (CORS, `/info.engines`, WebSockets). | `docs/v2/FLOWSCOPE.md` | — |
| M3.4 | Guía de instalación v2 (NetBeans, WildFly, MariaDB, perfil RAG opcional con Ollama, Qdrant y Redis). | `docs/v2/SETUP.md` | otra persona instala siguiendo la guía |

### M4 · Seguridad final
**Objetivo:** revisión antes del release. **Depende de:** M1.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M4.1 | Revisión tipo OWASP: autenticación en todos los endpoints, límites de entrada, secretos fuera de los logs, cabeceras de seguridad. | `docs/v2/SECURITY_REVIEW.md` | sin hallazgos abiertos |
| M4.2 | **Spike (opcional):** OAuth/OIDC (Keycloak o el OIDC de WildFly), del roadmap v1. | Umsetzungsplan | decisión |

### M5 · Release 2.0.0
**Objetivo:** cerrar la versión. **Depende de:** M1–M4.

| ID | Micropaso | Clases / archivos | Verificación |
|---|---|---|---|
| M5.1 | Versión `2.0.0`, README final y CHANGELOG. | POMs, `README.md` | build |
| M5.2 | 👤 Tag `v2.0.0` y push (con aprobación). | git | tag remoto |
| M5.3 | 👤 Decidir la rama por defecto en GitHub (`main`, `master`, `v2`). | GitHub | decisión |

**DoD del proyecto:** todos los milestones en Terminado en el tracking.
