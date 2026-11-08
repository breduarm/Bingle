# Compose (Android UI) Standards

Android screens are Compose code in `sharedUI`, rendering the shared ViewModels.

## Rules
- Split each screen into a stateful route and a stateless screen:
  - `<Screen>Route(viewModel)` collects state with `collectAsStateWithLifecycle()` and passes it down.
  - `<Screen>Screen(state: <Screen>UiState, onEvent: (<Screen>Event) -> Unit)` is stateless and previewable.
- No business logic in Composables. Formatting helpers are fine; rules are not.
- Use Material 3 through the Bingle design system (`ui/designsystem`). Don't hard-code colors or dimensions in screens.
- Give every stateless screen a `@Preview` for its main states: content, empty, loading, and error.
- Put strings in Compose resources (`composeResources/values/strings.xml`), never as literals in UI.
- Accessibility:
  - Every interactive element has a content description or a label.
  - Touch targets are at least 48dp.
  - The **play** screen especially needs large tap targets and high contrast, because halls are dim and fast.
- Navigation is wired in the app layer. Features expose routes and never navigate into each other directly.
