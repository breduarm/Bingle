# US 75-Ball Bingo — Domain Rules

This is the reference for parsers, validators, and win detection. If code disagrees with this file, the code is wrong. To change the rules, change this file in a PR first.

## Card layout
- 5 columns headed **B I N G O**, 5 rows, 25 cells in total.
- The center cell (column N, row 3) is **FREE**. It is always marked and has no number.
- So a card has **24 numbers**.

| Column | Allowed range | Numbers on card |
|---|---|---|
| B | 1–15 | 5 |
| I | 16–30 | 5 |
| N | 31–45 | 4 + FREE |
| G | 46–60 | 5 |
| O | 61–75 | 5 |

## Validation rules
Use these when parsing and when the user edits a card.
1. Every number is in its column's range.
2. There are no duplicates within a card.
3. The center cell is FREE, and every other cell holds a number.
4. Order within a column is **not** guaranteed. Many printed cards sort columns ascending, but never rely on that.
5. If a parsed card breaks any rule, it is **not** silently fixed. The cells involved are flagged for the user to review.

## Calls
- Numbers run from 1 to 75. The letter comes from the range (e.g. 7 → B, 52 → G).
- A number can be called only once per game. Entering a duplicate gives a warning and is not marked twice.
- The user can undo the last call, in case of a mistyped number.

## Win patterns (MVP)
The minimum set is listed here. Others are added by spec.

| Pattern | Rule |
|---|---|
| Single line | Any full row, column, or one of the two diagonals (FREE counts as marked) |
| Four corners | The four corner cells |
| Blackout / Coverall | All 25 cells |

Halls announce the pattern for each round. The user picks the active pattern for the game or round.

## Physical-card realities the parser must handle
- Sheets with several cards (often 3, 4, or 6 per page), sometimes with serial numbers or hall branding.
- The BINGO header letters may be missing, stylised, or coloured.
- Glare, perspective skew, creases, and dauber ink on cards that have already been played.
- PDFs from online hall stores. These are often vector text, so text can be extracted without OCR.
