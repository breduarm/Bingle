# ADR-0002: Modularize into core/* and feature/* with an iOS umbrella

- **Status:** Accepted (implementation pending)
- **Date:** 2026-11-07
- **Deciders:** Bryan Armijos

## Context
The JetBrains template starts with a single `sharedLogic` module. Bingle has four MVP features (auth, games, card import, play), and AI agents will build them in parallel. One big shared module invites tangled dependencies and merge conflicts, and it gives agents no clear boundary for "only touch this area". It also makes Gradle builds slower.

## Options considered
1. **Keep a single `sharedLogic` module** and use packages. Simple, but nothing enforces the boundaries.
2. **`core/*` + `feature/*` modules with convention plugins** (the Now in Android pattern, also used by large KMP apps). iOS consumes a single umbrella framework (`sharedLogic`) that `export`s the modules it needs.

## Decision
Option 2, as described in [ARCHITECTURE.md](../ARCHITECTURE.md) and enforced by [architecture rules](../standards/architecture-rules.md).

## Consequences
- Agents can be given one module per task. Konsist and Gradle enforce the boundaries. Builds run in parallel and are incremental.
- There is more Gradle setup up front. Convention plugins in `build-logic` keep each module's build file short.
- iOS must only ever link `Shared.framework` (the umbrella). Linking several Kotlin frameworks would duplicate the runtime.
- Follow-up: build the skeleton (`build-logic`, `core/*`, umbrella export) before feature 001.
