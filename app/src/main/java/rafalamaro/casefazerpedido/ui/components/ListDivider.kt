package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun ListDivider() {
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.LightGray,
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 5.dp)
    )
}
