# Lorcana Collection Tracker — Android

Native Android app built with Kotlin and Jetpack Compose.

## Stack

- Kotlin 2.0
- Jetpack Compose + Material 3
- MVVM + Clean Architecture (multi-module)
- Hilt (DI)
- Room (local DB)
- Retrofit + Kotlinx Serialization
- Firebase Auth + Firestore
- Coil (image loading)

## Requirements

- Android Studio Ladybug or newer
- JDK 17 (bundled with Android Studio)
- minSdk 26 (Android 8.0 Oreo)
- A `google-services.json` file in `app/` (request it or generate from Firebase Console)

## Setup

1. Clone the parent repo
2. **Open the `android/` folder in Android Studio (NOT the repo root)**
3. Place `google-services.json` in `android/app/`
4. Sync Gradle and run

## Module Structure (planned)

\`\`\`
:app
:core:common
:core:ui
:core:database
:core:network
:core:domain
:feature:auth
:feature:collection
:feature:loans
:feature:card-detail
:feature:person-detail
\`\`\`
