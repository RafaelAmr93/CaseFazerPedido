package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.ProductList
import rafalamaro.casefazerpedido.ui.components.productList
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
fun OrderHistoryDetailsScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CurrentOrderNumber(1, "Rafael")
        ProductListTitle()
        ProductList(productList)
    }
}

@Composable
fun CurrentOrderNumber(
    orderNumber: Int,
    clientName: String
) {
    Text(
        text = stringResource(R.string.current_order_number, orderNumber),
        style = Typography.titleLarge
    )
    Text(
        text = clientName,
        style = Typography.bodyMedium
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
@Preview
private fun OrderHistoryDetailsScreenPreview() {
    OrderHistoryDetailsScreen()
}