# ADR-0004: Use Room KMP for local persistence

- **Status:** Proposed
- **Date:** 2026-11-07

## Context
Games, cards, and calls must persist offline ([Principle 2](../PRINCIPLES.md)). The data is relational: a game has many cards, a game has many calls, and a card has 25 cells. Queries must be reactive so marking updates the UI immediately.

## Options considered
1. **Room KMP.** Google-maintained and stable for KMP. Annotation-based DAOs, `Flow` queries, and migrations with schema export.
2. **SQLDelight.** SQL first, generates type-safe Kotlin, mature on KMP. More SQL to write by hand.
3. **DataStore / files.** Not suited to relational queries.

## Decision
Room KMP with the bundled SQLite driver, in `core/database`. Schemas are exported to `core/database/schemas/` and committed.

## Consequences
- Familiar to Android developers, with reactive `Flow` queries.
- KSP must run per target, which adds build time.
- Migrations need human approval ([stop-and-ask](../workflow/agentic-workflow.md#stop-and-ask-rules)).
