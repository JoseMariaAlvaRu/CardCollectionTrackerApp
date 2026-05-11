# Lorcana Collection Tracker

Cross-platform mobile app to manage a Disney Lorcana card collection, with tracking of loans and sales to other people.

## Platforms

| Platform | Stack | Path | Status |
|----------|-------|------|--------|
| Android  | Kotlin + Jetpack Compose | [`android/`](./android) | 🚧 In development |
| iOS      | Swift + SwiftUI          | [`ios/`](./ios)         | ⏳ Planned |

## Architecture

Both apps share:

- **Backend:** Firebase (Auth + Firestore for multi-device sync)
- **Card data:** [Lorcast API](https://lorcast.com) and [LorcanaJSON](https://lorcanajson.org)
- **Auth:** Google Sign-In

Each app is independently developed in its native language and framework, but shares the same:

- Data model (cards, collection items, persons, transactions)
- Backend (Firebase project)
- Design language (Material 3 on Android, Human Interface Guidelines on iOS)

## Getting Started

### Android

Open the `android/` folder in Android Studio. See [`android/README.md`](./android/README.md) for details.

### iOS

_Coming soon._

## License

TBD.
