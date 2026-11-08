# Swift / SwiftUI Standards

The iOS app is a **thin native UI** over the shared Kotlin ViewModels ([ADR-0001](../adr/0001-native-swiftui-shared-logic.md)). Swift code renders state and forwards events. It never re-implements business rules.

## Structure
```
iosApp/iosApp/
├── iOSApp.swift            # app entry; starts Koin from the Shared framework
├── DesignSystem/           # colors, typography, reusable components
├── Navigation/             # NavigationStack + routes
└── Features/<Feature>/     # <Screen>View.swift, <Screen>Model.swift (observable wrapper)
```

## Rules
- Target the minimum iOS version set in the Xcode project. Use the Observation framework (`@Observable`) for view models where it is available.
- Follow this pattern for each shared ViewModel (once SKIE lands, [ADR-0005](../adr/0005-skie-for-swift-interop.md)):
  - An `@Observable` Swift wrapper owns the Kotlin ViewModel.
  - It collects `uiState` (an `AsyncSequence` via SKIE) in a `.task {}`.
  - It forwards user actions via `viewModel.onEvent(...)`.
  - It calls `clear()` on the ViewModel when it goes away.
- Use `switch` over SKIE-generated enums (`onEnum(of:)`) for sealed states. Make the switch exhaustive, with no `default`.
- Views are small and preview-able: give each `View` a `#Preview` with fake state.
- Strings go in a String Catalog (`Localizable.xcstrings`), with no hard-coded user-facing text. Support Dynamic Type and Dark Mode.
- Style: Swift API Design Guidelines, 4-space indentation, `final class` by default.

## Xcode project
- Do not hand-edit `project.pbxproj` without asking (see [stop-and-ask rules](../workflow/agentic-workflow.md#stop-and-ask-rules)). Adding a new Swift file through Xcode is the safe path. If an agent must add files, it says so in the PR.
- The Gradle build phase `:sharedLogic:embedAndSignAppleFrameworkForXcode` must stay in place.
