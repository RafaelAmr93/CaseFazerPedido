package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.ui.components.BackToTopButton
import rafalamaro.casefazerpedido.ui.components.ListDivider
import rafalamaro.casefazerpedido.ui.theme.Typography
import rafalamaro.casefazerpedido.viewmodels.OrderHistoryListViewModel

@Composable
fun OrdersHistoryScreen(onNavigateToDetailedOrder: (Int) -> Unit) {
    val viewModel: OrderHistoryListViewModel = koinViewModel()
    val orderHistoryList = viewModel.orderHistoryList.collectAsState()
    val listState = rememberLazyListState()
    val showGotoTop by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getOrdersHistoryList()
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrderHistoryTitle()
            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(bottom = 10.dp)
            ) {
                if (orderHistoryList.value.isEmpty()) {
                    item {
                        NoOrdersToShow()
                    }
                } else {
                    itemsIndexed(orderHistoryList.value) { index, order ->
                        OrderComponent(
                            order,
                            onNavigateToDetailedOrder
                        )
                        if (index < orderHistoryList.value.lastIndex) ListDivider()
                    }
                }
            }
        }
        AnimatedVisibility(visible = showGotoTop, enter = fadeIn(), exit = fadeOut()) {
            BackToTopButton {
                scope.launch {
                    listState.scrollToItem(0)
                }
            }
        }
    }
}

@Composable
private fun OrderComponent(
    order: BaseOrderHistoryModel,
    onNavigateToDetails: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = order.clientName,
            style = Typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.order_symbol, order.orderNumber),
            style = Typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.see_details).uppercase(),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(
                onClick = {
                    onNavigateToDetails(order.orderNumber)
                }
            )
        )
    }
}

@Composable
private fun OrderHistoryTitle() {
    Text(
        stringResource(R.string.order_history_title),
        style = Typography.titleLarge,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@Composable
private fun NoOrdersToShow() {
    Text(
        stringResource(R.string.no_orders_to_show),
        style = Typography.titleLarge,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@Composable
@Preview
private fun OrderHistoryScreenPreview() {
    OrdersHistoryScreen({})
}
