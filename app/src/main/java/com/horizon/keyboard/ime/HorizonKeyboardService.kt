package com.horizon.keyboard.ime

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.horizon.keyboard.keyboard.VirtualKeyboard
import com.horizon.keyboard.keyboard.clipboard.ClipboardHistory
import com.horizon.keyboard.keyboard.model.KeyboardLayouts
import com.horizon.keyboard.keyboard.voice.VoiceState
import com.horizon.keyboard.keyboard.voice.VoiceTypingManager
import com.horizon.keyboard.keyboard.voice.voiceLanguages
import com.horizon.keyboard.settings.KeyboardSettings
import com.horizon.keyboard.settings.ThemeMode
import com.horizon.keyboard.suggestion.SuggestionEngine
import com.horizon.keyboard.theme.HorizonKeyboardTheme

class HorizonKeyboardService : InputMethodService() {

    private var currentInput: InputConnection? = null
    private lateinit var clipboardHistory: ClipboardHistory
    private lateinit var voiceManager: VoiceTypingManager
    private lateinit var settings: KeyboardSettings

    override fun onCreate() {
        super.onCreate()
        clipboardHistory = ClipboardHistory(this)
        voiceManager = VoiceTypingManager(this)
        settings = KeyboardSettings(this)
    }

    override fun onCreateInputView(): View {
        return ComposeView(this).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                HorizonKeyboardTheme {
                    KeyboardIME(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    override fun onStartInputView(info: EditorInfo, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        currentInput = currentInputConnection
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        currentInput = null
        voiceManager.stopListening()
    }

    override fun onDestroy() {
        voiceManager.destroy()
        super.onDestroy()
    }

    @Composable
    private fun KeyboardIME(modifier: Modifier = Modifier) {
        var currentLayoutState by remember { mutableStateOf(KeyboardLayouts.QWERTY) }
        var currentWord by remember { mutableStateOf("") }
        var clipboardItems by remember { mutableStateOf(clipboardHistory.items) }
        var voiceState by remember { mutableStateOf(VoiceState.IDLE) }
        var voiceText by remember { mutableStateOf("") }
        var voiceError by remember { mutableStateOf("") }
        var voiceLanguageIndex by remember { mutableStateOf(0) }
        var showSuggestions by remember { mutableStateOf(settings.showSuggestions) }
        var hapticEnabled by remember { mutableStateOf(settings.hapticFeedback) }
        var soundEnabled by remember { mutableStateOf(settings.soundFeedback) }
        var autoCorrectEnabled by remember { mutableStateOf(settings.autoCorrect) }
        var themeMode by remember { mutableStateOf(settings.themeMode) }

        voiceManager.onResult = { text ->
            currentInput?.commitText("$text ", 1)
            currentWord = ""
            voiceText = text
            voiceState = VoiceState.IDLE
            voiceError = ""
        }
        voiceManager.onPartialResult = { text ->
            voiceText = text
        }
        voiceManager.onStateChange = { state ->
            voiceState = state
        }
        voiceManager.onError = { message ->
            voiceState = VoiceState.ERROR
            voiceError = message
        }

        VirtualKeyboard(
            modifier = modifier,
            currentLayout = currentLayoutState,
            typedPrefix = currentWord,
            clipboardItems = clipboardItems,
            voiceState = voiceState,
            voiceRecognizedText = voiceText,
            voiceErrorMessage = voiceError,
            showSuggestions = showSuggestions,
            hapticEnabled = hapticEnabled,
            soundEnabled = soundEnabled,
            autoCorrectEnabled = autoCorrectEnabled,
            themeMode = themeMode,
            onKeyPress = { text ->
                currentInput?.commitText(text, 1)
                currentWord += text
            },
            onBackspace = {
                currentInput?.deleteSurroundingText(1, 0)
                if (currentWord.isNotEmpty()) {
                    currentWord = currentWord.dropLast(1)
                }
            },
            onSpace = {
                if (autoCorrectEnabled && currentWord.isNotEmpty()) {
                    val correction = SuggestionEngine.getAutoCorrect(currentWord)
                    if (correction != null) {
                        val deleteCount = currentWord.length
                        currentInput?.deleteSurroundingText(deleteCount, 0)
                        currentInput?.commitText("$correction ", 1)
                    } else {
                        currentInput?.commitText(" ", 1)
                    }
                } else {
                    currentInput?.commitText(" ", 1)
                }
                currentWord = ""
            },
            onEnter = {
                currentInput?.commitText("\n", 1)
                currentWord = ""
            },
            onSwipeRightToLeft = {
                currentInput?.deleteSurroundingText(5, 0)
                currentWord = ""
            },
            onSuggestionTap = { word ->
                val deleteCount = currentWord.length
                if (deleteCount > 0) {
                    currentInput?.deleteSurroundingText(deleteCount, 0)
                }
                currentInput?.commitText("$word ", 1)
                currentWord = ""
            },
            onLayoutSwitch = {
                val layouts = KeyboardLayouts.characterLayouts
                val currentIndex = layouts.indexOf(currentLayoutState).let { if (it < 0) 0 else it }
                val nextIndex = (currentIndex + 1) % layouts.size
                currentLayoutState = layouts[nextIndex]
            },
            onShowNumbers = {
                currentLayoutState = KeyboardLayouts.SYMBOLS
            },
            onClipboardTap = { item ->
                currentInput?.commitText(item.text, 1)
                clipboardItems = clipboardHistory.items
            },
            onClipboardDelete = { item ->
                clipboardHistory.deleteEntry(item)
                clipboardItems = clipboardHistory.items
            },
            onClipboardPin = { item ->
                clipboardHistory.togglePin(item)
                clipboardItems = clipboardHistory.items
            },
            onStartVoice = {
                voiceText = ""
                voiceError = ""
                val language = voiceLanguages.getOrElse(voiceLanguageIndex) { voiceLanguages[0] }
                voiceManager.startListening(language.localeCode)
            },
            onStopVoice = {
                voiceManager.stopListening()
            },
            onHapticToggle = { enabled ->
                hapticEnabled = enabled
                settings.hapticFeedback = enabled
            },
            onSoundToggle = { enabled ->
                soundEnabled = enabled
                settings.soundFeedback = enabled
            },
            onSuggestionsToggle = { enabled ->
                showSuggestions = enabled
                settings.showSuggestions = enabled
            },
            onAutoCorrectToggle = { enabled ->
                autoCorrectEnabled = enabled
                settings.autoCorrect = enabled
            },
            onThemeChange = { mode ->
                themeMode = mode
                settings.themeMode = mode
            },
            onShowEmojis = { },
            onShowTranslate = { },
            onOpenSettings = { }
        )
    }
}
