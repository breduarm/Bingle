# Architecture Decision Records

An ADR records **one** significant decision: its context, the options, the choice, and the consequences. ADRs are immutable once accepted. To change a decision, write a new ADR that supersedes the old one.

- Create one with the `/write-adr` skill, or copy [0000-template.md](0000-template.md).
- Agents draft ADRs with `Status: Proposed`. Only the project owner sets `Accepted`.

| # | Decision | Status |
|---|---|---|
| [0001](0001-native-swiftui-shared-logic.md) | Share logic in KMP; native SwiftUI on iOS, Compose on Android | Accepted |
| [0002](0002-modularization-core-feature.md) | Modularize into `core/*` + `feature/*` with an iOS umbrella module | Accepted |
| [0003](0003-koin-for-di.md) | Koin for dependency injection | Proposed |
| [0004](0004-room-kmp-local-db.md) | Room KMP for local persistence | Proposed |
| [0005](0005-skie-for-swift-interop.md) | SKIE for Swift interop | Proposed |
| [0006](0006-on-device-ocr-mlkit-vision.md) | On-device OCR: ML Kit (Android) + Apple Vision (iOS) | Proposed |
| 0007 | Auth backend (Supabase vs Firebase) | To be written when spec 004 starts |
