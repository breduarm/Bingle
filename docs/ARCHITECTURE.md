# Architecture

> **Status:** Each section is marked **Current** (exists in the repo today) or **Target** (agreed in ADRs, not built yet). Agents must not assume a Target module exists. Check `settings.gradle.kts` first.

## Overview
Bingle is built with Kotlin Multiplatform. The shared logic is consumed by native UIs:

```
             ┌──────────────────────── Kotlin Multiplatform ────────────────────────┐
             │  feature/*  (domain · data · presentation/ViewModel)                │
             │  core/*     (model · database · network · auth · common)            │
             │        ▲                                    ▲                       │
             │   sharedUI (Compose)              sharedLogic (iOS umbrella)        │
             └────────▲────────────────────────────────────▲───────────────────────┘
                      │                                    │  Shared.framework (SKIE)
               androidApp (Compose)                  iosApp (SwiftUI)
```

The decision is recorded in [ADR-0001](adr/0001-native-swiftui-shared-logic.md) and [ADR-0002](adr/0002-modularization-core-feature.md).

## Modules

### Current (JetBrains 2026 default template)
| Module | Role |
|---|---|
| `sharedLogic` | KMP library with no Compose. Targets Android, iosArm64, and iosSimulatorArm64. Builds the static `SharedLogic` framework for Xcode. |
| `sharedUI` | Compose Multiplatform library, currently Android-only. Re-exports `sharedLogic` via `api`. |
| `androidApp` | Thin Android application shell (`MainActivity`). |
| `iosApp` | Xcode project with the SwiftUI app. A build phase runs `:sharedLogic:embedAndSignAppleFrameworkForXcode`. |

### Target
| Module | Role |
|---|---|
| `build-logic/convention` | Convention plugins: `bingle.kmp.library`, `bingle.kmp.feature`, `bingle.compose.library`, `bingle.android.application`. |
| `core/common` | Dispatchers, `AppResult`/`AppError`, logging. |
| `core/model` | Pure domain types: `BingoCard`, `Cell`, `Game`, `Call`, `WinPattern`. |
| `core/database` | Room KMP: entities, DAOs, migrations ([ADR-0004](adr/0004-room-kmp-local-db.md)). |
| `core/network` | Ktor client. Arrives together with auth/sync. |
| `core/auth` | `AuthRepository` interface plus platform provider bridges (Google and Facebook). |
| `core/testing` | Fakes, the fixture loader, and test dispatchers. |
| `feature/games` | Create and list games. |
| `feature/play` | Call entry, auto-marking, and win detection. |
| `feature/cards` | Card import: OCR, parse, review, save ([ADR-0006](adr/0006-on-device-ocr-mlkit-vision.md)). |
| `feature/auth` | Sign up, log in, log out. |
| `sharedLogic` | **iOS umbrella.** Depends on every `core/*` and `feature/*` module, `export`s them into `Shared.framework`, starts Koin, and configures SKIE ([ADR-0005](adr/0005-skie-for-swift-interop.md)). |
| `sharedUI` | Compose screens and the design system for Android. |
| `konsist-tests` | Architecture tests that enforce [architecture rules](standards/architecture-rules.md). |

## Inside a feature module (Target)
```
feature/<name>/src/
├── commonMain/kotlin/com/beam/bingle/feature/<name>/
│   ├── domain/        # use cases, pure logic, repository interfaces
│   ├── data/          # repository implementations, mappers
│   ├── presentation/  # <Screen>ViewModel, <Screen>UiState, <Screen>Event
│   ├── platform/      # interfaces for platform capabilities (camera, OCR, PDF…)
│   └── di/            # <Name>Module.kt (Koin)
├── androidMain/       # actual platform implementations
├── iosMain/
└── commonTest/        # domain + ViewModel tests
```

## Data flow (Target)
1. A UI event goes to the ViewModel (`onEvent(Event)`).
2. The ViewModel calls a use case, then the repository, then the DAO or API.
3. The repository exposes `Flow<T>`. The ViewModel maps it into a `StateFlow<UiState>`.
4. **Android:** `collectAsStateWithLifecycle()` in Compose.
5. **iOS:** SKIE exposes `StateFlow` as an `AsyncSequence`, which a SwiftUI `@Observable` wrapper collects in a `.task {}`.

## Platform capabilities (Target)
Each capability is declared as an interface in `commonMain` and implemented per platform, then bound in Koin. `expect`/`actual` is reserved for trivial values such as the platform name.

| Capability | Android | iOS |
|---|---|---|
| Text recognition | ML Kit Text Recognition v2 | Apple Vision `VNRecognizeTextRequest` |
| PDF rendering and text | `PdfRenderer` | PDFKit |
| Camera / picker | CameraX / Photo Picker | `PHPicker` / `UIImagePickerController` |
| Google sign-in | Credential Manager | GoogleSignIn SDK |
| Facebook sign-in | Facebook Login SDK | Facebook Login SDK |
