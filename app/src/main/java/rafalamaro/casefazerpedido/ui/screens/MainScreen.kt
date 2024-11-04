package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.CommonButton
import rafalamaro.casefazerpedido.ui.components.SnackBarComponent
import rafalamaro.casefazerpedido.ui.uiStates.SnackBarType
import rafalamaro.casefazerpedido.ui.theme.Typography
import rafalamaro.casefazerpedido.ui.uiStates.ButtonType
import rafalamaro.casefazerpedido.ui.uiStates.MainScreenUiState
import rafalamaro.casefazerpedido.ui.viewmodels.MainScreenViewModel

@Composable
internal fun MainScreen(
    onNavigateToOrderHistory: () -> Unit,
    onNavigateToPlaceOrder: () -> Unit,
    orderPlaced: Boolean
) {
    val viewModel: MainScreenViewModel = koinViewModel()
    val uiState by viewModel.mainScreenUiState.collectAsState()
    var showSnackBar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (orderPlaced) {
            showSnackBar = true
            delay(3000)
            showSnackBar = false
        }
        viewModel.getTotalSales()
        viewModel.getOrdersCount()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Header()
        Body(uiState)
        Footer(
            onNavigateToOrderHistory = onNavigateToOrderHistory,
            onNavigateToPlaceOrder = onNavigateToPlaceOrder
        )
        Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
            if (showSnackBar) {
              SnackBarComponent(SnackBarType.OrderPlaced)
            }
        }
    }
}

@Composable
private fun BoxScope.Header() {
    Column(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(
                top = 32.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            )
    ) {
        Text(
            text = stringResource(R.string.main_screen_title),
            style = Typography.titleMedium,
        )
        HorizontalDivider(
            thickness = 2.dp
        )
        Image(
            painter = painterResource(R.drawable.comprar_online),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
    }
}

@Composable
private fun BoxScope.Body(uiState: MainScreenUiState) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        when (uiState) {
            is MainScreenUiState.Loading -> {
                TotalOrders(null)
                TotalSales(null)
            }
            is MainScreenUiState.Loaded -> {
                TotalOrders(uiState.ordersCount)
                TotalSales(uiState.totalSales)
            }
        }
    }
}

@Composable
private fun BoxScope.Footer(
    onNavigateToOrderHistory: () -> Unit,
    onNavigateToPlaceOrder: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 40.dp
            )
    ) {
        CommonButton(
            text = stringResource(R.string.order_history_button_label),
            onClick = {
                onNavigateToOrderHistory()
            },
            type = ButtonType.Standard
        )
        CommonButton(
            text = stringResource(R.string.place_order_button_label),
            onClick = {
                onNavigateToPlaceOrder()
            },
            type = ButtonType.Primary
        )
    }
}

@Composable
private fun TotalOrders(ordersCount: Int?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.total_orders),
            style = Typography.titleMedium
        )
        if (ordersCount == null) {
            LoadingAnimation()
        } else {
            Text(
                text = stringResource(R.string.order_symbol, ordersCount),
                fontSize = 50.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Divider()
    }
}

@Composable
private fun TotalSales(value: Double?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.total_sales),
            style = Typography.titleMedium
        )
        if (value == null) {
            LoadingAnimation()
        } else {
            Text(
                text = stringResource(R.string.money_symbol, value),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )
        }
        Divider()
    }
}

@Composable
private fun Divider() {
    HorizontalDivider(
        thickness = 2.dp
    )
}

@Composable
private fun LoadingAnimation() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp),
        color = Color.Black,
        trackColor = Color.LightGray
    )
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen({},{}, false)
}