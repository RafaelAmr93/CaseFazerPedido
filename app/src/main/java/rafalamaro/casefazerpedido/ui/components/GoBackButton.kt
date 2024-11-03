package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
private fun GoBackButton(isBackButton: Boolean = false) {
    Box(modifier = Modifier.background(Color.Transparent)) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .clickable {  }
                .padding(6.dp)
        ) {
            Icon(
                imageVector = if (isBackButton) {
                    Icons.AutoMirrored.Default.ArrowBack
                } else {
                    Icons.AutoMirrored.Default.ArrowForward
                },
                contentDescription = "Go to top button",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}