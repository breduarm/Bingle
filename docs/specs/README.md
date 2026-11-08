# Product Specs

Each feature has one folder, `NNN-<name>/`, which holds three files:

| File | Written by | Approved by | Contains |
|---|---|---|---|
| `spec.md` | Human, or agent draft (`spec-writer`) | Human | What and why: user stories, acceptance criteria, edge cases, out of scope |
| `plan.md` | Agent (plan mode) | Human | How: modules, interfaces, data model, test strategy, risks |
| `tasks.md` | Agent | — | An ordered checklist that the agent ticks off while implementing |

Templates for all three are in [`_template/`](_template/). The full lifecycle is described in [agentic workflow](../workflow/agentic-workflow.md).

## Index

| # | Feature | Status | Depends on |
|---|---|---|---|
| 001 | games: create a game (date + nickname), list games on home | Not started | code skeleton |
| 002 | play: enter a called number, auto-mark all cards, detect bingo | Not started | 001 |
| 003 | card-import: photo or PDF → OCR → reviewed 5×5 card(s) | Not started | 001, card samples |
| 004 | auth: email, Google, and Facebook sign-up; login; logout | Not started | ADR-0007 |

Status values: `Not started` → `Draft` → `Approved` → `In progress` → `Done`.
