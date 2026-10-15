# StackFlowServer v2: registro de decisiones de arquitectura

Cada decisión se registra solo cuando el usuario la confirma con **ROGER**. Las decisiones de este registro prevalecen sobre cualquier otro documento de diseño.

| ID | Fecha | Decisión | Estado |
|---|---|---|---|
| DA-001 | 15.10.2026 | Estilo del núcleo: **Opción C (mixta)**: EJB para los servicios, Java puro para el motor de cálculo | Aprobada (ROGER C) |
| DA-002 | 15.10.2026 | **Reglas R1–R8** de código que mantienen barato un futuro host Spring | Aprobada (ROGER) |
| DA-003 | 15.10.2026 | **Cada ROGER cierra el paso del todo, incluido el push de `v2`**; sin pendientes entre pasos | Indicada por el usuario |

---

## DA-001 · Opción C: EJB para servicios, Java puro para el motor

**Contexto.** Había tres opciones: (A) EJB en todas las clases, como en v1; (B) núcleo en Java puro con un EJB delgado por servicio y una clase de "cableado" CDI; (C) mixta. Las notas de Obsidian piden un núcleo independiente del framework para un posible host Spring futuro. Al mismo tiempo, el usuario domina EJB y nunca ha trabajado con Spring.

**Decisión.**

| Tipo de clase | Estilo | Ejemplos |
|---|---|---|
| Servicios de aplicación (tocan BD, seguridad, transacciones, hilos, timers o mensajes) | **EJB**, convención v1: `XServiceLocal` (`@Local`) + `XServiceBean` (`@Stateless`, `@Singleton`…) | `UserServiceBean`, `OrchestratorServiceBean`, `WorkerServiceBean`, `PipelineExecutorBean`, `ScenarioManagerBean`, `ArchivingServiceBean`, `MonitorServiceBean` |
| Acceso a datos | **EJB**: `XDaoLocal` + `XDaoBean` con `EntityManager` | `UserDaoBean`, `JobQueueDaoBean`, `SignalDaoBean` |
| Entrada (REST, WebSocket, JMS) | Jakarta: `XResource`, `XSocketEndpoint`, `XMDB`, `ExceptionMapper` | `UserResource`, `SignalProcessorMDB` |
| Motor de cálculo y dominio (solo calculan) | **Java puro, sin anotaciones** | `RPNStack`, `StackElement`, `Operation`, ejecución de pasos de pipeline, comparación de escenarios, ventanas de streaming, chunks; en el futuro, parser e intérprete de Merlynix |
| Ports hacia motores externos | **Interfaces Java puras** | `MerLabCorePort`, `PegasusRagPort`, `ModelForgePort`, `MerlynixPort` |

**Regla para agentes:**
> Si la clase toca base de datos, seguridad, transacciones, hilos, timers o mensajes → EJB. Si solo calcula → Java puro.

Los EJB crean los objetos del motor con `new`. **No hay** una clase central de "cableado". Si la lógica de un servicio se vuelve compleja, se extrae a una clase Java pura que el EJB usa (caso por caso, no como regla general).

**Comprobación automática (ArchUnit):** los paquetes de dominio, motor (`rpn`, `engine`) y ports no pueden importar `jakarta.*`, salvo `jakarta.validation` en los DTOs.

**Tests:**
- motor y dominio: JUnit puro;
- EJB: JUnit + Mockito (`@Mock` para los `XDaoLocal`/ports, `@InjectMocks` para el bean), sin servidor;
- transacciones, seguridad y despliegue: Arquillian sobre WildFly.

**Consecuencias.**
- ✅ Forma de trabajo conocida (NetBeans + WildFly + EJB); tests rápidos del motor; pocas clases; sin errores de cableado CDI.
- ❌ Un host Spring futuro tendría que reescribir los beans (el motor, los ports, los DTOs y las entidades sí se reutilizan). Ver [FUTURO_SPRING.md](FUTURO_SPRING.md).

**Aplicado** en la Revisión 2 del diseño y del roadmap (paso 0.5.4): nombres, módulos y estilo. Los documentos anteriores se leen con la tabla de traducción de DISENO_REVISION_2 §11.

---

## DA-002 · Reglas de código R1–R8

**Contexto.** Con DA-001 (opción C), un host Spring futuro reutiliza el motor, los ports, los DTOs y las entidades. Las reglas de [FUTURO_SPRING.md](FUTURO_SPRING.md) §4 hacen que esa reutilización sea real y barata. No añaden funcionalidad: dicen **cómo** escribir el código del roadmap.

**Decisión.** Las reglas son obligatorias en todos los micropasos:

| # | Regla | Comprobación |
|---|---|---|
| R1 | Dominio, motor RPN y ports sin `jakarta.*` (salvo `jakarta.validation` en DTOs). | ArchUnit |
| R2 | Los beans EJB son delgados: coordinan y delegan. La lógica larga o con varios caminos va a una clase Java pura que el bean usa con `new`. | revisión en cada micropaso |
| R3 | Los adaptadores de motores (MerLabCore, Pegasus, ModelForge) son clases Java normales, expuestas por un producer CDI pequeño; sin `@Stateless` ni lógica de contenedor. | ArchUnit (sin `jakarta.ejb` en esos módulos) |
| R4 | Los servicios acceden a datos siempre por `XDaoLocal`; solo los `*DaoBean` usan `EntityManager`. | ArchUnit |
| R5 | Los tests de integración de la API son HTTP de caja negra, con la URL base como parámetro (`-Dstackflow.baseUrl`). | convención de los `*IT` |
| R6 | La configuración se lee solo con `ConfigProvider` y claves de `ConfigKeys`. | revisión |
| R7 | El código Java puro (motor, adaptadores de motores) usa `java.util.logging` o SLF4J, no JBoss Logging. | ArchUnit |
| R8 | La lógica de cada `ExceptionMapper` y filtro vive en una clase Java pura (p. ej. `ErrorDTOFactory`). | revisión |

**Consecuencias.** ✅ Un posible host Spring reescribiría solo los beans y la configuración (con Jersey, ni siquiera los resources). ❌ Unas pocas clases auxiliares más (factorías, producers de adaptadores).

---

## DA-003 · Sin pendientes: cada ROGER cierra el paso, incluido el push

**Contexto.** Al terminar 0.1.1 quedó "push del tag pendiente de aprobación", y el usuario no sabía qué cubría el siguiente ROGER. Los milestones existen precisamente para que el estado sea claro.

**Decisión.** Cada micropaso termina **cerrado del todo**: implementación, verificación, TRACKING, UMSETZUNGSPLAN, commit y **push de la rama `v2`**. No se deja nada "pendiente de aprobación" entre un ROGER y el siguiente. Si algo no se puede terminar, el paso queda **Bloqueado**, con el motivo dicho explícitamente.

**Detalles técnicos.**
- Push: `git push origin v2`. En este PC, Git con OpenSSL fallaba con "SSL certificate problem"; desde el 15.10.2026 git usa schannel (almacén de Windows) de forma global, y Maven usa `MAVEN_OPTS=-Djavax.net.ssl.trustStoreType=Windows-ROOT` (variable de usuario).
- Solo fast-forward: nunca `--force`, nunca reescribir historia publicada.
- `master` y los tags existentes no se tocan.

