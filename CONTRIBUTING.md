# Contributing to Bingle

Bingle is developed by humans and AI coding agents (Claude Code) together. This page is the short version. The full operating model is in [docs/workflow/agentic-workflow.md](docs/workflow/agentic-workflow.md).

## The flow
1. **Spec:** describe the feature. An agent can draft `docs/specs/NNN-name/spec.md`. A human approves it.
2. **Plan:** an agent writes `plan.md` + `tasks.md` in plan mode. A human approves them.
3. **Implement:** work on branch `feat/NNN-name`, writing tests first for domain logic.
4. **Verify:** run `/verify` (or the [Definition of Done](docs/workflow/definition-of-done.md) by hand), and run the app on both platforms.
5. **PR:** fill in the template. A human reviews and merges.

Small fixes and doc changes skip steps 1–2.

## Working with the agent: tips for humans
- Start each feature in a **fresh Claude Code session**. Point it at the spec: *"Implement docs/specs/002-play. Start in plan mode."*
- Approve the plan before any code is written. Correcting a plan is cheap; correcting code is not.
- Give context as files, not as chat. Card samples go in `docs/references/bingo-cards/`, and wireframes go in `docs/references/ui/`.
- If the agent gets something wrong because a rule was missing, **add the rule to `docs/standards/`** (or the CLAUDE.md map), so it won't happen again.
- To run features in parallel, give each one its own branch, worktree, and session.

## Where to put things
| You have… | Put it in… |
|---|---|
| A feature idea | An issue, or ask the agent to draft `docs/specs/NNN-*/spec.md` |
| An architecture decision | `docs/adr/NNNN-*.md` (`/write-adr`) |
| A coding rule or convention | `docs/standards/*.md` |
| Real bingo cards (photos or PDFs) | `docs/references/bingo-cards/` |
| Mockups or screenshots | `docs/references/ui/` |
| A shortcut you took on purpose | `docs/exec-plans/tech-debt-tracker.md` |

## Rules
- Follow the [principles](docs/PRINCIPLES.md) and the [standards](docs/standards/).
- Branch names, commit messages, and PR rules are in [git-and-pr.md](docs/workflow/git-and-pr.md).
- Never commit secrets. See [security-privacy.md](docs/standards/security-privacy.md).
