 # Horizon Keyboard

A modern virtual keyboard application built with Kotlin and Jetpack Compose.

## Features

- **QWERTY Layout** - Standard keyboard layout with 4 rows
- **Dark/Light Theme** - Automatic system theme detection
- **Shift Support** - Toggle uppercase with visual feedback
- **Toolbar** - Quick access to emojis, voice typing, clipboard, translate, and settings
- **Material 3** - Built with latest Material Design components

## Architecture

```
app/src/main/java/com/horizon/keyboard/
├── MainActivity.kt          # Entry point
└── ui/
    ├── VirtualKeyboard.kt   # Main keyboard composable
    └── theme/
        └── Theme.kt         # Material 3 theme configuration
```

## Keyboard Layout

```
┌─────────────────────────────────────────────────────────────┐
│  ⌨  😊  🎤  📋  🌐  ⚙️                                        │ (Toolbar)
├─────────────────────────────────────────────────────────────┤
│  Q  W  E  R  T  Y  U  I  O  P                               │
│   A  S  D  F  G  H  J  K  L                                 │
│  ⇧  Z  X  C  V  B  N  M  ⌫                                 │
│  123  ,  ──────────────  .  ⏎                               │
└─────────────────────────────────────────────────────────────┘
```

## Build Requirements

- Android Studio Ladybug or later
- JDK 17
- Android SDK 35
- Kotlin 2.0.21

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on emulator or device

## Key Specifications

| Property       | Value  |
|---------------|--------|
| Key Height    | 44dp   |
| Key Radius    | 6dp    |
| Row Spacing   | 8dp    |
| Key Spacing   | 6dp    |
| Bottom Padding| 32dp   |

## License

Copyright 2024 Horizon Keyboard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
