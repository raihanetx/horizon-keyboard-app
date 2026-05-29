package com.horizon.keyboard.settings

import android.content.Context
import android.content.SharedPreferences

class KeyboardSettings(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "keyboard_settings", Context.MODE_PRIVATE
    )

    var hapticFeedback: Boolean
        get() = prefs.getBoolean(KEY_HAPTIC, true)
        set(value) = prefs.edit().putBoolean(KEY_HAPTIC, value).apply()

    var soundFeedback: Boolean
        get() = prefs.getBoolean(KEY_SOUND, true)
        set(value) = prefs.edit().putBoolean(KEY_SOUND, value).apply()

    var showSuggestions: Boolean
        get() = prefs.getBoolean(KEY_SUGGESTIONS, true)
        set(value) = prefs.edit().putBoolean(KEY_SUGGESTIONS, value).apply()

    var autoCorrect: Boolean
        get() = prefs.getBoolean(KEY_AUTO_CORRECT, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_CORRECT, value).apply()

    var themeMode: ThemeMode
        get() = ThemeMode.entries.getOrElse(prefs.getInt(KEY_THEME, 0)) { ThemeMode.AUTO }
        set(value) = prefs.edit().putInt(KEY_THEME, value.ordinal).apply()

    var vibrationDuration: Int
        get() = prefs.getInt(KEY_VIBRATION_DURATION, 30)
        set(value) = prefs.edit().putInt(KEY_VIBRATION_DURATION, value).apply()

    companion object {
        private const val KEY_HAPTIC = "haptic_feedback"
        private const val KEY_SOUND = "sound_feedback"
        private const val KEY_SUGGESTIONS = "show_suggestions"
        private const val KEY_AUTO_CORRECT = "auto_correct"
        private const val KEY_THEME = "theme_mode"
        private const val KEY_VIBRATION_DURATION = "vibration_duration"
    }
}

enum class ThemeMode(val label: String) {
    AUTO("System Default"),
    LIGHT("Light"),
    DARK("Dark")
}
