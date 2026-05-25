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
import com.horizon.keyboard.keyboard.model.KeyboardLayouts
import com.horizon.keyboard.theme.HorizonKeyboardTheme

class HorizonKeyboardService : InputMethodService() {

    private var currentInput: InputConnection? = null

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
    }

    @Composable
    private fun KeyboardIME(modifier: Modifier = Modifier) {
        var currentLayoutState by remember { mutableStateOf(KeyboardLayouts.QWERTY) }

        VirtualKeyboard(
            modifier = modifier,
            currentLayout = currentLayoutState,
            onKeyPress = { text ->
                currentInput?.commitText(text, 1)
            },
            onBackspace = {
                currentInput?.deleteSurroundingText(1, 0)
            },
            onSpace = {
                currentInput?.commitText(" ", 1)
            },
            onEnter = {
                currentInput?.commitText("\n", 1)
            },
            onSwipeRightToLeft = {
                currentInput?.deleteSurroundingText(5, 0)
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
            onShowEmojis = { },
            onVoiceTyping = { },
            onShowClipboard = { },
            onShowTranslate = { },
            onOpenSettings = { }
        )
    }
}
