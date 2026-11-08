# Bingo Card Reference Samples

Put real bingo cards here. They serve two purposes:
1. **Context for agents.** Claude can look at the images to see what real cards look like.
2. **Regression fixtures.** The card-import parser tests run against every sample that has an `expected.json`.

## Folder layout
```
bingo-cards/
├── photos/
│   └── 001-short-name/
│       ├── card.jpg          # the raw photo, as a user would take it (jpg/png/heic)
│       ├── expected.json     # ground truth (see below)
│       └── notes.md          # optional: hall, lighting, glare, sheet with N cards, etc.
└── pdfs/
    └── 001-short-name/
        ├── card.pdf
        └── expected.json
```
- Number the folders in order (`001-`, `002-`, …) and use short kebab-case names, e.g. `004-glare-angled`, `007-six-per-sheet`.
- Keep the raw file unedited: no cropping and no filters. The parser has to cope with real input.
- Before committing, remove anything personal: names, membership numbers, faces.

## `expected.json`
Each entry in `cards` is one card on the sheet, read left to right and top to bottom. Each column lists its numbers from **top to bottom**. Write the FREE center as `0`.

```json
{
  "source": "photo",
  "cardsOnSheet": 1,
  "cards": [
    {
      "serial": "optional printed serial or null",
      "columns": {
        "B": [3, 11, 7, 14, 1],
        "I": [22, 19, 30, 17, 25],
        "N": [41, 33, 0, 38, 44],
        "G": [52, 60, 47, 55, 49],
        "O": [70, 63, 75, 68, 61]
      }
    }
  ]
}
```

You don't have to write `expected.json` yourself. Drop in the image, ask Claude to draft it, and correct the draft.

## Coverage
Aim for 10–20 samples that cover:
- different halls or printers;
- sheets with one card and sheets with several;
- glare, angle, creases, and cards already daubed;
- dark and bright lighting;
- PDFs from online stores, both text-based and scanned.

## Rules for agents
- Never modify or delete a sample's raw file or its `expected.json` without approval from the project owner.
- If a test fails against a sample, fix the parser. Do not change the fixture.
