# Execution Plans

An exec plan is a **living log** for work that spans more than one agent session. It lets a new session, or a human, pick up exactly where the last one stopped without needing the old chat history.

- `active/NNN-<name>.md`: work in progress. Update it at the end of every session.
- `completed/`: move the file here when the PR merges.
- [tech-debt-tracker.md](tech-debt-tracker.md): shortcuts we chose deliberately, and work we deferred.

Small, single-session changes don't need an exec plan.

## Template
```markdown
# Exec plan NNN: <name>
Spec: ../../specs/NNN-<name>/spec.md · Branch: feat/NNN-<name>

## Progress
- [x] 2026-11-08: domain tests + CardValidator (AC-1..3)
- [ ] next: parser grid reconstruction

## Decision log
- 2026-11-08: chose X over Y because … (if architectural, write an ADR)

## Surprises / blockers
- …

## Next session should start by
- …
```
