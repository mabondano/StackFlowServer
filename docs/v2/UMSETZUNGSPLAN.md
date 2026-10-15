# StackFlowServer v2: Umsetzungsplan

Registro de implementación. **Una entrada por micropaso terminado**, la más reciente al final. Las entradas son cortas: qué se hizo, qué clases intervinieron, cómo se verificó y qué sigue. Cada entrada termina con el siguiente paso y la espera de **ROGER**.

## Plantilla

```markdown
### [<ID>] <título corto> · <dd.mm.aaaa>
- **Agente:** Codex | Claude
- **Qué se hizo:** <1–3 frases>
- **Clases / archivos:** `<Clase1>`, `<Clase2>`, `<archivo>`
- **Verificación:** `<comando>` → OK (<n> tests)
- **Decisiones:** <solo si hubo; si no, "—">
- **Commit:** `<hash corto>`
- **Siguiente:** <ID> <título> → esperando ROGER
```

Para un **Spike**, "Qué se hizo" se sustituye por **Pregunta**, **Opciones** y **Decisión**.

---

## Registro

### [0.1.2] Rama v2 creada · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** rama `v2` creada desde `master` (`79d1e53`). Primero se usó una carpeta aparte; a petición del usuario se cerró el merge pendiente de `master` con un commit normal (`53ef018`), se quitó esa carpeta y `stackflow` cambió a la rama `v2`.
- **Clases / archivos:** carpeta `C:\merLab\projects\StackFlowServer\stackflow`
- **Verificación:** `git branch --show-current` → `v2`; `master` sigue con su merge pendiente.
- **Decisiones:** se trabaja en la misma carpeta `stackflow`; en NetBeans se cambia de rama, no de proyecto.
- **Commit:** — (sin cambios de código)
- **Siguiente:** 0.5.1

### [0.5.1] Documentación de trabajo v2 · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** creados README, ROADMAP (14 grupos, 73 milestones, 275 micropasos), TRACKING (generado desde el roadmap) y este Umsetzungsplan. Copia canónica del diseño en `design/`, con los roles definitivos y el Anexo A (todos los módulos del diseño v1: streaming, plugins, schedules, archivo, modelos, BD externas, scripting…).
- **Clases / archivos:** `docs/v2/README.md`, `ROADMAP.md`, `TRACKING.md`, `UMSETZUNGSPLAN.md`, `design/DISENO_REST_UNIFICADO.md`, `design/DISENO_CLASES_REST.md`
- **Verificación:** IDs del TRACKING generados automáticamente desde el ROADMAP.
- **Decisiones:** roles `VIEWER`, `ANALYST`, `AUDITOR` y `ADMIN`; autenticación JWT + API key; proyecto multi-módulo; MerLabRAG opcional (perfil `rag`).
- **Commit:** — (pendiente, paso 0.5.3)
- **Siguiente:** 0.5.2

### [0.5.2] Instrucciones para agentes · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** `AGENTS.md` (Codex) y `CLAUDE.md` (Claude) en la raíz, con el protocolo ROGER y las reglas.
- **Clases / archivos:** `AGENTS.md`, `CLAUDE.md`
- **Verificación:** —
- **Decisiones:** —
- **Commit:** — (pendiente, paso 0.5.3)
- **Siguiente:** 0.5.3

### [0.5.3] Primer commit de la documentación · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** commit local en `v2` de `docs/v2/`, `AGENTS.md` y `CLAUDE.md`, aprobado por el usuario. Sin push.
- **Clases / archivos:** `docs/v2/**`, `AGENTS.md`, `CLAUDE.md`
- **Verificación:** `git log -1` en `v2`
- **Decisiones:** —
- **Commit:** el commit `[0.5.3]` de la rama `v2`
- **Siguiente:** 0.1.1 Crear el tag `v1-legacy` (👤) → esperando ROGER

### [Decisión DA-001] Estilo del núcleo: Opción C · 15.10.2026
- **Agente:** Claude
- **Pregunta:** ¿EJB en todo (A), núcleo Java puro + cableado CDI (B) o mixta (C)?
- **Opciones:** A (simple y conocida, sin tests rápidos del motor), B (independiente del framework, con el doble de clases), C (EJB en servicios, Java puro en el motor).
- **Decisión:** **C** (ROGER C del usuario). Detalle en `design/DECISIONES.md`. Notas para un posible host Spring futuro en `design/FUTURO_SPRING.md`; sus reglas R2–R8 están pendientes de ROGER.
- **Clases / archivos:** `docs/v2/design/DECISIONES.md`, `docs/v2/design/FUTURO_SPRING.md`
- **Commit:** `[DA-001]` (aprobado con ROGER)
- **Siguiente:** Revisión 2 del diseño y del roadmap (nombres v1/Obsidian, módulos, RAG separado, ModelForge remoto, Merlynix al final) → esperando ROGER

### [0.5.4] Revisión 2 del diseño y del roadmap · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** revisión aprobada con ROGER tras leer las notas de Obsidian. Nuevo `design/DISENO_REVISION_2.md` (alcance S1–S11, módulos de Obsidian, nombres v1, motor RPN de MerLabCore reutilizado, registry, archivos WAV/imagen/CSV/OHLCV, Orchestrator/Worker con EJB, motores opcionales Pegasus y ModelForge remoto, RAG separado con exportación al stack, audio y trading). ROADMAP reescrito: 16 grupos (0, A–G, U, I, H, J, K, L, M, N), 68 milestones y 265 micropasos; TRACKING regenerado. README y AGENTS actualizados (perfiles `pegasus` y `modelforge`, precedencia del diseño, DA-001).
- **Clases / archivos:** `docs/v2/design/DISENO_REVISION_2.md`, `docs/v2/ROADMAP.md`, `docs/v2/TRACKING.md`, `docs/v2/README.md`, `AGENTS.md`
- **Verificación:** tablas validadas; diagramas Mermaid y PlantUML validados.
- **Decisiones:** sin Spring en v2; ModelForge solo remoto; Merlynix en el grupo N (después de 2.0); operaciones que falten en MerLabCore las añade el usuario fuera de este roadmap.
- **Commit:** `[0.5.4]`
- **Siguiente:** 0.1.1 Crear el tag `v1-legacy` (👤) → esperando ROGER

### [Decisión DA-002] Reglas de código R1–R8 · 15.10.2026
- **Agente:** Claude
- **Pregunta:** ¿se adoptan las reglas de FUTURO_SPRING §4 para que un futuro host Spring salga barato?
- **Decisión:** sí (ROGER del usuario). Obligatorias en todos los micropasos. R1, R3, R4 y R7 se comprueban con ArchUnit (paso 0.4.1); R5 con la base de ITs (0.4.3).
- **Clases / archivos:** `design/DECISIONES.md`, `design/FUTURO_SPRING.md`, `ROADMAP.md`, `README.md`, `AGENTS.md`
- **Commit:** `[DA-002]`
- **Siguiente:** 0.1.1 Crear el tag `v1-legacy` (👤) → esperando ROGER


### [0.1.1] Tag v1-legacy · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** tag anotado `v1-legacy` sobre el último commit de `master` (`53ef018`, "Merge origin/main"), que marca el StackFlowServer v1 (3 capas, Jakarta EE) antes de la reescritura. Solo local: el push del tag espera aprobación del usuario.
- **Clases / archivos:** git (`refs/tags/v1-legacy`)
- **Verificación:** `git log --oneline -1 v1-legacy` → `53ef018 Merge origin/main`
- **Decisiones:** —
- **Commit:** `[0.1.1]` (solo documentación; el tag no cambia archivos)
- **Siguiente:** 0.1.3 Retirar el código v1 de la rama v2 → esperando ROGER

### [0.1.1] Nota: tag subido · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** a petición del usuario, `git push origin v1-legacy`. Solo el tag; `master` y `v2` siguen sin push.
- **Decisiones:** Git Bash falla con "SSL certificate problem" usando OpenSSL; el push funcionó con `-c http.sslBackend=schannel` (almacén de certificados de Windows). No se cambió la configuración global de git.

### [0.1.3] Retirar el código v1 de la rama v2 · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** documentación v1 movida a `docs/v1/` (`API.md`, `DATABASE_SCHEMA.md`, `SETUP_GUIDE.md`, `Setup Guide.docx`, `sqlscript_stackflowdb.sql`, `postman/`). Borrados en `v2`: `src/` (todo el código v1), `class_list.txt`, `pom.xml` v1 y `nb-configuration.xml` v1 (en 0.3.1 se crea uno nuevo en `stackflow-host-wildfly`). El v1 sigue intacto en `master` y en el tag `v1-legacy`.
- **Clases / archivos:** `docs/v1/**`; borrados `src/**`, `class_list.txt`, `pom.xml`, `nb-configuration.xml`
- **Verificación:** no queda `src/` en la raíz; `git status` → 181 borrados y 6 movidos. (No hay build hasta 0.2.)
- **Decisiones:** la carpeta `Neuer Ordner` (vacía y fuera de git) se deja como está; `target/` se trata en 0.1.4.
- **Commit:** `[0.1.3]`
- **Siguiente:** 0.1.4 `.gitignore` y sacar `target/` del índice → esperando ROGER

### [Decisión DA-003] Sin pendientes: cada ROGER cierra el paso con push · 15.10.2026
- **Agente:** Claude
- **Pregunta:** el usuario se pierde si un paso termina con "pendiente de aprobación".
- **Decisión:** cada micropaso termina cerrado del todo: commit **y push de `v2`** (`git -c http.sslBackend=schannel push origin v2`, solo fast-forward). Lo que no se pueda terminar queda `Bloqueado`. Actualizados README, AGENTS y DECISIONES.
- **Commit:** `[0.1.4]` (junto con el paso)

### [0.1.4] .gitignore y target/ fuera de git · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** `.gitignore` nuevo (`target/`, NetBeans privado, `*.class`, logs, IDEs, `*.local.properties`, `.env`). `git rm -r --cached target`: estaban versionados 4 archivos de `target/`. La carpeta sigue en disco, ahora ignorada.
- **Clases / archivos:** `.gitignore`
- **Verificación:** `git ls-files target` → 0
- **Decisiones:** también se sube `master` (fast-forward hasta `53ef018`, el merge ya hecho) para que el tag `v1-legacy` apunte a un commit de una rama remota.
- **Commit:** `[0.1.4]`, con push de `v2` y `master`
- **Siguiente:** 0.1.5 README v2 provisional → esperando ROGER

### [0.1.5] README v2 provisional · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** README reescrito para v2 (en inglés, como el código): qué es StackFlowServer (runtime de MerLabCore; audio y trading), motores opcionales (PegasusRAG, ModelForge, Merlynix), estado (v2 en reconstrucción, v1 en `master` + `v1-legacy`), cómo compilar y enlaces a `docs/v2/` y `docs/v1/`. Licencia Apache 2.0 (`LICENSE.txt`).
- **Clases / archivos:** `README.md`
- **Verificación:** enlaces relativos comprobados (existen `docs/v2/README.md`, `docs/v1/`, `LICENSE.txt`).
- **Decisiones:** —
- **Commit:** `[0.1.5]`, con push de `v2`
- **Siguiente:** 0.2.1 POM padre `stackflow-parent` → esperando ROGER (milestone 0.1 terminado)

### [0.2.1] POM padre stackflow-parent · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** `pom.xml` padre `com.merlab:stackflow-parent:2.0.0-SNAPSHOT` (`pom`): Java 17 (`release`), UTF-8; `dependencyManagement` (jakartaee-api 10.0.0 provided, junit-bom 5.11.4, mockito 5.14.2, archunit 1.3.0); `pluginManagement` (compiler 3.13.0, surefire 3.5.3 con `*Test`, failsafe 3.5.3 con `*IT`, war 3.4.0, enforcer 3.5.0); enforcer activo: Java ≥ 17 y Maven ≥ 3.9. Comentario con la regla 0.2.6 (módulos bajo demanda).
- **Clases / archivos:** `pom.xml`
- **Verificación:** `mvn -q validate` → OK (con el almacén de certificados de Windows, ver Decisiones).
- **Decisiones:** Maven, igual que Git, falla en este PC con "PKIX path building failed" (algo intercepta TLS). Solución sin tocar el sistema ni el repo: `MAVEN_OPTS=-Djavax.net.ssl.trustStoreType=Windows-ROOT` en cada comando (documentado en AGENTS.md). Para NetBeans se añade esa opción en el paso 0.3.1. Maven corre con JDK 23 y compila con `--release 17`.
- **Commit:** `[0.2.1]`, con push de `v2`
- **Siguiente:** 0.2.2 Módulos `stackflow-domain`, `stackflow-ports` y `stackflow-api-contract` → esperando ROGER

### [Entorno] Certificados de Git y Maven resueltos de forma permanente · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** con permiso del usuario: variable de usuario `MAVEN_OPTS=-Djavax.net.ssl.trustStoreType=Windows-ROOT` y `git config --global http.sslBackend schannel`. Las dos herramientas usan ahora el almacén de certificados de Windows. Maven de referencia: `C:\apache-maven-3.9.16`.
- **Verificación:** Maven 3.9.16 descarga `mockito-core` y `mvn -q validate` pasa sin opciones extra; `git fetch` funciona sin `-c`.
- **Decisiones:** nada se guarda en el repo (`.mvn/jvm.config` rompería macOS/Linux). En NetBeans solo hay que elegir Maven Home 3.9.16 (paso 0.3.1).
- **Commit:** `[env]`, con push de `v2`
- **Siguiente:** 0.2.2 Módulos `stackflow-domain`, `stackflow-ports` y `stackflow-api-contract` → esperando ROGER


### [0.2.2] Módulos domain, ports y api-contract · 15.10.2026
- **Agente:** Claude
- **Qué se hizo:** tres módulos jar con su `package-info.java` (cabecera Apache 2.0): `stackflow-domain` (`com.merlab.stackflow.domain`, Java puro), `stackflow-ports` (`com.merlab.stackflow.ports`, Java puro, depende de domain) y `stackflow-api-contract` (`com.merlab.stackflow.contract`, solo `jakarta.validation`). El padre declara los módulos y gestiona `jakarta.validation-api` 3.0.2 (`provided`, la de Jakarta EE 10).
- **Clases / archivos:** `pom.xml`, `stackflow-domain/**`, `stackflow-ports/**`, `stackflow-api-contract/**`
- **Verificación:** `mvn clean verify` (Maven 3.9.16) → BUILD SUCCESS, 3 jars.
- **Decisiones:** paquete del contrato `com.merlab.stackflow.contract` (subpaquetes `contract.common`, `contract.job`… según el grupo A). Los perfiles `pegasus`/`modelforge` aún no existen (0.2.5), así que la verificación se hace sin `-P!…`.
- **Commit:** `[0.2.2]`, con push de `v2`
- **Siguiente:** 0.2.3 Módulo `stackflow-adapter-jakarta` → esperando ROGER

### [0.2.3] Módulo stackflow-adapter-jakarta · 16.10.2026
- **Agente:** Claude
- **Qué se hizo:** módulo jar `stackflow-adapter-jakarta` (`com.merlab.stackflow.adapter.jakarta`): depende de `api-contract`, `domain` y `jakarta.jakartaee-api` (provided). `META-INF/beans.xml` (CDI 4.0, `bean-discovery-mode="annotated"`) para que WildFly lo trate como bean archive dentro del WAR.
- **Clases / archivos:** `stackflow-adapter-jakarta/pom.xml`, `META-INF/beans.xml`, `package-info.java`; módulo añadido al padre
- **Verificación:** `mvn clean verify` → BUILD SUCCESS (4 módulos); el jar contiene `META-INF/beans.xml`.
- **Decisiones:** las dependencias de los módulos de aplicación (jobs, pipeline, security…) se añaden cuando existan.
- **Commit:** `[0.2.3]`, con push de `v2`
- **Siguiente:** 0.2.4 Módulo `stackflow-host-wildfly` (WAR) → esperando ROGER

### [0.2.4] Módulo stackflow-host-wildfly (WAR) · 16.10.2026
- **Agente:** Claude
- **Qué se hizo:** módulo `war` `stackflow-host-wildfly` (`com.merlab.stackflow.host`), `finalName` `stackflow`, depende de `adapter-jakarta`. `WEB-INF/jboss-web.xml` (context root `/stackflow`), `WEB-INF/beans.xml` (CDI 4.0, `annotated`), `index.html` mínimo, sin `web.xml` (`failOnMissingWebXml=false`).
- **Clases / archivos:** `stackflow-host-wildfly/pom.xml`, `src/main/webapp/WEB-INF/jboss-web.xml`, `WEB-INF/beans.xml`, `index.html`, `package-info.java`; módulo añadido al padre
- **Verificación:** `mvn clean verify` → BUILD SUCCESS (5 módulos + padre). `stackflow.war` (8 KB) contiene `jboss-web.xml`, `beans.xml`, `index.html` y en `WEB-INF/lib` los jars de adapter-jakarta, api-contract y domain. Las APIs de Jakarta (provided) no van dentro, como debe ser.
- **Decisiones:** `stackflow-ports` todavía no entra en el WAR (nadie lo usa aún); llegará con el primer adaptador (E1).
- **Commit:** `[0.2.4]`, con push de `v2`
- **Siguiente:** 0.2.5 Perfiles Maven `pegasus` y `modelforge` → esperando ROGER

### [0.2.5] Perfiles Maven pegasus y modelforge · 16.10.2026
- **Agente:** Claude
- **Qué se hizo:** perfiles en el POM padre, vacíos de momento (los módulos se añaden en J2.2 y H3.3). `pegasus` se activa solo si existe `~/.m2/repository/com/merlab/rag/merlab-rag/0.1.0-SNAPSHOT/merlab-rag-0.1.0-SNAPSHOT.jar`; `modelforge` nunca se activa por defecto (ModelForge es remoto).
- **Clases / archivos:** `pom.xml`
- **Verificación:** `help:active-profiles` → por defecto ninguno (merlab-rag no está instalado, así que el modo "solo MerLabCore" es el actual); `-Ppegasus` y `-Pmodelforge` los activan. `mvn -P!pegasus,!modelforge clean verify` y `mvn -Ppegasus,modelforge clean verify` → BUILD SUCCESS.
- **Decisiones:** la activación por fichero no admite propiedades del proyecto, así que la versión de merlab-rag va escrita en la ruta; si cambia, se actualiza ahí (está comentado en el POM).
- **Commit:** `[0.2.5]`, con push de `v2`
- **Siguiente:** 0.2.6 Regla de módulos bajo demanda en el POM padre → esperando ROGER

### [0.2.6] Regla de módulos bajo demanda · 16.10.2026
- **Agente:** Claude
- **Qué se hizo:** comentario en la cabecera del POM padre con la regla (un módulo entra en `<modules>` en el paso que lo necesita; nunca módulos vacíos) y una tabla con los 19 módulos de v2: paso de creación y estilo (DA-001). Los pasos se contrastaron con el ROADMAP (14 de 14 coinciden).
- **Clases / archivos:** `pom.xml`
- **Verificación:** `mvn -P!pegasus,!modelforge clean verify` → BUILD SUCCESS. Primer intento fallido: una línea `----` dentro del comentario XML no es válida (`--` está prohibido en comentarios XML); se cambió por `====`.
- **Decisiones:** —
- **Commit:** `[0.2.6]`, con push de `v2`
- **Siguiente:** 0.3.1 👤 Abrir el proyecto en NetBeans → esperando ROGER (milestone 0.2 terminado)

### [0.3.1] Proyecto abierto en NetBeans · 16.10.2026
- **Agente:** Claude + usuario (👤)
- **Qué se hizo:** el usuario abrió `stackflow` (rama `v2`) en NetBeans con Maven 3.9.16: aparecen el padre "StackFlowServer [v2]" y los 5 módulos (Domain, Ports, API Contract, Adapter Jakarta, Host WildFly). *Clean and Build* desde NetBeans → BUILD SUCCESS (6,3 s). Claude añadió `stackflow-host-wildfly/nb-configuration.xml` con el hint de despliegue a WildFly (mismo valor que v1).
- **Clases / archivos:** `stackflow-host-wildfly/nb-configuration.xml`
- **Verificación:** captura del árbol de módulos y Reactor Summary de NetBeans (todo SUCCESS); NetBeans no creó otros archivos en el repo.
- **Decisiones:** el proyecto `test-1.0-SNAPSHOT` que también aparece en NetBeans es independiente (`StackFlowServer/note.txt`, fuera del repo) y no afecta.
- **Commit:** `[0.3.1]`, con push de `v2`
- **Siguiente:** 0.3.2 👤 Registrar WildFly 36 en NetBeans y elegir el perfil del servidor → esperando ROGER

### [0.3.2] Configuración de WildFly: microprofile en 8090 · 16.10.2026
- **Agente:** Claude
- **Qué se hizo:** el usuario aprobó la recomendación (DA-004). Script versionado `scripts/wildfly/http-port-8090.cli` (offline, idempotente) aplicado a `standalone-microprofile.xml` (copia previa en `standalone-microprofile.xml.before-stackflow-v2`). Prueba de arranque y apagado del servidor.
- **Clases / archivos:** `scripts/wildfly/http-port-8090.cli`, `design/DECISIONES.md` (DA-004), `ROADMAP.md`, `TRACKING.md`
- **Verificación:** WildFly 36 arranca con `-c standalone-microprofile.xml`; Undertow escucha en 127.0.0.1:8090; `/metrics` (9990) → 200. Servidor apagado al terminar.
- **Decisiones:** el registro del servidor en NetBeans pasa al paso 0.3.4 (junto con el primer despliegue desde NetBeans), para que 0.3.2 quede cerrado. El `stackflow.war` v1 que sigue en `deployments/` falla al arrancar (falta `StackflowDS`): es esperado y se resuelve en 0.3.3 y 0.3.4.
- **Incidencia:** el primer commit `4bf8974` salió incompleto (un script de edición falló a mitad); este segundo commit lo completa. No se reescribió historia.
- **Commit:** `4bf8974` + `[0.3.2] (completion)`, con push de `v2`
- **Siguiente:** 0.3.3 👤 DataSources `StackflowDS` y `StackflowTestDS` → esperando ROGER
