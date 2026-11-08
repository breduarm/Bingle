---
name: write-adr
description: Draft an Architecture Decision Record in docs/adr/ for Bingle. Use when a plan introduces a new library, pattern, or architectural choice, or when the user asks to record a decision.
---

# Write an ADR

1. Read `docs/adr/README.md` to find the next free number (`NNNN`, zero-padded).
2. Copy `docs/adr/0000-template.md` to `docs/adr/NNNN-<kebab-title>.md`.
3. Fill it in:
   - **Status:** `Proposed`. Only the human sets `Accepted`.
   - **Date:** today.
   - **Context:** the facts and constraints. Link the specs, principles, and references involved.
   - **Options considered:** at least two real options, each with pros and cons. Mark claims you could not verify as unverified.
   - **Decision**, and **Consequences**, including any follow-up tasks.
4. If the decision supersedes an older ADR, set the old one's status to `Superseded by ADR-NNNN`. That is the only edit allowed on an accepted ADR.
5. Add a row to the index table in `docs/adr/README.md`.
6. Tell the human the ADR is ready for review. Don't implement anything that depends on it until it is accepted.
