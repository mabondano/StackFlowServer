# StackFlowServer v2: documentación de trabajo

Implementación nueva del REST unificado (StackFlow + MerLabCore + MerLabRAG opcional) en la rama `v2`. El código v1 (3 capas) sigue en `master` y en el tag `v1-legacy`, y **no se mezcla** con v2.

| Documento | Para qué |
|---|---|
| [ROADMAP.md](ROADMAP.md) | qué hay que hacer: 14 grupos, milestones y micropasos con clases, verificación y DoD |
| [TRACKING.md](TRACKING.md) | estado de cada micropaso: Pendiente, En proceso, Terminado o Bloqueado |
| [UMSETZUNGSPLAN.md](UMSETZUNGSPLAN.md) | registro de lo hecho: resumen corto, clases implicadas, verificación y commit |
| [design/DISENO_REST_UNIFICADO.md](design/DISENO_REST_UNIFICADO.md) | decisiones, endpoints, módulos, Anexo A (módulos del diseño v1) |
| [design/DISENO_CLASES_REST.md](design/DISENO_CLASES_REST.md) | diagramas de clases, estados y secuencias (Mermaid y PlantUML) |

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
4. **Verificar**: `mvn -q -P!rag clean verify` en verde (desde J2 también con `-Prag`). Si el paso lo indica, el IT correspondiente.
5. **Actualizar**:
   - TRACKING: estado `Terminado`, fecha, commit; en "Resumen por milestone", el contador y el estado; en "Paso actual", el siguiente ID;
   - UMSETZUNGSPLAN: una entrada nueva con la plantilla.
6. **Commit local** con el mensaje `[<ID>] <resumen>`. **Sin push**, salvo que el usuario lo pida.
7. **Detenerse** y responder con este formato:

   ```
   ✅ <ID> terminado: <una frase>
   Clases: <lista corta>
   Verificación: <comando> → OK
   Siguiente: <ID> <título corto>
   Escribe ROGER para continuar.
   ```

### Pasos especiales

- **👤 (usuario):** el agente prepara lo necesario (comandos, scripts, instrucciones), explica qué tiene que hacer el usuario y espera. No marca el paso como `Terminado` hasta que el usuario confirma.
- **Spike:** no hay código de producción. El resultado es una entrada "Decisión" en el UMSETZUNGSPLAN; el paso se marca `Terminado`.
- **Bloqueado:** si un paso no se puede completar (falta un servicio, el diseño no cubre un caso, falla un test ajeno), el agente lo marca `Bloqueado`, explica el motivo en Notas y pregunta.

## Reglas que nunca se rompen

1. No modificar **MerLabCore** ni **MerLabRAG** (ni sus miniservers). Se usan como JAR.
2. No tocar `master`, el tag `v1-legacy` ni el código v1.
3. No copiar código del proyecto de la empresa (`genai-java-rest-api`); solo se usa la forma de sus endpoints.
4. Sin secretos en el código ni en git.
5. Sin push ni tags remotos sin aprobación del usuario.
6. Si el diseño y el roadmap no coinciden, manda el diseño. Si falta algo, se anota y se pregunta.
