# ADR-0005: Use SKIE for Swift interop

- **Status:** Proposed
- **Date:** 2026-11-07

## Context
SwiftUI observes the shared ViewModels ([ADR-0001](0001-native-swiftui-shared-logic.md)). Without help, the default Kotlin/Native export gives Swift:
- `Flow` objects that need callback adapters;
- sealed classes that cannot be switched over exhaustively;
- suspend functions without proper cancellation.

## Options considered
1. **SKIE (Touchlab).** A compiler plugin. It exposes `Flow` as `AsyncSequence`, suspend functions as `async` with cancellation, and sealed classes and enums as exhaustive Swift enums. It needs no extra Swift package.
2. **KMP-NativeCoroutines.** Covers Flows and suspend functions only, and needs a Swift package plus annotations.
3. **Swift export (JetBrains, experimental).** The future direction, but not production-ready for our needs yet.

## Decision
SKIE, configured in `sharedLogic` (the umbrella). Revisit when Kotlin's Swift export becomes stable.

## Consequences
- Idiomatic Swift: `for await state in vm.uiState`, plus exhaustive `switch onEnum(of:)`.
- SKIE must support our Kotlin version. Check SKIE compatibility before every Kotlin upgrade.
