package com.horizon.keyboard.keyboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.model.LocalKeyboardColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun KeyboardKey(
    text: String,
    modifier: Modifier = Modifier,
    alternates: List<String> = emptyList(),
    onClick: () -> Unit,
    onLongPress: ((String) -> Unit)? = null
) {
    val colors = LocalKeyboardColors.current
    var showPopup by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = 50),
        label = "keyScale"
    )
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release -> isPressed = false
                is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    val keyShape = RoundedCornerShape(6.dp)

    Box(contentAlignment = Alignment.TopCenter) {
        Box(
            modifier = modifier
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .shadow(2.dp, keyShape, clip = false)
                .clip(keyShape)
                .background(colors.keyBg)
                .border(1.dp, colors.borderColor, keyShape)
                .drawWithContent {
                    drawContent()
                    drawLine(
                        color = colors.keyBorderBottom,
                        start = androidx.compose.ui.geometry.Offset(0f, size.height),
                        end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                .height(44.dp)
                .combinedClickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        showPopup = false
                        onClick()
                    },
                    onLongClick = {
                        if (alternates.isNotEmpty() && onLongPress != null) {
                            showPopup = true
                        }
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = colors.textColor,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

        if (showPopup) {
            LongPressPopup(
                alternates = alternates,
                onSelect = { selected ->
                    showPopup = false
                    onLongPress?.invoke(selected)
                },
                onDismiss = { showPopup = false }
            )
        }
    }
}
