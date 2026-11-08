# Testing Standards

## Where tests go
| What | Source set | Framework |
|---|---|---|
| Domain logic (parser, validator, marking, win detection) | `commonTest` | `kotlin.test` (Kotest assertions optional, once added) |
| ViewModels | `commonTest` | `kotlin.test` + `kotlinx-coroutines-test` + Turbine (once added) |
| Repositories with Room | `commonTest`, using an in-memory DB per platform | Room KMP testing |
| Platform implementations (OCR, PDF) | `androidHostTest` / `iosTest` | Platform-specific; keep them thin |
| Architecture rules | `konsist-tests` | Konsist |

Tests in `commonTest` run on **both** Android host and iOS simulator. A test that passes on only one platform is a bug.

## Rules
- **Domain code is written test-first.** Write a failing test from the spec's acceptance criteria, then implement.
- Name tests by behavior, using backticks: `` `marks called number on every card in the game`() ``.
- Use the Arrange / Act / Assert structure, with one behavior per test.
- Use fakes over mocks. Fakes live in `core/testing` (shared) or the module's `commonTest`. No mocking library in `commonTest`.
- Tests must be deterministic. Inject time, IDs, and dispatchers; never use `delay` for synchronization; use `runTest`.
- Never weaken an assertion or delete a test to make a build pass. If a test is wrong, say so in the PR and explain why.

## Card fixtures
- The ground-truth samples live in [`docs/references/bingo-cards/`](../references/bingo-cards/README.md).
- Parser tests run on the **OCR text output** (a recorded `.txt` per sample) rather than on images, so they stay pure Kotlin and run on both platforms. Platform OCR is tested separately and thinly.
- Each new sample adds one regression case. Fixtures are never edited to make a test pass.

## Commands (current)
```bash
./gradlew :sharedLogic:testAndroidHostTest :sharedUI:testAndroidHostTest   # Android host (JVM)
./gradlew :sharedLogic:iosSimulatorArm64Test                               # iOS simulator
```
