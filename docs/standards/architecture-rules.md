# Architecture Rules

These rules are meant to be checked mechanically. Once `konsist-tests` exists, each rule below gets a test, and a PR that breaks one fails CI. Until then, the `code-reviewer` agent checks them by hand.

## Module dependencies
| From | May depend on | Must NOT depend on |
|---|---|---|
| `core/model` | nothing (only the Kotlin stdlib and kotlinx-datetime) | any other module |
| `core/*` | `core/model`, `core/common` | `feature/*`, `sharedLogic`, `sharedUI`, apps |
| `feature/*` | `core/*` | **other `feature/*`**, `sharedUI`, apps |
| `sharedLogic` (umbrella) | `core/*`, `feature/*` | `sharedUI`, apps |
| `sharedUI` | `sharedLogic` (api), `core/model` | apps |
| `androidApp` | `sharedUI` | — |
| `iosApp` | `Shared.framework` (from `sharedLogic`) only | — |

If two features need the same thing, move it into a `core/*` module. Features never talk to each other directly; navigation between them is wired in the app layer.

## Layers inside a feature
- `domain/` imports only from `core/model`, `core/common`, and the Kotlin standard libraries.
- `data/` may import from `domain/`, `core/database`, and `core/network`.
- `presentation/` may import from `domain/`, and from `data/` only through interfaces resolved by DI.
- `platform/` contains interfaces only. Implementations live in `androidMain` or `iosMain`.
- Nothing in `commonMain` imports `android.*`, `platform.UIKit.*`, or Compose.

## Naming (Konsist-checkable)
| Kind | Suffix | Location |
|---|---|---|
| ViewModel | `…ViewModel` | `presentation/` |
| UI state | `…UiState` (sealed interface or data class) | `presentation/` |
| UI event | `…Event` (sealed interface) | `presentation/` |
| Use case | `…UseCase`, with a single `operator fun invoke` | `domain/` |
| Repository interface | `…Repository` | `domain/` |
| Repository impl | `Default…Repository` | `data/` |
| Koin module | `…Module` | `di/` |
| Fake for tests | `Fake…` | `core/testing` or `commonTest` |

## Platform UI
- Android screens live in `sharedUI`, organized by feature: `ui/<feature>/<Screen>Screen.kt`.
- iOS screens live in `iosApp/iosApp/Features/<Feature>/<Screen>View.swift`.
- Both platforms render the **same** `UiState` from the **same** shared ViewModel. Screens contain no business logic.
