package rafalamaro.casefazerpedido.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.model.ProductModel
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
internal fun ProductList(productsList: List<ProductModel>) {
    val listState = rememberLazyListState()
    val showGotoTop by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val scope = rememberCoroutineScope()

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                ProductListTitle()
            }
            itemsIndexed(productsList) { index, product ->
                ProductComponent(product)
                if (index < productsList.lastIndex) ListDivider()
            }
            item {
                HorizontalDivider(color = Color.White,thickness = 10.dp)
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
private fun ProductListTitle() {
    Text(
        stringResource(R.string.product_list_title),
        style = Typography.titleMedium
    )
}

@Composable
@Preview
private fun ProductListPreview() {
    ProductList(productList)
}

internal val productList = List(size = 15) {
    ProductModel(
        name = "Coca-Cola",
        quantity = 10,
        value = 10.0,
        description = "Pack lata 12x350ml"
    )
}