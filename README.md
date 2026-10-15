# StackFlowServer

**StackFlowServer** is the runtime of the MerLab platform: a Jakarta EE 10 server (WildFly) that exposes **MerLabCore** as a REST API. It runs RPN stack operations, signal processing and machine-learning algorithms on signals and data, chains them in pipelines and executes them as jobs. First applications: **audio** and **trading**.

Optional engines, integrated through ports and adapters, never mixed with the core:

- **PegasusRAG**: RAG and knowledge engine (Maven profile `pegasus`).
- **ModelForge**: model training on a remote machine, reached over HTTP (Maven profile `modelforge`).
- **Merlynix**: the language of the RPN stack (planned after 2.0).

> **Status:** version 2 is being rebuilt from scratch on branch `v2`. Version 1 (3-tier) is preserved on `master` and in the tag `v1-legacy`.

## Build

Requirements: Java 17, Maven 3.9+, WildFly 36, MariaDB. MerLabCore installed in the local Maven repository (`mvn install` in the MerLabCore project).

```bash
mvn -P!pegasus,!modelforge clean verify
```

The multi-module Maven project is created in milestone 0.2 of the roadmap. Until then, this branch contains documentation only.

## Documentation

All working documentation lives in [`docs/v2/`](docs/v2/README.md):

| Document | Purpose |
|---|---|
| [docs/v2/README.md](docs/v2/README.md) | ROGER protocol and rules for implementation agents |
| [docs/v2/ROADMAP.md](docs/v2/ROADMAP.md) | groups, milestones and micro-steps |
| [docs/v2/TRACKING.md](docs/v2/TRACKING.md) | status of every micro-step |
| [docs/v2/UMSETZUNGSPLAN.md](docs/v2/UMSETZUNGSPLAN.md) | implementation log |
| [docs/v2/design/](docs/v2/design/) | decisions (DA-xxx) and design |

Version 1 documentation: [`docs/v1/`](docs/v1/).

## License

Apache License 2.0. See [LICENSE.txt](LICENSE.txt).

## Author

Merly Abondano
