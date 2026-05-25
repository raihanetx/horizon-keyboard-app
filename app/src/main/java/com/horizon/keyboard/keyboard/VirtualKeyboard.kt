package com.horizon.keyboard.keyboard

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.components.KeyboardKey
import com.horizon.keyboard.keyboard.components.KeyboardSpecialKey
import com.horizon.keyboard.keyboard.components.ToolbarIconButton
import com.horizon.keyboard.keyboard.model.KeyboardColors

@Composable
fun VirtualKeyboard(
    modifier: Modifier = Modifier,
    onKeyPress: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onEnter: () -> Unit
) {
    var isShift by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(KeyboardColors.PanelBg)
            .padding(horizontal = 6.dp)
            .padding(top = 6.dp, bottom = 32.dp)
    ) {
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
                ToolbarIconButton(icon = Icons.Default.Keyboard, contentDescription = "Keyboard Layout", tint = KeyboardColors.IconColor)
                ToolbarIconButton(icon = Icons.Default.EmojiEmotions, contentDescription = "Emojis", tint = KeyboardColors.IconColor)
                ToolbarIconButton(icon = Icons.Default.Mic, contentDescription = "Voice Typing", tint = KeyboardColors.IconColor)
                ToolbarIconButton(icon = Icons.Default.ContentPaste, contentDescription = "Clipboard", tint = KeyboardColors.IconColor)
                ToolbarIconButton(icon = Icons.Default.Translate, contentDescription = "Translate", tint = KeyboardColors.IconColor)
                ToolbarIconButton(icon = Icons.Default.Settings, contentDescription = "Settings", tint = KeyboardColors.IconColor)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(KeyboardColors.BorderColor)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val row1 = listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")
                row1.forEach { char ->
                    val displayChar = if (isShift) char.uppercase() else char
                    KeyboardKey(
                        text = displayChar,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val row2 = listOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
                row2.forEach { char ->
                    val displayChar = if (isShift) char.uppercase() else char
                    KeyboardKey(
                        text = displayChar,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val shiftBgTop = if (isShift) KeyboardColors.ShiftActiveTop else KeyboardColors.SpecialKeyGradientTop
                val shiftBgBottom = if (isShift) KeyboardColors.ShiftActiveBottom else KeyboardColors.SpecialKeyGradientBottom
                val shiftShadow = if (isShift) KeyboardColors.ShiftActiveShadow else KeyboardColors.KeyShadow

                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.3f),
                    bgTopColor = shiftBgTop,
                    bgBottomColor = shiftBgBottom,
                    shadowColor = shiftShadow,
                    onClick = { isShift = !isShift }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Shift",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                val row3 = listOf("z", "x", "c", "v", "b", "n", "m")
                row3.forEach { char ->
                    val displayChar = if (isShift) char.uppercase() else char
                    KeyboardKey(
                        text = displayChar,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }

                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.3f),
                    bgTopColor = KeyboardColors.SpecialKeyGradientTop,
                    bgBottomColor = KeyboardColors.SpecialKeyGradientBottom,
                    shadowColor = KeyboardColors.KeyShadow,
                    onClick = onBackspace
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Backspace,
                        contentDescription = "Backspace",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.5f),
                    bgTopColor = KeyboardColors.SpecialKeyGradientTop,
                    bgBottomColor = KeyboardColors.SpecialKeyGradientBottom,
                    shadowColor = KeyboardColors.KeyShadow,
                    onClick = { /* Handle number keyboard toggle */ }
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
                    onClick = onSpace
                )

                KeyboardKey(
                    text = ".",
                    modifier = Modifier.weight(1.1f),
                    onClick = { onKeyPress(".") }
                )

                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.5f),
                    bgTopColor = KeyboardColors.SpecialKeyGradientTop,
                    bgBottomColor = KeyboardColors.SpecialKeyGradientBottom,
                    shadowColor = KeyboardColors.KeyShadow,
                    onClick = onEnter
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
