package com.horizon.keyboard.keyboard.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.repeatOnLongPress(
    initialDelay: Long = 500L,
    repeatDelay: Long = 80L,
    onRepeat: () -> Unit
): Modifier = this.pointerInput(onRepeat) {
    coroutineScope {
        detectTapGestures(
            onPress = {
                launch {
                    delay(initialDelay)
                    while (true) {
                        onRepeat()
                        delay(repeatDelay)
                    }
                }
                tryAwaitRelease()
            }
        )
    }
}
