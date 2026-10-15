# StackFlowServer v2: documentación de trabajo

Implementación nueva de StackFlowServer, la runtime de MerLabCore (stack RPN + ML sobre señales y datos), con PegasusRAG y ModelForge como motores opcionales, en la rama `v2`. El código v1 (3 capas) sigue en `master` y en el tag `v1-legacy`, y **no se mezcla** con v2.

| Documento | Para qué |
|---|---|
| [ROADMAP.md](ROADMAP.md) | qué hay que hacer: 16 grupos, 68 milestones y 265 micropasos con clases, verificación y DoD |
| [TRACKING.md](TRACKING.md) | estado de cada micropaso: Pendiente, En proceso, Terminado o Bloqueado |
| [UMSETZUNGSPLAN.md](UMSETZUNGSPLAN.md) | registro de lo hecho: resumen corto, clases implicadas, verificación y commit |
| [design/DECISIONES.md](design/DECISIONES.md) | **1.º en precedencia:** decisiones aprobadas con ROGER (DA-001: EJB para servicios, Java puro para el motor) |
| [design/DISENO_REVISION_2.md](design/DISENO_REVISION_2.md) | **2.º:** alcance, módulos, nombres, motor RPN, archivos, jobs, motores opcionales, audio y trading |
| [design/DISENO_REST_UNIFICADO.md](design/DISENO_REST_UNIFICADO.md) | 3.º: endpoints, errores, Anexo A (módulos v1); traducir nombres con Revisión 2 §11 |
| [design/DISENO_CLASES_REST.md](design/DISENO_CLASES_REST.md) | 3.º: diagramas de clases, estados y secuencias (Mermaid y PlantUML) |
| [design/FUTURO_SPRING.md](design/FUTURO_SPRING.md) | notas para un posible host Spring (no planificado) |

## Protocolo ROGER

El trabajo avanza **un micropaso cada vez**. El usuario controla el ritmo con mensajes cortos.

| El usuario escribe | El agente hace |
|---|---|
| `ROGER` | implementa el **siguiente** micropaso del TRACKING y se detiene |
| `ROGER <ID>` | implementa ese micropaso concreto (p. ej. `ROGER A2.3`) y se detiene |
| `ROGER` + comentario | igual que `ROGER`, teniendo en cuenta el comentario |
| `ALTO` | no sigue; deja el paso actual en `En proceso` o `Bloqueado` con una nota |

### Ciclo de cada micropaso

1. **Leer** el TRACKING (sección "Paso actual") y el milestone del paso en el ROADMAP, más las secciones de diseño que cita.
2. **Marcar** el paso como `En proceso` en el TRACKING.
3. **Implementar solo ese paso** (clases y tests indicados).
4. **Verificar**: `mvn -q -P!pegasus,!modelforge clean verify` en verde (desde J2 también con `-Ppegasus`; desde H3, con `-Pmodelforge`). Si el paso lo indica, el IT correspondiente.
5. **Actualizar**:
   - TRACKING: estado `Terminado`, fecha, commit; en "Resumen por milestone", el contador y el estado; en "Paso actual", el siguiente ID;
   - UMSETZUNGSPLAN: una entrada nueva con la plantilla.
6. **Commit** con el mensaje `[<ID>] <resumen>` y **push de la rama `v2`** (`git push origin v2`; solo fast-forward, nunca `--force`).
7. **Detenerse** y responder con este formato:

   ```
   ✅ <ID> terminado: <una frase>
   Clases: <lista corta>
   Verificación: <comando> → OK
   Siguiente: <ID> <título corto>
   Escribe ROGER para continuar.
   ```

   **Sin pendientes:** cada micropaso termina cerrado del todo (incluido el push). Si algo no se puede terminar, el paso queda `Bloqueado` con el motivo, dicho de forma explícita. Nunca "queda pendiente de aprobación" entre un ROGER y el siguiente.

### Pasos especiales

- **👤 (usuario):** el agente prepara lo necesario (comandos, scripts, instrucciones), explica qué tiene que hacer el usuario y espera. No marca el paso como `Terminado` hasta que el usuario confirma.
- **Spike:** no hay código de producción. El resultado es una entrada "Decisión" en el UMSETZUNGSPLAN; el paso se marca `Terminado`.
- **Bloqueado:** si un paso no se puede completar (falta un servicio, el diseño no cubre un caso, falla un test ajeno), el agente lo marca `Bloqueado`, explica el motivo en Notas y pregunta.

## Reglas que nunca se rompen

1. No modificar **MerLabCore** ni **PegasusRAG/MerLabRAG** (ni sus miniservers). Se usan como JAR. No reimplementar algoritmos que ya existen en MerLabCore.
2. No tocar `master`, el tag `v1-legacy` ni el código v1.
3. No copiar código del proyecto de la empresa (`genai-java-rest-api`); solo se usa la forma de sus endpoints.
4. Sin secretos en el código ni en git.
5. Push de `v2` en cada micropaso (DA-003). Nunca `--force`, nunca reescribir historia publicada, nunca tocar `master` ni tags existentes.
6. Si el diseño y el roadmap no coinciden, manda el diseño (en su orden de precedencia). Si falta algo, se anota y se pregunta.
7. Estilo DA-001: EJB (`XServiceLocal`/`XServiceBean`, `XDaoLocal`/`XDaoBean`) para lo que toca BD, seguridad, transacciones, hilos, timers o mensajes; Java puro para lo que solo calcula. Además, las reglas R1–R8 de DA-002 en cada micropaso.
8. Las decisiones de diseño solo se toman cuando el usuario escribe **ROGER**; una explicación o una pregunta no es una decisión.
