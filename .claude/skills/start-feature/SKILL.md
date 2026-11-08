---
name: start-feature
description: Start implementing a Bingle feature from its spec. Checks the spec is approved, writes plan.md and tasks.md, creates the feature branch, and opens an exec plan. Use when the user says "start/implement spec NNN" or "work on feature X".
---

# Start a feature

Argument: a spec number or name, e.g. `002` or `play`.

1. **Find the spec.** Look for `docs/specs/NNN-*/spec.md`.
   - If it doesn't exist, stop. Offer to draft it with the `spec-writer` subagent.
   - If its `Status` is not `Approved`, stop and ask the human to approve it first.
2. **Load context, and only what this feature needs:**
   - `CLAUDE.md`
   - the spec
   - `docs/PRINCIPLES.md` and `docs/standards/architecture-rules.md`
   - any ADRs and references the spec links to
   - the existing code in the modules this feature will touch
3. **Plan (read-only).** Write `docs/specs/NNN-*/plan.md` and `tasks.md` from `docs/specs/_template/`.
   - Map every acceptance criterion to at least one test.
   - List every new dependency or architectural choice under "New dependencies / ADRs". These need approval.
4. **Ask for approval** of the plan. Do not write production code before the human approves.
5. Once approved:
   - Create the branch `feat/NNN-<name>` from an up-to-date `main`.
   - Set the spec to `Status: In progress`.
   - Create `docs/exec-plans/active/NNN-<name>.md` from the template in `docs/exec-plans/README.md`.
6. **Implement** in the order given in `tasks.md`. Write domain tests first, and tick off tasks as they are done.
7. At the end of each session, update the exec plan's progress, decision log, and "Next session should start by" section.
8. When all tasks are done, run `/verify`, then prepare the PR using `.github/pull_request_template.md`.
