# Tech Debt Tracker

These are deliberate shortcuts and deferred work. Each entry needs a reason and a trigger for when to pay it back.

| # | Item | Why deferred | Pay back when | Added |
|---|---|---|---|---|
| 1 | Code skeleton: `build-logic`, `core/*`, Koin, SKIE, umbrella export (ADR-0002…0005) | We set up the agentic workflow first and left the code layout for later | Before spec 001 starts | 2026-11-07 |
| 2 | Spotless/ktlint + detekt + Konsist are not configured yet. Standards are enforced by review only | Depends on #1 | With #1 | 2026-11-07 |
| 3 | No CI workflow (`.github/workflows/ci.yml`) | Needs a stable module layout first | With #1 | 2026-11-07 |
| 4 | Skills `new-feature` (module scaffold) and `swiftui-screen` (SKIE wiring pattern) | They depend on the code skeleton's conventions | Right after #1 | 2026-11-07 |
| 5 | Format-on-edit hook in `.claude/settings.json` | Needs Spotless (#2) | With #2 | 2026-11-07 |
