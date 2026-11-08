# Git & Pull Requests

## Branches
- `main` is always releasable. Never commit to it directly.
- Branch names:
  - `feat/NNN-<name>`: implements spec NNN, e.g. `feat/002-play`.
  - `fix/<short-desc>`, `chore/<short-desc>`, `docs/<short-desc>`.
- For parallel agent work, use **one git worktree per branch** (`git worktree add ../Bingle-002-play feat/002-play`) or one Claude session per worktree. Two agents never edit the same branch.

## Commits
- Follow [Conventional Commits](https://www.conventionalcommits.org/), e.g.:
  - `feat(play): auto-mark called number on all cards`
  - `test(cards): add parser fixtures for six-per-sheet`
- Scopes: `games`, `play`, `cards`, `auth`, `core`, `ui`, `ios`, `android`, `build`, `docs`, `agents`.
- Keep commits small and logical. Agents commit only when the human asks, or as part of an approved plan.
- Commits written with an agent keep the agent's `Co-Authored-By` trailer.

## Pull requests
- One spec, or one coherent fix, per PR. Aim for under about 400 changed lines, not counting generated files and fixtures.
- Use the [PR template](../../.github/pull_request_template.md): link the spec and plan, tick the [Definition of Done](definition-of-done.md), and add screenshots.
- The human reviews and merges. Use squash merge, with a PR title in Conventional Commit form.
- Agents never force-push to shared branches, never rewrite `main`, and never merge their own PRs.
