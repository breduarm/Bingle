# iosApp — Agent Notes

The SwiftUI app. It is a thin native UI over the shared Kotlin ViewModels. The full rules are in [docs/standards/swift.md](../docs/standards/swift.md).

- The Kotlin code arrives as the `SharedLogic` framework (once [ADR-0002](../docs/adr/0002-modularization-core-feature.md) is built, it becomes the `Shared` umbrella). It is built by the Xcode run-script phase `./gradlew :sharedLogic:embedAndSignAppleFrameworkForXcode`. Never remove that phase.
- After changing Kotlin code, rebuild the iOS app so the framework is regenerated. Swift sees Kotlin API changes only after a rebuild.
- No business logic in Swift. If you find yourself writing a rule (validation, marking, parsing), move it into Kotlin `commonMain`.
- **Stop and ask** before editing `iosApp.xcodeproj/project.pbxproj`, signing or team settings, `Info.plist` permissions (camera, photos), or `Configuration/Config.xcconfig`.
- New screens go in `iosApp/Features/<Feature>/<Screen>View.swift`. If you add files outside Xcode, say in the PR that they must be added to the target, or ask first.
- Build: `xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -destination 'platform=iOS Simulator,name=<device>' build` (run it from the repo root).
