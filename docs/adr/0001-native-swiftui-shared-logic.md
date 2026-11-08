# ADR-0001: Share logic in KMP; use native SwiftUI on iOS

- **Status:** Accepted
- **Date:** 2026-11-07
- **Deciders:** Bryan Armijos

## Context
Bingle targets Android and iOS. Kotlin Multiplatform lets us share code. The open question was how much to share: logic only, or logic plus UI through Compose Multiplatform. The play screen has to feel instant and native in a noisy hall. Card import relies heavily on platform features (camera, photo picker, Vision, PDFKit).

## Options considered
1. **Compose Multiplatform on both platforms.** One UI codebase. iOS look and feel, and integration with platform pickers and camera, are weaker. Fewer Swift skills needed.
2. **Shared logic, native UI on each platform.** Domain, data, and ViewModels are in Kotlin. Android uses Compose; iOS uses SwiftUI. This is the pattern Cash App, Netflix, and Booking.com report using in production.

## Decision
Option 2. Share everything below the UI, including ViewModels and `UiState`. Write the UI natively on each platform.

## Consequences
- Each platform gets native UX and direct access to platform APIs.
- Every screen is written twice. This stays manageable because screens are thin renderers of a shared `UiState`.
- Kotlin→Swift interop quality matters (Flows, sealed classes, suspend functions). See [ADR-0005](0005-skie-for-swift-interop.md).
- This matches JetBrains' 2026 default for native-UI projects: `sharedLogic` is used by all platforms, `sharedUI` by the Compose platforms only.
