ui# Horizon Keyboard

A modern Android virtual keyboard (IME) built with Kotlin and Jetpack Compose.

## Features

- **Multi-language Layouts** - QWERTY (English), Arabic, Hebrew with RTL support
- **Light/Dark Theme** - Automatic system theme detection with manual override
- **Word Suggestions** - Prefix-based word completion from built-in dictionary
- **Auto-Correct** - Levenshtein distance-based typo correction
- **Emoji Picker** - 7 categories with 400+ emojis
- **Voice Typing** - Android SpeechRecognizer integration
- **Clipboard Manager** - Persistent clipboard history with pin/delete support
- **Long-Press Alternates** - Hold keys for accented characters and symbols
- **Haptic & Sound Feedback** - Configurable key press feedback
- **Backspace Repeat** - Hold backspace for continuous deletion
- **Settings Panel** - Theme, feedback, suggestions, and auto-correct toggles
- **Material 3** - Built with latest Material Design components

## Architecture

```
app/src/main/java/com/horizon/keyboard/
├── MainActivity.kt                    # Entry point activity
├── ime/
│   └── HorizonKeyboardService.kt      # Android IME service
├── keyboard/
│   ├── VirtualKeyboard.kt             # Main keyboard composable
│   ├── KeyboardFeedback.kt            # Haptic & sound feedback
│   ├── clipboard/
│   │   ├── ClipboardPanel.kt          # Clipboard UI
│   │   └── ClipboardHistory.kt        # Clipboard persistence
│   ├── components/
│   │   ├── KeyboardKey.kt             # Alphanumeric key with long-press
│   │   ├── KeyboardSpecialKey.kt      # Special key with repeat-on-hold
│   │   ├── LongPressPopup.kt          # Alternate character popup
│   │   ├── RepeatOnLongPress.kt       # Long-press repeat modifier
│   │   └── ToolbarIconButton.kt       # Toolbar button
│   ├── emoji/
│   │   ├── EmojiData.kt               # Emoji categories & data
│   │   └── EmojiPicker.kt            # Emoji picker UI
│   ├── model/
│   │   ├── KeyboardColors.kt          # Theme-aware color system
│   │   └── KeyboardLayout.kt         # Layout data model
│   └── voice/
│       ├── VoiceTypingPanel.kt        # Voice typing UI
│       └── VoiceTypingManager.kt      # SpeechRecognizer wrapper
├── settings/
│   ├── KeyboardSettings.kt            # SharedPreferences wrapper
│   └── SettingsPanel.kt              # Settings UI
├── suggestion/
│   ├── Dictionary.kt                  # Word list (~500 words)
│   ├── SuggestionBar.kt              # Suggestion chips UI
│   └── SuggestionEngine.kt           # Suggestions & auto-correct
└── theme/
    └── Theme.kt                       # Material 3 light/dark theme
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
4. Run on emulator or device (minSdk 26)
5. Enable "Horizon Keyboard" in System > Languages > On-screen keyboard

## Key Specifications

| Property       | Value  |
|---------------|--------|
| Key Height    | 52dp   |
| Key Radius    | 8dp    |
| Row Spacing   | 6dp    |
| Key Spacing   | 5dp    |
| Font Size     | 20sp   |
| Bottom Padding| 28dp   |
| minSdk        | 26     |
| targetSdk     | 35     |

## Testing

Run unit tests:
```bash
./gradlew testDebugUnitTest
```

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
