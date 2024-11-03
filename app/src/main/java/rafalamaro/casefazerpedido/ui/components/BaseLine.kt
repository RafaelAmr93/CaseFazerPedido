package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
internal fun BaseLine(
    innerTextField: @Composable () -> Unit,
    onClearText: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, size.height),
                end = Offset(x = size.width, size.height),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Butt
            )
        }
) {
    Icon(
        Icons.Rounded.Clear,
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .clickable(
                onClick = { onClearText() },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            ),
        tint = Color.LightGray
    )
    innerTextField()
}
