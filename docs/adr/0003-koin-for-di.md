# ADR-0003: Use Koin for dependency injection

- **Status:** Proposed
- **Date:** 2026-11-07

## Context
Shared ViewModels, repositories, and platform capabilities (OCR, sign-in) must be wired the same way on Android and iOS. Platform implementations are bound at startup on each side.

## Options considered
1. **Koin.** Pure Kotlin and multiplatform. The most widely used DI library in KMP, with first-class Compose and ViewModel support. Wiring is checked at runtime, not compile time.
2. **kotlin-inject / Metro.** Compile-time safety through KSP or a compiler plugin. Smaller ecosystem, and more setup across KMP targets.
3. **Manual DI.** No library, but a lot of boilerplate as the app grows.

## Decision
Koin. Use one module per Gradle module (`di/<Name>Module.kt`), and start it from `androidApp` and from `iosApp` (through a function exported by `sharedLogic`).

## Consequences
- Simple and well documented. Agents know Koin well, which lowers the error rate.
- Errors show up at runtime. Mitigate with a `verify()` test of the Koin graph in `commonTest`.
