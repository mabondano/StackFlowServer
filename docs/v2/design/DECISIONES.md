# StackFlowServer v2: registro de decisiones de arquitectura

Cada decisión se registra solo cuando el usuario la confirma con **ROGER**. Las decisiones de este registro prevalecen sobre cualquier otro documento de diseño.

| ID | Fecha | Decisión | Estado |
|---|---|---|---|
| DA-001 | 15.10.2026 | Estilo del núcleo: **Opción C (mixta)**: EJB para los servicios, Java puro para el motor de cálculo | Aprobada (ROGER C) |

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

**Pendiente de aplicar:** el ROADMAP y los documentos de diseño todavía usan nombres anteriores (`JobService`, `MlEnginePort`, `stackflow-service`…). Se adaptarán en la "Revisión 2" del diseño, que necesita su propio ROGER.
