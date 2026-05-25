package com.horizon.keyboard.keyboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Special function key (Shift, Backspace, Enter, 123) with custom content.
 *
 * @param modifier Modifier for the key container.
 * @param bgColor Background color for the key face.
 * @param shadowColor Shadow/border color for the key base.
 * @param onClick Callback when key is pressed.
 * @param content Composable content to display inside the key.
 */
@Composable
internal fun KeyboardSpecialKey(
    modifier: Modifier = Modifier,
    bgColor: Color,
    shadowColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(shadowColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = Color.Gray),
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.94f)
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp, bottomStart = 5.dp, bottomEnd = 5.dp))
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
