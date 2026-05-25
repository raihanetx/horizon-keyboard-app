package com.horizon.keyboard.keyboard.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Toolbar icon button used in the top utility row.
 *
 * @param icon Material icon to display.
 * @param contentDescription Accessibility description.
 * @param tint Icon tint color.
 * @param modifier Modifier for the button container.
 */
@Composable
internal fun ToolbarIconButton(
    icon: ImageVector,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /* Handle toolbar callback */ },
        modifier = modifier.size(36.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}
