package com.horizon.keyboard.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.components.KeyboardKey
import com.horizon.keyboard.keyboard.components.KeyboardSpecialKey
import com.horizon.keyboard.keyboard.components.ToolbarIconButton
import com.horizon.keyboard.keyboard.model.KeyboardColors
import com.horizon.keyboard.keyboard.model.KeyboardLayout
import com.horizon.keyboard.keyboard.model.KeyboardLayouts

@Composable
fun VirtualKeyboard(
    modifier: Modifier = Modifier,
    currentLayout: KeyboardLayout = KeyboardLayouts.QWERTY,
    onKeyPress: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onEnter: () -> Unit,
    onSwipeRightToLeft: () -> Unit = {},
    onLayoutSwitch: () -> Unit = {},
    onShowNumbers: () -> Unit = {},
    onShowEmojis: () -> Unit = {},
    onVoiceTyping: () -> Unit = {},
    onShowClipboard: () -> Unit = {},
    onShowTranslate: () -> Unit = {},
    onOpenSettings: () -> Unit = {}
) {
    var isShift by remember { mutableStateOf(false) }
    var isSymbols by remember { mutableStateOf(false) }

    val activeLayout = when {
        isSymbols -> KeyboardLayouts.SYMBOLS
        else -> currentLayout
    }

    var swipeAccumulator by remember { mutableStateOf(0f) }
    val layoutDirection = if (currentLayout.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(KeyboardColors.PanelBg)
            .padding(horizontal = 6.dp)
            .padding(top = 6.dp, bottom = 32.dp)
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ToolbarIconButton(
                    icon = Icons.Default.Keyboard,
                    contentDescription = "Switch Layout",
                    tint = KeyboardColors.IconColor,
                    onClick = onLayoutSwitch
                )
                ToolbarIconButton(
                    icon = Icons.Default.EmojiEmotions,
                    contentDescription = "Emojis",
                    tint = KeyboardColors.IconColor,
                    onClick = onShowEmojis
                )
                ToolbarIconButton(
                    icon = Icons.Default.Mic,
                    contentDescription = "Voice Typing",
                    tint = KeyboardColors.IconColor,
                    onClick = onVoiceTyping
                )
                ToolbarIconButton(
                    icon = Icons.Default.ContentPaste,
                    contentDescription = "Clipboard",
                    tint = KeyboardColors.IconColor,
                    onClick = onShowClipboard
                )
                ToolbarIconButton(
                    icon = Icons.Default.Translate,
                    contentDescription = "Translate",
                    tint = KeyboardColors.IconColor,
                    onClick = onShowTranslate
                )
                ToolbarIconButton(
                    icon = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = KeyboardColors.IconColor,
                    onClick = onOpenSettings
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(KeyboardColors.BorderColor)
            )

            activeLayout.rows.forEachIndexed { rowIndex, row ->
                val isLastRow = rowIndex == activeLayout.rows.lastIndex
                val horizontalPadding = if (rowIndex == 1 && activeLayout.hasCharacters) 10.dp else 0.dp

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (isLastRow && activeLayout.hasCharacters) {
                        KeyboardSpecialKey(
                            modifier = Modifier.weight(1.3f),
                            bgColor = if (isShift) {
                                KeyboardColors.ShiftActiveBg
                            } else {
                                KeyboardColors.SpecialKeyBg
                            },
                            onClick = { isShift = !isShift }
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
                            bgColor = KeyboardColors.SpecialKeyBg,
                            onClick = {
                                isSymbols = false
                                isShift = false
                            }
                        ) {
                            Text(
                                text = "ABC",
                                color = KeyboardColors.SpecialTextColor,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    row.forEach { char ->
                        val displayChar = if (isShift && activeLayout.hasCharacters) {
                            char.uppercase()
                        } else {
                            char
                        }
                        KeyboardKey(
                            text = displayChar,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onKeyPress(displayChar)
                                if (isShift && activeLayout.hasCharacters) isShift = false
                            }
                        )
                    }

                    if (isLastRow && activeLayout.hasCharacters) {
                        KeyboardSpecialKey(
                            modifier = Modifier.weight(1.3f),
                            bgColor = KeyboardColors.SpecialKeyBg,
                            onClick = { onBackspace() }
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
                            bgColor = KeyboardColors.SpecialKeyBg,
                            onClick = { onBackspace() }
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
                    bgColor = KeyboardColors.SpecialKeyBg,
                    onClick = { onShowNumbers() }
                ) {
                    Text(
                        text = "123",
                        color = KeyboardColors.SpecialTextColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                KeyboardKey(
                    text = ",",
                    modifier = Modifier.weight(1.1f),
                    onClick = { onKeyPress(",") }
                )

                KeyboardKey(
                    text = "space",
                    modifier = Modifier.weight(4.5f),
                    onClick = { onSpace() }
                )

                KeyboardKey(
                    text = ".",
                    modifier = Modifier.weight(1.1f),
                    onClick = { onKeyPress(".") }
                )

                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.5f),
                    bgColor = KeyboardColors.SpecialKeyBg,
                    onClick = { onEnter() }
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
