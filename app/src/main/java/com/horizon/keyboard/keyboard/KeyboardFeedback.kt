package com.horizon.keyboard.keyboard

import android.media.AudioManager
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

class KeyboardFeedback(
    private val view: View,
    private val audioManager: AudioManager?,
    var hapticEnabled: Boolean = true,
    var soundEnabled: Boolean = true
) {
    fun hapticFeedback() {
        if (!hapticEnabled) return
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
    }

    fun soundFeedback() {
        if (!soundEnabled) return
        audioManager?.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, -1f)
    }

    fun performAll() {
        hapticFeedback()
        soundFeedback()
    }
}

@Composable
fun rememberKeyboardFeedback(
    hapticEnabled: Boolean = true,
    soundEnabled: Boolean = true
): KeyboardFeedback {
    val view = LocalView.current
    val context = view.context
    val audioManager = remember {
        context.getSystemService(AudioManager::class.java)
    }
    val feedback = remember(view, audioManager) {
        KeyboardFeedback(view, audioManager)
    }
    feedback.hapticEnabled = hapticEnabled
    feedback.soundEnabled = soundEnabled
    return feedback
}
