# Definition of Done

A change is done only when every applicable box is checked. The `/verify` skill and the PR template both use this list.

## Always
- [ ] It does what the spec's acceptance criteria (or the issue) ask, and nothing more.
- [ ] `./gradlew build` passes.
- [ ] Unit tests pass, on both Android and iOS:
  - `./gradlew :sharedLogic:testAndroidHostTest :sharedUI:testAndroidHostTest`
  - `./gradlew :sharedLogic:iosSimulatorArm64Test`
- [ ] New or changed logic has tests. Domain logic has its tests written first.
- [ ] The change follows [standards](../standards/): no new lint, detekt, or Konsist violations once those tools exist.
- [ ] No secrets, tokens, or personal data appear in code, logs, fixtures, or commits.
- [ ] Docs are updated where the behavior or a decision changed (spec status, ADR, `ARCHITECTURE.md`, `CLAUDE.md` map).

## When UI changed
- [ ] Ran on the Android emulator and the iOS simulator.
- [ ] The PR includes screenshots from both platforms.
- [ ] Empty, loading, and error states are handled. Text supports Dynamic Type and font scaling.

## When working from a spec
- [ ] All boxes in `tasks.md` are checked, and anything deferred is moved to [tech-debt-tracker](../exec-plans/tech-debt-tracker.md).
- [ ] The spec's `Status` is updated, and the exec plan (if any) is moved to `completed/`.

## Coverage targets
- `domain/` packages: at least 80% line coverage.
- ViewModels: every `UiState` transition named in the spec has a test.
- UI: no coverage target. Rely on manual checks and screenshots until UI tests are introduced.
