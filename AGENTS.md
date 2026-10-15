# Instrucciones para agentes (Codex, Claude)

Este repositorio, en la rama `v2`, es la implementación nueva de **StackFlowServer**: la runtime Jakarta EE 10 (WildFly 36) de **MerLabCore** (stack RPN + ML sobre señales y datos; primeras aplicaciones: audio y trading). **PegasusRAG** y **ModelForge** (remoto, Mac mini) son motores opcionales y separados. **Merlynix** llega al final.

## Antes de hacer nada

1. Lee [docs/v2/README.md](docs/v2/README.md): protocolo **ROGER** y reglas.
2. Lee [docs/v2/TRACKING.md](docs/v2/TRACKING.md), sección "Paso actual".
3. Lee el milestone de ese paso en [docs/v2/ROADMAP.md](docs/v2/ROADMAP.md) y las secciones de diseño que cita. Precedencia: `design/DECISIONES.md` > `design/DISENO_REVISION_2.md` > los demás `design/DISENO_*.md`.

## Cómo trabajar

- **Un micropaso por turno.** Implementa, verifica, actualiza TRACKING y UMSETZUNGSPLAN, haz un commit local `[<ID>] <resumen>` y **detente**. Continúa solo cuando el usuario escriba `ROGER` (o `ROGER <ID>`).
- Verificación mínima: `mvn -q -P!pegasus,!modelforge clean verify` en verde. Desde J2, también `-Ppegasus`; desde H3, también `-Pmodelforge`.
- Estilo (DA-001): EJB para servicios y DAOs (`XServiceLocal`/`XServiceBean`, `XDaoLocal`/`XDaoBean`); Java puro para el motor (dominio, `StackSession`, `PipelineRunner`, importadores).
- Pasos 👤: prepara todo, explica qué debe hacer el usuario y espera su confirmación.
- Pasos Spike: sin código de producción; escribe la decisión en el UMSETZUNGSPLAN.

## Nunca

- Modificar MerLabCore o PegasusRAG/MerLabRAG (ni sus miniservers). Se usan como JAR desde `~/.m2`. Tampoco reimplementar algoritmos de MerLabCore.
- Entrenar modelos o ejecutar LLMs en el PC: eso va por API a los Mac mini (ModelForge, Ollama).
- Tocar la rama `master`, el tag `v1-legacy` o el código v1.
- Copiar código de `genai-java-rest-api` (proyecto de la empresa).
- Guardar secretos en el código o en git.
- Hacer push o crear tags remotos sin aprobación.

## Datos del entorno

- Java 17 · Jakarta EE 10 · WildFly 36 (`C:\merLab\projects\StackFlowServer\wildfly-36.0.1.Final`) · HTTP 8090
- URL base: `http://localhost:8090/stackflow/api/v1`
- MariaDB `localhost:3307`, BD `stackflowdb`, DataSource `java:/jdbc/StackflowDS`
- IDE del usuario: NetBeans (proyecto Maven multi-módulo)
- Idioma con el usuario: español. Diagramas y código: inglés.
