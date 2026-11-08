# Bingle — Agent Map

Bingle is a Kotlin Multiplatform (Android + iOS) companion app for playing **real-life US 75-ball bingo**. Users import their paper or PDF cards (photo → OCR → a virtual 5×5 grid), group them into a dated **Game**, and type each called number. The app then marks it on every card and detects a bingo.

This file is a **map**, not the manual. Read the linked doc only when your task needs it. `docs/` is the system of record. If something here disagrees with `docs/`, `docs/` wins, and you should flag the mismatch.

## Before any non-trivial task
1. Read [docs/workflow/agentic-workflow.md](docs/workflow/agentic-workflow.md). It covers roles, the spec → plan → implement → verify → PR lifecycle, and the **stop-and-ask rules**.
2. Find the spec in [docs/specs/](docs/specs/README.md). If the task has no spec and is not a small fix, propose writing one first.
3. Check [docs/PRINCIPLES.md](docs/PRINCIPLES.md). These are the non-negotiables.

## Where things are
| Need | Go to |
|---|---|
| Module map, layers, data flow (Current vs Target) | [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) |
| Dependency and naming rules | [docs/standards/architecture-rules.md](docs/standards/architecture-rules.md) |
| Kotlin / Compose / Swift / testing rules | [docs/standards/](docs/standards/) |
| Security, secrets, card-image privacy | [docs/standards/security-privacy.md](docs/standards/security-privacy.md) |
| Why we chose X (decisions) | [docs/adr/](docs/adr/README.md) |
| Bingo domain rules (ranges, FREE, patterns) | [docs/references/bingo-rules-us-75.md](docs/references/bingo-rules-us-75.md) |
| Real card samples and ground truth | [docs/references/bingo-cards/](docs/references/bingo-cards/README.md) |
| Domain vocabulary | [docs/GLOSSARY.md](docs/GLOSSARY.md) |
| In-flight multi-session work | [docs/exec-plans/active/](docs/exec-plans/README.md) |
| Known shortcuts and deferred work | [docs/exec-plans/tech-debt-tracker.md](docs/exec-plans/tech-debt-tracker.md) |
| Definition of Done | [docs/workflow/definition-of-done.md](docs/workflow/definition-of-done.md) |
| Branches, commits, PRs | [docs/workflow/git-and-pr.md](docs/workflow/git-and-pr.md) |

## Repo today (Current)
These are the JetBrains 2026 default-template modules. The `core/*` + `feature/*` split ([ADR-0002](docs/adr/0002-modularization-core-feature.md)) is **not built yet**. Check `settings.gradle.kts` before assuming a module exists.

- `sharedLogic/` is the KMP library (Android, iosArm64, iosSimulatorArm64). It has no Compose and builds the `SharedLogic` iOS framework. **All shared logic goes here.**
- `sharedUI/` holds the Compose UI for Android. It is Android-only and depends on `sharedLogic`.
- `androidApp/` is a thin Android shell.
- `iosApp/` is the Xcode project with the SwiftUI app. See [iosApp/CLAUDE.md](iosApp/CLAUDE.md).

## Commands
```bash
./gradlew :androidApp:assembleDebug                                       # build Android app
./gradlew :sharedLogic:testAndroidHostTest :sharedUI:testAndroidHostTest  # JVM/Android host tests
./gradlew :sharedLogic:iosSimulatorArm64Test                              # iOS simulator tests
./gradlew build                                                           # everything
```
- iOS app: `xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -destination 'platform=iOS Simulator,name=<device>' build`, or open it in Xcode.
- Run `/verify` before saying any work is done.

## Hard rules (the full list is in the workflow doc)
- **Stop and ask** before you:
  - add or upgrade dependencies;
  - edit `*.pbxproj`, signing, CI, or `.claude/settings.json`;
  - write DB migrations;
  - touch auth, tokens, or card-image storage;
  - deviate from an approved spec or plan.
- Business logic lives in Kotlin `commonMain`, never in Swift or in Composables.
- `feature → core` only, never `feature → feature`.
- Domain logic is test-first in `commonTest`. Never weaken or delete a test, or edit a fixture, to get a build to pass.
- Never read or print secrets, keystores, or `local.properties` values.
- Keep docs true. If you change behavior or make a decision, update the spec, ADR, or ARCHITECTURE.md in the same PR.

## Agent tooling (`.claude/`)
- Skills:
  - `/verify` runs the Definition-of-Done check suite.
  - `/start-feature` takes a spec through plan, branch, and exec plan.
  - `/write-adr` drafts an ADR.
- Subagents:
  - `spec-writer` drafts a spec from an idea.
  - `code-reviewer` reviews a diff against `docs/standards/`.
