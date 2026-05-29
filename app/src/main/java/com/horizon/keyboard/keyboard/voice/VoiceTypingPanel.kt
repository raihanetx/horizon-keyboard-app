package com.horizon.keyboard.keyboard.voice

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.model.LocalKeyboardColors

data class VoiceLanguage(
    val displayName: String,
    val localeCode: String
)

val voiceLanguages = listOf(
    VoiceLanguage("English", "en-US"),
    VoiceLanguage("Español", "es-ES"),
    VoiceLanguage("Français", "fr-FR"),
    VoiceLanguage("Deutsch", "de-DE"),
    VoiceLanguage("العربية", "ar-SA"),
    VoiceLanguage("বাংলা", "bn-BD"),
    VoiceLanguage("हिंदी", "hi-IN"),
    VoiceLanguage("日本語", "ja-JP"),
    VoiceLanguage("한국어", "ko-KR"),
    VoiceLanguage("中文", "zh-CN"),
    VoiceLanguage("Português", "pt-BR"),
    VoiceLanguage("Русский", "ru-RU")
)

@Composable
fun VoiceTypingPanel(
    modifier: Modifier = Modifier,
    isListening: Boolean,
    isProcessing: Boolean = false,
    isError: Boolean = false,
    errorMessage: String = "",
    recognizedText: String = "",
    language: VoiceLanguage,
    onLanguageToggle: () -> Unit,
    onStartListening: () -> Unit,
    onStopListening: () -> Unit,
    onClose: () -> Unit
) {
    val colors = LocalKeyboardColors.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colors.specialKeyBg)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.keyBg)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onLanguageToggle
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = language.displayName,
                        color = colors.textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "▼",
                        color = colors.iconColor,
                        fontSize = 10.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.keyBg)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClose
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close voice typing",
                    tint = colors.iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val micAlpha = if (isListening) {
            val infiniteTransition = rememberInfiniteTransition(label = "mic_pulse")
            val alpha by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0.4f,
                animationSpec = infiniteRepeatable(
                    animation = tween(800),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "mic_alpha"
            )
            alpha
        } else {
            1f
        }

        Box(
            modifier = Modifier
                .size(72.dp)
                .graphicsLayer(alpha = micAlpha)
                .clip(CircleShape)
                .background(
                    when {
                        isListening -> Color(0xFFEF4444)
                        isError -> Color(0xFF6B7280)
                        else -> colors.shiftActiveBg
                    }
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        if (isListening) {
                            onStopListening()
                        } else {
                            onStartListening()
                        }
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isListening) Icons.Default.Mic else Icons.Default.MicOff,
                contentDescription = if (isListening) "Stop listening" else "Start listening",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = when {
                isError -> errorMessage.ifEmpty { "Error occurred" }
                isProcessing -> "Processing..."
                isListening -> "Listening..."
                else -> "Tap microphone to speak"
            },
            color = if (isError) Color(0xFFEF4444) else colors.textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        if (recognizedText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.keyBg)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    text = recognizedText,
                    color = colors.textColor,
                    fontSize = 16.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
