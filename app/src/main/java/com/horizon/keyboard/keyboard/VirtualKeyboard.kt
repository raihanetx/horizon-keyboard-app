package com.horizon.keyboard.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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

/**
 * Virtual keyboard composable with QWERTY layout.
 *
 * @param modifier Modifier for the root container.
 * @param darkTheme Whether to use dark theme colors.
 * @param onKeyPress Callback when an alphanumeric key is pressed.
 * @param onBackspace Callback when backspace is pressed.
 * @param onSpace Callback when spacebar is pressed.
 * @param onEnter Callback when enter/return is pressed.
 */
@Composable
fun VirtualKeyboard(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    onKeyPress: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onEnter: () -> Unit
) {
    var isShift by remember { mutableStateOf(false) }

    val panelBg = if (darkTheme) KeyboardColors.PanelDark else KeyboardColors.PanelLight
    val borderColor = if (darkTheme) KeyboardColors.BorderDark else KeyboardColors.BorderLight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(panelBg)
            .padding(horizontal = 6.dp)
            .padding(top = 6.dp, bottom = 32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // region Toolbar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val iconColor = if (darkTheme) KeyboardColors.IconMutedDark else KeyboardColors.IconMutedLight
                ToolbarIconButton(icon = Icons.Default.Keyboard, contentDescription = "Keyboard Layout", tint = iconColor)
                ToolbarIconButton(icon = Icons.Default.EmojiEmotions, contentDescription = "Emojis", tint = iconColor)
                ToolbarIconButton(icon = Icons.Default.Mic, contentDescription = "Voice Typing", tint = iconColor)
                ToolbarIconButton(icon = Icons.Default.ContentPaste, contentDescription = "Clipboard", tint = iconColor)
                ToolbarIconButton(icon = Icons.Default.Translate, contentDescription = "Translate", tint = iconColor)
                ToolbarIconButton(icon = Icons.Default.Settings, contentDescription = "Settings", tint = iconColor)
            }
            // endregion

            // region Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(borderColor)
            )
            // endregion

            // region Row 1: QWERTY
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
                        darkTheme = darkTheme,
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }
            }
            // endregion

            // region Row 2: ASDF
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
                        darkTheme = darkTheme,
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }
            }
            // endregion

            // region Row 3: Shift + ZXCVB + Backspace
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Shift Key
                val shiftBg = if (isShift) {
                    if (darkTheme) KeyboardColors.BlueActiveDark else KeyboardColors.BlueActiveLight
                } else {
                    if (darkTheme) KeyboardColors.SpecialKeyDark else KeyboardColors.SpecialKeyLight
                }
                val shiftShadow = if (isShift) {
                    KeyboardColors.BlueActiveShadow
                } else {
                    if (darkTheme) KeyboardColors.KeyShadowDark else KeyboardColors.KeyShadowLight
                }
                val shiftIconColor = if (isShift) {
                    Color.White
                } else {
                    if (darkTheme) Color.White else Color.Black
                }

                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.3f),
                    bgColor = shiftBg,
                    shadowColor = shiftShadow,
                    onClick = { isShift = !isShift }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Shift",
                        tint = shiftIconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                val row3 = listOf("z", "x", "c", "v", "b", "n", "m")
                row3.forEach { char ->
                    val displayChar = if (isShift) char.uppercase() else char
                    KeyboardKey(
                        text = displayChar,
                        modifier = Modifier.weight(1f),
                        darkTheme = darkTheme,
                        onClick = {
                            onKeyPress(displayChar)
                            if (isShift) isShift = false
                        }
                    )
                }

                // Backspace Key
                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.3f),
                    bgColor = if (darkTheme) KeyboardColors.SpecialKeyDark else KeyboardColors.SpecialKeyLight,
                    shadowColor = if (darkTheme) KeyboardColors.KeyShadowDark else KeyboardColors.KeyShadowLight,
                    onClick = onBackspace
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Backspace,
                        contentDescription = "Backspace",
                        tint = if (darkTheme) Color.White else Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            // endregion

            // region Row 4: 123 + Comma + Space + Period + Enter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // "123" Key
                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.5f),
                    bgColor = if (darkTheme) KeyboardColors.SpecialKeyDark else KeyboardColors.SpecialKeyLight,
                    shadowColor = if (darkTheme) KeyboardColors.KeyShadowDark else KeyboardColors.KeyShadowLight,
                    onClick = { /* Handle number keyboard toggle */ }
                ) {
                    Text(
                        text = "123",
                        color = if (darkTheme) Color.White else Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Comma Key
                KeyboardKey(
                    text = ",",
                    modifier = Modifier.weight(1.1f),
                    darkTheme = darkTheme,
                    onClick = { onKeyPress(",") }
                )

                // Spacebar Key
                KeyboardKey(
                    text = "space",
                    modifier = Modifier.weight(4.5f),
                    darkTheme = darkTheme,
                    onClick = onSpace
                )

                // Period Key
                KeyboardKey(
                    text = ".",
                    modifier = Modifier.weight(1.1f),
                    darkTheme = darkTheme,
                    onClick = { onKeyPress(".") }
                )

                // Enter Key
                KeyboardSpecialKey(
                    modifier = Modifier.weight(1.5f),
                    bgColor = if (darkTheme) KeyboardColors.SpecialKeyDark else KeyboardColors.SpecialKeyLight,
                    shadowColor = if (darkTheme) KeyboardColors.KeyShadowDark else KeyboardColors.KeyShadowLight,
                    onClick = onEnter
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardReturn,
                        contentDescription = "Enter",
                        tint = if (darkTheme) Color.White else Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            // endregion
        }
    }
}

// region Previews
@Preview(showBackground = true)
@Composable
private fun PreviewVirtualKeyboardLight() {
    VirtualKeyboard(
        darkTheme = false,
        onKeyPress = {},
        onBackspace = {},
        onSpace = {},
        onEnter = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewVirtualKeyboardDark() {
    VirtualKeyboard(
        darkTheme = true,
        onKeyPress = {},
        onBackspace = {},
        onSpace = {},
        onEnter = {}
    )
}
// endregion
