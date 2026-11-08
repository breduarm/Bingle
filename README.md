# Bingle

**Your real-life bingo cards, on your phone.** Bingle is an Android and iOS companion app for people who play US 75-ball bingo in halls.

- 📸 **Import cards.** Take a photo of your paper cards, or upload a PDF, and Bingle reads them into virtual 5×5 cards.
- 🗓️ **Organize by game.** Create a game with a date and a nickname, and attach that day's cards.
- 🔢 **Play.** Type each called number. Bingle marks it on every card and tells you when you have a bingo.
- 👤 **Account.** Sign up with email, Google, or Facebook.

> Status: early development. The MVP specs are in [docs/specs](docs/specs/README.md).

## Tech
- Kotlin Multiplatform shares the domain, data, and ViewModels.
- The UI is native on each platform: Jetpack Compose on Android, SwiftUI on iOS. See [ADR-0001](docs/adr/0001-native-swiftui-shared-logic.md).
- The module layout and data flow are in [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

## Getting started
**Requirements:**
- JDK 17+
- Android Studio (latest stable) with the Kotlin Multiplatform plugin
- Xcode (latest stable) for iOS

**Android**
```bash
./gradlew :androidApp:assembleDebug
```
You can also run the `androidApp` configuration from Android Studio.

**iOS:** open `iosApp/iosApp.xcodeproj` in Xcode and run it. The Kotlin framework is built automatically by a build phase.

**Tests**
```bash
./gradlew :sharedLogic:testAndroidHostTest :sharedUI:testAndroidHostTest
./gradlew :sharedLogic:iosSimulatorArm64Test
```

## Project layout
| Path | What |
|---|---|
| `sharedLogic/` | Shared Kotlin logic. It also produces the iOS framework |
| `sharedUI/` | Compose UI (Android) |
| `androidApp/` | Android app entry point |
| `iosApp/` | Xcode project and SwiftUI app |
| `docs/` | Specs, ADRs, standards, workflow, and reference material |
| `.claude/` | Configuration for the AI coding agent (Claude Code) |

## How we work
This project is built by humans and AI agents working together:
- humans own the specs, decisions, and reviews;
- agents implement against approved specs.

Before contributing, read [CONTRIBUTING.md](CONTRIBUTING.md). If you are an AI agent, start at [CLAUDE.md](CLAUDE.md).
