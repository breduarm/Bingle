# Bingle Principles

These are the project's non-negotiables, playing the same role as a Spec Kit "constitution". They apply to every human and every agent. To change one you need an ADR (`docs/adr/`) approved by the project owner.

## Product
1. **Players first, at the table.** A bingo hall is noisy and fast. Marking a called number has to take one action and give instant feedback. If a feature slows that down, it waits.
2. **Offline first.** Playing a game must never need a network. Network is used for sign-in and sync only.
3. **The real card is the truth.** A digital card that disagrees with the paper card is a bug. Before the app trusts an imported card, the user must be able to review and correct it.
4. **US 75-ball bingo first.** Other variants (90-ball, 80-ball) are out of scope until a spec adds them. See [bingo rules](references/bingo-rules-us-75.md).

## Engineering
5. **Share logic, keep the UI native.**
   - Domain rules, data, and UI state (ViewModels) live in Kotlin Multiplatform.
   - Android renders them with Compose, iOS with SwiftUI.
   - Business logic is never duplicated in Swift or in a Composable.
6. **Domain logic is pure and tested first.**
   - Card parsing, validation, number marking, and win detection are pure Kotlin in `commonMain`.
   - Their tests are written before the implementation.
7. **Clear dependency direction.**
   - `feature → core`, never `feature → feature`.
   - Platform code sits behind interfaces declared in `commonMain`.
   - See [architecture rules](standards/architecture-rules.md).
8. **The repository is the system of record.**
   - A decision that is not in `docs/` (spec, ADR, standard) does not exist.
   - Chat history, PR comments, and memory are not documentation.
9. **Small, verifiable steps.**
   - Every change is small enough to review in one sitting.
   - Every change passes the [verify checklist](workflow/definition-of-done.md) before review.
10. **Privacy by default.**
    - Card photos and PDFs stay on the device unless the user opts in to sync.
    - Auth tokens are never logged.
    - See [security & privacy](standards/security-privacy.md).

## Collaboration
11. **Humans decide the what and the why; agents propose the how.**
    - Specs, ADRs, and merges need human approval.
    - See [agentic workflow](workflow/agentic-workflow.md).
12. **When in doubt, stop and ask.** An agent that is unsure asks instead of guessing. Asking is cheaper than a wrong guess.
