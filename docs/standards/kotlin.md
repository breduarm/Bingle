# Kotlin Standards

These rules apply to all Kotlin in this repo: `commonMain`, the platform source sets, and Gradle scripts.

## Style
- Follow the [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html). Formatting will be enforced by Spotless and ktlint once they are added; until then, match the surrounding code.
- Use explicit API mode (`explicitApi()`) in `core/*` modules once they exist. Everything is `internal` unless another module needs it.
- Classes and objects use `PascalCase`, functions and properties use `camelCase`, constants use `SCREAMING_SNAKE`.
- The package for module `feature/foo` is `com.beam.bingle.feature.foo`; for `core/bar` it is `com.beam.bingle.core.bar`.
- Keep to one public top-level type per file, and name the file after it.

## Modeling
- Model domain types as immutable `data class`es with `val` properties. Prefer value classes for IDs (`@JvmInline value class GameId(val value: String)`).
- Use `sealed interface` for UI state, events, and errors. `when` over them must be exhaustive, with no `else` branch.
- Never use `!!`. Use `requireNotNull` or `checkNotNull` with a message, or model the absence explicitly.
- Domain code must not know about platforms or frameworks: no Android, Room, Ktor, or Koin types in `domain/`.

## Errors
- Expected failures (invalid card, network down, auth cancelled) are values: return `AppResult<T>` (or `Result<T>` until `core/common` exists) with a typed `AppError`.
- Exceptions are only for programmer errors. Catch platform exceptions at the `data/` or `platform/` boundary and map them to `AppError`.
- Never swallow an exception silently. At minimum, log it (without PII).

## Coroutines & Flow
- Inject a dispatcher provider. Never hard-code `Dispatchers.IO` or `Dispatchers.Default`. Tests need to swap them.
- Repositories expose `Flow<T>` for observable data and `suspend fun` for one-shot operations.
- ViewModels expose exactly one `StateFlow<UiState>`, built with `stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), initial)`. One-off effects go through a `Channel` exposed as a `Flow`.
- Never use `GlobalScope`. Never use `runBlocking` outside tests.

## ViewModels (shared, multiplatform)
- Extend `androidx.lifecycle.ViewModel`; this works in `commonMain`.
- Use one public entry point for input: `fun onEvent(event: <Screen>Event)`.
- No Android, Compose, or UIKit types in a ViewModel. It must be usable from SwiftUI as-is.

## Dependency injection
- Use Koin ([ADR-0003](../adr/0003-koin-for-di.md)). Each module exposes one `val <name>Module = module { … }` in its `di/` package.
- Use constructor injection only. Never call `get()` or `inject()` inside domain or data classes.

## expect / actual
- Use these only for trivial platform values (platform name, UUID, current time source). Model platform *capabilities* (OCR, camera, sign-in) as interfaces with platform implementations bound in Koin. This makes them testable with fakes.
