package rafalamaro.casefazerpedido

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackToTopButton(goToTop: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .clickable { goToTop() }
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Go to top button",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GoToTopButtonPreview() {
    Box(modifier = Modifier
        .padding(all = 20.dp)
        .background(color = Color.White)) {
        BackToTopButton {}
    }
}