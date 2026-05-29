package com.horizon.keyboard.keyboard.model

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class KeyboardColors(
    val panelBg: Color,
    val borderColor: Color,
    val keyBg: Color,
    val specialKeyBg: Color,
    val shiftActiveBg: Color,
    val shiftActiveBorder: Color,
    val iconColor: Color,
    val textColor: Color,
    val specialTextColor: Color,
    val keyBorderBottom: Color,
    val suggestionBg: Color,
    val rippleColor: Color
)

val LightKeyboardColors = KeyboardColors(
    panelBg = Color(0xFFE5E7EB),
    borderColor = Color(0xFFD1D5DB),
    keyBg = Color(0xFFF8FAFC),
    specialKeyBg = Color(0xFFEBEDF0),
    shiftActiveBg = Color(0xFF3B82F6),
    shiftActiveBorder = Color(0xFF1D4ED8),
    iconColor = Color(0xFF6B7280),
    textColor = Color(0xFF1E293B),
    specialTextColor = Color(0xFF334155),
    keyBorderBottom = Color(0xFFD1D5DB),
    suggestionBg = Color(0xFFF1F5F9),
    rippleColor = Color.Black.copy(alpha = 0.1f)
)

val DarkKeyboardColors = KeyboardColors(
    panelBg = Color(0xFF1F2937),
    borderColor = Color(0xFF374151),
    keyBg = Color(0xFF374151),
    specialKeyBg = Color(0xFF1F2937),
    shiftActiveBg = Color(0xFF2563EB),
    shiftActiveBorder = Color(0xFF1D4ED8),
    iconColor = Color(0xFF9CA3AF),
    textColor = Color(0xFFFFFFFF),
    specialTextColor = Color(0xFFD1D5DB),
    keyBorderBottom = Color(0xFF111827),
    suggestionBg = Color(0xFF374151),
    rippleColor = Color.White.copy(alpha = 0.2f)
)

val LocalKeyboardColors = staticCompositionLocalOf { DarkKeyboardColors }
