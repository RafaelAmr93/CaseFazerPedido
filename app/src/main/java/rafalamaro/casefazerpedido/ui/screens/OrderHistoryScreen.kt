package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rafalamaro.casefazerpedido.ui.components.ProductList
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.productList
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
fun OrderHistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement
                .spacedBy(space = 16.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PreviousOrderButton(true)
            OrderHistoryTitle()
            PreviousOrderButton()
        }
        CurrentOrderNumber(1)
        ProductListTitle()
        // temp mock
        ProductList(productList)
    }
}

@Composable
fun CurrentOrderNumber(orderNumber: Int) {
    Text(
        text = stringResource(R.string.current_order_number, orderNumber),
        style = Typography.titleLarge
    )
}

@Composable
private fun ProductListTitle() {
    Text(
        stringResource(R.string.product_list_title),
        style = Typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun OrderHistoryTitle() {
    Text(
        stringResource(R.string.order_history_title),
        style = Typography.titleLarge
    )
}

@Composable
private fun PreviousOrderButton(isBackButton: Boolean = false) {
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

@Composable
@Preview
private fun OrderHistoryScreenPreview() {
    OrderHistoryScreen()
}
