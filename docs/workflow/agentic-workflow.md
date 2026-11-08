# Agentic Workflow: How Humans and Agents Work Together

This is the operating model for building Bingle with AI agents (Claude Code). It is based on Anthropic's Claude Code best practices, OpenAI's "harness engineering" (the repository as the system of record), and GitHub Spec Kit (spec-driven development).

## Roles

| | Human (project owner) | Agent (Claude Code) |
|---|---|---|
| **Owns** | What and why: product, priorities, architecture decisions, merges | How: plans, code, tests, docs updates |
| **Writes** | Feature ideas, reference material (card samples, wireframes), reviews | Draft specs, `plan.md`, `tasks.md`, ADR drafts, code, tests, exec-plan logs |
| **Approves** | Specs, plans, ADRs, PRs | Nothing. Agents propose; humans approve. |

## The feature lifecycle

```
 idea ─▶ 1 SPEC ─▶ 2 PLAN ─▶ 3 IMPLEMENT ─▶ 4 VERIFY ─▶ 5 PR ─▶ merged
         human ✔    human ✔                              human ✔
```

### 1. Spec: what and why
- The human describes the feature in chat or in an issue. The agent (`spec-writer` subagent) drafts `docs/specs/NNN-<name>/spec.md` from the [template](../specs/_template/spec.md).
- The spec contains user stories, **testable acceptance criteria**, edge cases, and anything out of scope. It contains **no implementation detail**.
- ✔ **Gate:** the human edits and approves it, and sets `Status: Approved` in the spec.

### 2. Plan: how
- The agent works in **plan mode**: it reads `CLAUDE.md`, then the spec, then the relevant `standards/` and ADRs, then the code.
- It writes `plan.md` (approach, modules touched, interfaces, test strategy, risks) and `tasks.md` (ordered, small, checkable steps).
- A new architectural choice gets an ADR draft (use the `/write-adr` skill).
- ✔ **Gate:** the human approves the plan. Any open questions are resolved here, not during implementation.

### 3. Implement
- Use one branch per spec: `feat/NNN-<name>`. To run features in parallel, use one git worktree or Claude session per spec.
- Follow this order: domain tests → domain → data → ViewModel (+ tests) → Android UI → iOS UI.
- Tick off `tasks.md` as you go. Work that spans several sessions keeps a log in `docs/exec-plans/active/NNN-<name>.md` (progress and a decision log), so the next session can resume.

### 4. Verify
- Run the `/verify` skill. It is the same checklist as the [Definition of Done](definition-of-done.md).
- Run the app on the Android emulator **and** the iOS simulator for any UI change.
- Have the `code-reviewer` subagent review the diff against `standards/`, then fix what it finds.

### 5. Pull request
- Fill in the PR template: link the spec, check the DoD, and add screenshots from both platforms.
- ✔ **Gate:** the human reviews and merges.
- The exec plan moves to `docs/exec-plans/completed/`. The spec status becomes `Done`.

## Stop-and-ask rules
The agent **must stop and ask the human** before it:
- adds, removes, or upgrades a dependency (`gradle/libs.versions.toml`, Swift packages);
- changes a module's public API that another module uses, or edits a feature it was not asked to work on;
- edits `*.pbxproj`, signing settings, `.github/workflows/`, or `.claude/settings.json`;
- writes or changes a database migration;
- touches auth, tokens, or the storage of card images (see [security & privacy](../standards/security-privacy.md));
- deviates from an approved spec or plan, including when the plan turns out to be wrong;
- deletes files it did not create in the current task, or changes reference fixtures.

## Small changes
For a typo, a one-line fix, or a docs tweak, skip the spec and plan and go straight to implement, verify, and PR. Rule of thumb: if you can describe the diff in one sentence, it doesn't need a spec.

## Context hygiene for agents
- `CLAUDE.md` is a **map**. Load the deeper docs only when the task needs them.
- Start a fresh session for each spec. Hand off through the exec plan, not through chat history.
- Never treat chat as documentation. If a decision matters, write it into the spec, the plan, or an ADR.
- When reality and the docs disagree, flag it. Fixing stale docs is part of the task.
