# ADR-0006: On-device OCR with ML Kit (Android) and Apple Vision (iOS)

- **Status:** Proposed
- **Date:** 2026-11-07

## Context
Card import turns a photo or PDF into one or more 5×5 cards. This needs:
1. text recognition;
2. grid reconstruction;
3. validation against the [75-ball rules](../references/bingo-rules-us-75.md).

It must work offline in a bingo hall ([Principle 2](../PRINCIPLES.md)) and keep images private ([Principle 10](../PRINCIPLES.md)).

## Options considered
1. **Platform OCR on device:** ML Kit Text Recognition v2 on Android, Vision `VNRecognizeTextRequest` on iOS. Free, offline, and fast. Returns text blocks with bounding boxes.
2. **Cloud vision LLM.** Very good at reading grids, but needs a network connection, has a cost per scan, and sends images off device.
3. **Custom ML model.** Too heavy for an MVP.

## Decision
Option 1. Define a `TextRecognizer` interface in `feature/cards/platform/` that returns recognized tokens with bounding boxes. Reconstructing the grid and validating it is **pure Kotlin in `commonMain`**, so one implementation is shared and tested against [reference samples](../references/bingo-cards/README.md). For PDFs, extract embedded text first and fall back to rasterizing the page and running OCR.

## Consequences
- The hard part (the parser) is shared, testable, and improves as samples are added.
- There are two thin platform adapters to maintain.
- Accuracy depends on photo quality. The review-and-correct step is mandatory ([Principle 3](../PRINCIPLES.md)).
- Revisit: a cloud LLM fallback may be added later as an opt-in feature, with its own spec and ADR.
