package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.ProductList
import rafalamaro.casefazerpedido.ui.theme.Typography
import rafalamaro.casefazerpedido.viewmodels.OrderHistoryListViewModel

@Composable
fun OrderDetailedScreen(orderNumber: Int) {
    val viewModel: OrderHistoryListViewModel = koinViewModel()
    val orderModel = viewModel.orderDetailed.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getOrderDetails(orderNumber)
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CurrentOrderNumber(
            orderModel.value?.id ?: 0,
            orderModel.value?.clientName ?: ""
        )
        ProductList(orderModel.value?.productsList?.productsList ?: emptyList())
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
@Preview
private fun OrderHistoryDetailsScreenPreview() {
    OrderDetailedScreen(0)
}