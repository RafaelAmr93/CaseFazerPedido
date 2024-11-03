package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rafalamaro.casefazerpedido.ui.theme.Typography
import rafalamaro.casefazerpedido.ui.uiStates.ButtonType

@Composable
internal fun CommonButton(
    text: String,
    onClick: () -> Unit,
    type: ButtonType
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(12.dp))
            .background(type.color)
            .clickable(
                onClick = { onClick() },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Text(
            text = text.uppercase(),
            style = Typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Composable
@Preview
private fun CommonButtonPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(all = 20.dp)
    ) {
        CommonButton(text = "Place Order", onClick = {}, type = ButtonType.Primary)
    }
}