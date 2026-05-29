package com.horizon.keyboard.keyboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.horizon.keyboard.keyboard.model.LocalKeyboardColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun KeyboardSpecialKey(
    modifier: Modifier = Modifier,
    bgColor: Color,
    borderColor: Color = LocalKeyboardColors.current.borderColor,
    onClick: () -> Unit,
    repeatOnHold: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = LocalKeyboardColors.current
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = 50),
        label = "specialKeyScale"
    )

    val keyShape = RoundedCornerShape(6.dp)

    val baseModifier = modifier
        .graphicsLayer(scaleX = scale, scaleY = scale)
        .shadow(2.dp, keyShape, clip = false)
        .clip(keyShape)
        .background(bgColor)
        .border(1.dp, borderColor, keyShape)
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

    val gestureModifier = if (repeatOnHold) {
        baseModifier.pointerInput(onClick) {
            awaitEachGesture {
                awaitFirstDown(requireUnconsumed = false)
                isPressed = true
                val repeatJob = launch {
                    delay(500L)
                    while (true) {
                        onClick()
                        delay(80L)
                    }
                }
                do {
                    val event = awaitPointerEvent()
                } while (event.changes.any { it.pressed })
                repeatJob.cancel()
                isPressed = false
            }
        }
    } else {
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
        baseModifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    }

    Box(
        modifier = gestureModifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
