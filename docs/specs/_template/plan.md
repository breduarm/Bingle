# Plan NNN: <Feature name>

- **Status:** Draft | Approved
- **Spec:** [spec.md](spec.md)

## Approach
Two to five sentences describing how the spec will be met.

## Modules touched
| Module | Change |
|---|---|
| `feature/<name>` | new / modified |

## Domain model & interfaces
List the public types, use cases, repository interfaces, and platform interfaces (signatures only).

## Data
Cover entities, DAOs, and migrations. Any migration requires human approval.

## UI
- Android (`sharedUI`): routes and screens.
- iOS (`iosApp/Features/<Feature>`): views and wrappers.

## Test strategy
For each acceptance criterion, name the test(s) that cover it.

| AC | Test |
|---|---|
| AC-1 | `FooUseCaseTest.\`does x when y\`` |

## Risks & open questions
- …

## New dependencies / ADRs
List anything here that needs approval before implementation starts.
