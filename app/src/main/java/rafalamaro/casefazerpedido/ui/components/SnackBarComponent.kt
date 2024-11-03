package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rafalamaro.casefazerpedido.ui.uiStates.SnackBarType
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
fun SnackBarComponent(type: SnackBarType) {
    Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.LightGray)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                VerticalDivider(
                    thickness = 10.dp,
                    color = type.labelColor,
                    modifier = Modifier.height(60.dp)
                )
                Text(
                    text = stringResource(type.message),
                    style = Typography.bodyLarge
                )
            }
        }
    }
}

@Composable
@Preview
private fun SnackBarComponentPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SnackBarComponent(SnackBarType.ProductAdded)
        SnackBarComponent(SnackBarType.MissingFields)
    }
}
