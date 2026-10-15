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
