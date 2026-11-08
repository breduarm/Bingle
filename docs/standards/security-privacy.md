# Security & Privacy Standards

Agents must **stop and ask** before changing anything this document covers.

## Secrets
- Never commit API keys, OAuth client secrets, the Facebook app secret, or signing keystores.
- Local secrets go in `local.properties` (Android) and in an untracked `Secrets.xcconfig` (iOS). Template files with placeholder values are committed (`*.example`).
- CI secrets live in GitHub Actions secrets only.
- Agents never read, print, or copy secret files, keystores (`*.jks`, `*.keystore`, `*.p12`), or provisioning profiles.

## Auth
- Use each platform's secure storage for tokens: Android Keystore/EncryptedSharedPreferences or DataStore with encryption, and the iOS Keychain. Never store tokens in plain preferences or the database.
- Never log tokens, emails, or provider user IDs.
- Logout clears tokens and the user's synced data cache. Local-only data stays, unless the user chooses to delete it.

## Card images & PDFs
- Imported photos and PDFs are processed **on device** ([ADR-0006](../adr/0006-on-device-ocr-mlkit-vision.md)).
- Store originals in the app's private storage only. Don't save them to the gallery without explicit user action.
- Uploading to a backend requires an opt-in setting and needs its own spec.
- Reference samples committed to `docs/references/bingo-cards/` must be free of personal data.

## Logging
- Production logs carry no PII and no full card contents.
- Debug-only logging is gated behind a build flag.
