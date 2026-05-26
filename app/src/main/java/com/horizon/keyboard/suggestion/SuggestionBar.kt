package com.horizon.keyboard.suggestion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.keyboard.keyboard.model.KeyboardColors

@Composable
internal fun SuggestionBar(
    suggestions: List<Suggestion>,
    onSuggestionTap: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val showPlaceholder = suggestions.isEmpty()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(KeyboardColors.SpecialKeyBg),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showPlaceholder) {
            Text(
                text = "",
                color = KeyboardColors.IconColor,
                fontSize = 13.sp
            )
        } else {
            suggestions.forEach { suggestion ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(KeyboardColors.KeyBg)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onSuggestionTap(suggestion.word) }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = suggestion.displayText,
                        color = KeyboardColors.TextColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
