# Glossary

Use these terms, in English, in code, docs, and UI strings keys. Each row shows which name to use in code and which to avoid.

| Term | Code name | Meaning |
|---|---|---|
| **Game** (es: *partida*) | `Game` | One bingo session the user plays, on a date, at a place. It has a nickname (e.g. "Friday at St. Mary's") and owns a set of cards. Don't use `Match`, `Session`, or `Partida`. |
| **Card** | `BingoCard` | One 5×5 grid of numbers under the columns B‑I‑N‑G‑O. |
| **Sheet** | `CardSheet` | One physical paper or PDF page. It may hold several cards (halls often print 3–6 per sheet). Importing one sheet can produce many cards. |
| **Cell** | `Cell` | One position on a card. It has a column, a row, a number (or FREE), and a marked state. |
| **FREE space** | `Cell.isFree` | The center cell (N column, row 3). It is always marked. |
| **Call** | `Call` | One number announced by the caller, e.g. "B‑7". In US 75-ball, the letter is implied by the number. |
| **Daub / Mark** | `mark()` | Marking a called number on a card. The app does this automatically on every card in the game. |
| **Pattern** | `WinPattern` | The shape needed to win: a line, four corners, blackout/coverall, and so on. |
| **Bingo** | `Win` | A card that completes the active pattern. |
| **Import** | `CardImport` | Turning a photo or PDF into one or more `BingoCard`s through OCR, parsing, user review, and save. |
| **Ground truth** | `expected.json` | The correct grid for a reference sample. Used by tests. See [bingo-cards](references/bingo-cards/README.md). |
