package com.horizon.keyboard.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.filled.KeyboardReturn
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.components.KeyboardKey
import com.horizon.keyboard.keyboard.components.KeyboardSpecialKey
import com.horizon.keyboard.keyboard.components.ToolbarIconButton
import com.horizon.keyboard.keyboard.model.KeyboardLayout
import com.horizon.keyboard.keyboard.model.KeyboardLayouts
import com.horizon.keyboard.keyboard.model.LocalKeyboardColors
import com.horizon.keyboard.keyboard.clipboard.ClipboardItem
import com.horizon.keyboard.keyboard.clipboard.ClipboardPanel
import com.horizon.keyboard.keyboard.emoji.EmojiPicker
import com.horizon.keyboard.keyboard.voice.VoiceState
import com.horizon.keyboard.keyboard.voice.VoiceTypingPanel
import com.horizon.keyboard.keyboard.voice.voiceLanguages
import com.horizon.keyboard.settings.SettingsPanel
import com.horizon.keyboard.settings.ThemeMode

@Composable
fun VirtualKeyboard(
    modifier: Modifier = Modifier,
    currentLayout: KeyboardLayout = KeyboardLayouts.QWERTY,
    typedPrefix: String = "",
    clipboardItems: List<ClipboardItem> = emptyList(),
    voiceState: VoiceState = VoiceState.IDLE,
    voiceRecognizedText: String = "",
    voiceErrorMessage: String = "",
    showSuggestions: Boolean = true,
    hapticEnabled: Boolean = true,
    soundEnabled: Boolean = true,
    autoCorrectEnabled: Boolean = false,
    themeMode: ThemeMode = ThemeMode.AUTO,
    onKeyPress: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onEnter: () -> Unit,
    onSwipeRightToLeft: () -> Unit = {},
    onSuggestionTap: (String) -> Unit = {},
    onLayoutSwitch: () -> Unit = {},
    onShowNumbers: () -> Unit = {},
    onShowEmojis: () -> Unit = {},
    onVoiceTyping: () -> Unit = {},
    onShowClipboard: () -> Unit = {},
    onShowTranslate: () -> Unit = {},
    onOpenSettings: () -> Unit = {},
    onClipboardTap: (ClipboardItem) -> Unit = {},
    onClipboardDelete: (ClipboardItem) -> Unit = {},
    onClipboardPin: (ClipboardItem) -> Unit = {},
    onStartVoice: () -> Unit = {},
    onStopVoice: () -> Unit = {},
    onHapticToggle: (Boolean) -> Unit = {},
    onSoundToggle: (Boolean) -> Unit = {},
    onSuggestionsToggle: (Boolean) -> Unit = {},
    onAutoCorrectToggle: (Boolean) -> Unit = {},
    onThemeChange: (ThemeMode) -> Unit = {}
) {
    var isShift by remember { mutableStateOf(false) }
    var isSymbols by remember { mutableStateOf(false) }
    var isVoiceMode by remember { mutableStateOf(false) }
    var isClipboardMode by remember { mutableStateOf(false) }
    var isEmojiMode by remember { mutableStateOf(false) }
    var isSettingsMode by remember { mutableStateOf(false) }
    var voiceLanguageIndex by remember { mutableStateOf(0) }
    val voiceLanguage = voiceLanguages[voiceLanguageIndex]

    val colors = LocalKeyboardColors.current
    val feedback = rememberKeyboardFeedback(hapticEnabled = hapticEnabled, soundEnabled = soundEnabled)

    val activeLayout = when {
        isSymbols -> KeyboardLayouts.SYMBOLS
        else -> currentLayout
    }

    var swipeAccumulator by remember { mutableStateOf(0f) }
    val layoutDirection = if (activeLayout.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.panelBg)
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp, bottom = 32.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (swipeAccumulator < -80f) {
                            onSwipeRightToLeft()
                        }
                        swipeAccumulator = 0f
                    },
                    onDragCancel = { swipeAccumulator = 0f },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        swipeAccumulator += dragAmount.x
                    }
                )
            }
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            if (isVoiceMode) {
                VoiceTypingPanel(
                    isListening = voiceState == VoiceState.LISTENING,
                    isProcessing = voiceState == VoiceState.PROCESSING,
                    isError = voiceState == VoiceState.ERROR,
                    errorMessage = voiceErrorMessage,
                    recognizedText = voiceRecognizedText,
                    language = voiceLanguage,
                    onLanguageToggle = {
                        voiceLanguageIndex = (voiceLanguageIndex + 1) % voiceLanguages.size
                    },
                    onStartListening = onStartVoice,
                    onStopListening = onStopVoice,
                    onClose = {
                        onStopVoice()
                        isVoiceMode = false
                    }
                )
            } else if (isClipboardMode) {
                ClipboardPanel(
                    items = clipboardItems,
                    onItemTap = { item ->
                        onClipboardTap(item)
                        isClipboardMode = false
                    },
                    onItemDelete = { item ->
                        onClipboardDelete(item)
                    },
                    onItemPin = { item ->
                        onClipboardPin(item)
                    },
                    onClose = { isClipboardMode = false }
                )
            } else if (isEmojiMode) {
                EmojiPicker(
                    onEmojiTap = { emoji -> onKeyPress(emoji) },
                    onBackspace = { onBackspace() },
                    onClose = { isEmojiMode = false }
                )
            } else if (isSettingsMode) {
                SettingsPanel(
                    hapticEnabled = hapticEnabled,
                    soundEnabled = soundEnabled,
                    suggestionsEnabled = showSuggestions,
                    autoCorrectEnabled = autoCorrectEnabled,
                    themeMode = themeMode,
                    onHapticToggle = onHapticToggle,
                    onSoundToggle = onSoundToggle,
                    onSuggestionsToggle = onSuggestionsToggle,
                    onAutoCorrectToggle = onAutoCorrectToggle,
                    onThemeChange = onThemeChange,
                    onClose = { isSettingsMode = false }
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .padding(bottom = 8.dp)
                        .border(
                            width = 1.dp,
                            color = colors.borderColor.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ToolbarIconButton(
                        icon = Icons.Default.Keyboard,
                        contentDescription = "Switch Layout",
                        tint = colors.iconColor,
                        onClick = onLayoutSwitch
                    )
                    ToolbarIconButton(
                        icon = Icons.Default.EmojiEmotions,
                        contentDescription = "Emojis",
                        tint = colors.iconColor,
                        onClick = {
                            isVoiceMode = false
                            isClipboardMode = false
                            isSettingsMode = false
                            isEmojiMode = true
                        }
                    )
                    ToolbarIconButton(
                        icon = Icons.Default.Mic,
                        contentDescription = "Voice Typing",
                        tint = colors.iconColor,
                        onClick = {
                            isClipboardMode = false
                            isEmojiMode = false
                            isSettingsMode = false
                            isVoiceMode = true
                        }
                    )
                    ToolbarIconButton(
                        icon = Icons.Default.ContentPaste,
                        contentDescription = "Clipboard",
                        tint = colors.iconColor,
                        onClick = {
                            isVoiceMode = false
                            isEmojiMode = false
                            isSettingsMode = false
                            isClipboardMode = true
                        }
                    )
                    ToolbarIconButton(
                        icon = Icons.Default.Translate,
                        contentDescription = "Translate",
                        tint = colors.iconColor,
                        onClick = onShowTranslate
                    )
                    ToolbarIconButton(
                        icon = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = colors.iconColor,
                        onClick = {
                            isVoiceMode = false
                            isClipboardMode = false
                            isEmojiMode = false
                            isSettingsMode = true
                        }
                    )
                }
            }

            if (!isVoiceMode && !isClipboardMode && !isEmojiMode && !isSettingsMode) {
                activeLayout.rows.forEachIndexed { rowIndex, row ->
                    val isLastRow = rowIndex == activeLayout.rows.lastIndex
                    val rowWidthFraction = if (rowIndex == 1 && activeLayout.hasCharacters) 0.95f else 1f

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(rowWidthFraction)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        if (isLastRow && activeLayout.hasCharacters) {
                            KeyboardSpecialKey(
                                modifier = Modifier.weight(1.3f),
                                bgColor = if (isShift) {
                                    colors.shiftActiveBg
                                } else {
                                    colors.specialKeyBg
                                },
                                borderColor = if (isShift) {
                                    colors.shiftActiveBorder
                                } else {
                                    colors.borderColor
                                },
                                onClick = { feedback.hapticFeedback(); isShift = !isShift }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowUpward,
                                    contentDescription = "Shift",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        if (isLastRow && !activeLayout.hasCharacters) {
                            KeyboardSpecialKey(
                                modifier = Modifier.weight(1.5f),
                                bgColor = colors.specialKeyBg,
                                onClick = {
                                    feedback.performAll()
                                    isSymbols = false
                                    isShift = false
                                }
                            ) {
                                Text(
                                    text = "ABC",
                                    color = colors.specialTextColor,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        row.forEachIndexed { charIndex, char ->
                            val displayChar = if (isShift && activeLayout.hasCharacters) {
                                activeLayout.shiftRows?.getOrNull(rowIndex)?.getOrNull(charIndex)
                                    ?: char.uppercase()
                            } else {
                                char
                            }
                            val alternates = activeLayout.longPressAlternates[char] ?: emptyList()
                            KeyboardKey(
                                text = displayChar,
                                modifier = Modifier.weight(1f),
                                alternates = alternates,
                                onClick = {
                                    feedback.performAll()
                                    onKeyPress(displayChar)
                                    if (isShift && activeLayout.hasCharacters) isShift = false
                                },
                                onLongPress = { selected ->
                                    feedback.performAll()
                                    onKeyPress(selected)
                                    if (isShift && activeLayout.hasCharacters) isShift = false
                                }
                            )
                        }

                        if (isLastRow && activeLayout.hasCharacters) {
                            KeyboardSpecialKey(
                                modifier = Modifier.weight(1.3f),
                                bgColor = colors.specialKeyBg,
                                onClick = { feedback.performAll(); onBackspace() },
                                repeatOnHold = true
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Backspace,
                                    contentDescription = "Backspace",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        if (isLastRow && !activeLayout.hasCharacters) {
                            KeyboardSpecialKey(
                                modifier = Modifier.weight(1.5f),
                                bgColor = colors.specialKeyBg,
                                onClick = { feedback.performAll(); onBackspace() },
                                repeatOnHold = true
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Backspace,
                                    contentDescription = "Backspace",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    KeyboardSpecialKey(
                        modifier = Modifier.weight(1.5f),
                        bgColor = colors.specialKeyBg,
                        onClick = { feedback.performAll(); onShowNumbers() }
                    ) {
                        Text(
                            text = "123",
                            color = colors.specialTextColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    KeyboardKey(
                        text = ",",
                        modifier = Modifier.weight(1.1f),
                        onClick = { feedback.performAll(); onKeyPress(",") }
                    )

                    KeyboardKey(
                        text = "space",
                        modifier = Modifier.weight(4.5f),
                        onClick = { feedback.performAll(); onSpace() }
                    )

                    KeyboardKey(
                        text = ".",
                        modifier = Modifier.weight(1.1f),
                        onClick = { feedback.performAll(); onKeyPress(".") }
                    )

                    KeyboardSpecialKey(
                        modifier = Modifier.weight(1.5f),
                        bgColor = colors.specialKeyBg,
                        onClick = { feedback.performAll(); onEnter() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardReturn,
                            contentDescription = "Enter",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
private fun PreviewVirtualKeyboard() {
    VirtualKeyboard(
        onKeyPress = {},
        onBackspace = {},
        onSpace = {},
        onEnter = {}
    )
}
