---
name: verify
description: Run Bingle's Definition-of-Done check suite (build, Android host tests, iOS simulator tests, standards review) before declaring any work done or opening a PR. Use whenever a task is finished or the user asks to verify, check, or validate changes.
---

# Verify

Run this checklist and report the result of each step. The checklist is defined in `docs/workflow/definition-of-done.md`; that file wins if the two disagree.

## 1. Scope
Run `git status` and `git diff --stat` to list the changed modules, then decide which of the steps below apply.

## 2. Build and test (always)
Run each command and capture whether it passed or failed, plus the first relevant error:
```bash
./gradlew build
./gradlew :sharedLogic:testAndroidHostTest :sharedUI:testAndroidHostTest
./gradlew :sharedLogic:iosSimulatorArm64Test
```
When `core/*` or `feature/*` modules exist, use `./gradlew check` plus `allTests` on the changed modules instead. When detekt, Spotless, or Konsist are configured, add `./gradlew spotlessCheck detekt :konsist-tests:test`.

## 3. iOS app build (if Kotlin public API or Swift changed)
```bash
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -destination 'generic/platform=iOS Simulator' build
```

## 4. Static review (always)
Review the diff against these rules:
- `docs/standards/architecture-rules.md`: dependency direction, layers, and naming.
- No business logic in Swift files or Composables.
- No `!!`, no `GlobalScope`, no hard-coded dispatchers.
- No secrets or PII in code, logs, or fixtures.
- New or changed logic has tests, and no test was deleted or weakened.
- Docs are updated where needed (spec status, ADR, ARCHITECTURE.md, CLAUDE.md map).

For a non-trivial diff, delegate this step to the `code-reviewer` subagent.

## 5. UI check (if UI changed)
Run the app on the Android emulator and the iOS simulator. Check the empty, loading, error, and content states, and take screenshots for the PR.

## Report
Finish with a table of steps and results (✅, ❌, or ⏭️ skipped with the reason). If anything failed, say so plainly with the output, and do **not** call the work done.
