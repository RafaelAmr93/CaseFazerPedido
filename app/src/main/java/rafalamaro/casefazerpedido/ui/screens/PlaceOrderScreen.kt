package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.CommonButton
import rafalamaro.casefazerpedido.ui.components.CustomBaseTextField
import rafalamaro.casefazerpedido.ui.components.ProductList
import rafalamaro.casefazerpedido.ui.components.SnackBarComponent
import rafalamaro.casefazerpedido.ui.components.transformationDigitsOnly
import rafalamaro.casefazerpedido.ui.components.transformationTextOnly
import rafalamaro.casefazerpedido.ui.theme.Typography
import rafalamaro.casefazerpedido.ui.uiStates.ButtonType
import rafalamaro.casefazerpedido.ui.uiStates.ProductsListUiState
import rafalamaro.casefazerpedido.ui.uiStates.SnackBarType
import rafalamaro.casefazerpedido.viewmodels.PlaceOrderViewModel

@Composable
internal fun PlaceOrderScreen(
    onOrderPlaced: (Boolean) -> Unit
) {
    val viewModel: PlaceOrderViewModel = koinViewModel()
    val uiState by viewModel.productsList.collectAsState()
    val snackBarState by viewModel.snackBarState.collectAsState()
    var showSnackBar by remember { mutableStateOf(false) }

    LaunchedEffect(snackBarState) {
        showSnackBar = true
        delay(3000)
        showSnackBar = !showSnackBar
        viewModel.updateSnackBarState(null)
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(
                        vertical = 20.dp,
                        horizontal = 20.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ClientName(viewModel.clientName)
                ProductName(viewModel.productName)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProductQuantity(viewModel.productQuantity)
                    ProductValue(viewModel.productValue)
                }
                ProductDescription(viewModel.productDescription)
                CommonButton(
                    text = stringResource(R.string.place_product_button_label),
                    onClick = {
                        if (viewModel.checkForEmptyField()) {
                            viewModel.addProduct()
                            viewModel.updateSnackBarState(SnackBarType.ProductAdded)
                        } else {
                            viewModel.updateSnackBarState(SnackBarType.MissingFields)
                        }
                        viewModel.clearAllText()
                    },
                    type = ButtonType.Standard
                )
                CommonButton(
                    text = stringResource(R.string.place_order_button_label),
                    onClick = {
                        if (viewModel.isProductsListEmpty()) {
                            viewModel.updateSnackBarState(SnackBarType.EmptyProductsList)
                        } else {
                            viewModel.placeOrder()
                            onOrderPlaced(true)
                        }
                    },
                    type = ButtonType.Primary
                )

                Text(
                    text = stringResource(R.string.itens_quantity, viewModel.getLatestList().size),
                    style = Typography.bodyLarge
                )
                Text(
                    text = stringResource(
                        R.string.total_itens_value,
                        viewModel.getOrderTotalValue()
                    ),
                    style = Typography.bodyLarge
                )
            }
            when (uiState) {
                is ProductsListUiState.Loaded -> {
                    ProductList(viewModel.getLatestList())
                }

                else -> Unit
            }
        }

        Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
            if (showSnackBar) {
                snackBarState?.let { SnackBarComponent(it) }
            }
        }
    }
}

@Composable
private fun ClientName(fieldState: TextFieldState) {
    var forbiddenCharactersTyped by remember { mutableStateOf(false) }
    CustomBaseTextField(
        fieldName = stringResource(R.string.client_name),
        fieldState = fieldState,
        transformation = transformationTextOnly { forbiddenCharactersTyped = true },
        forbiddenCharactersTyped = forbiddenCharactersTyped
    )
}

@Composable
private fun ProductName(fieldState: TextFieldState) {
    var forbiddenCharactersTyped by remember { mutableStateOf(false) }
    CustomBaseTextField(
        fieldName = stringResource(R.string.product_name),
        fieldState = fieldState,
        transformation = transformationTextOnly { forbiddenCharactersTyped = true },
        forbiddenCharactersTyped = forbiddenCharactersTyped
    )
}

@Composable
private fun RowScope.ProductQuantity(fieldState: TextFieldState) {
    var forbiddenCharactersTyped by remember { mutableStateOf(false) }
    CustomBaseTextField(
        fieldName = stringResource(R.string.product_quantity),
        fieldState = fieldState,
        transformation = transformationDigitsOnly { forbiddenCharactersTyped = true },
        forbiddenCharactersTyped = forbiddenCharactersTyped,
        modifier = Modifier.weight(1f)
    )
}

@Composable
private fun RowScope.ProductValue(fieldState: TextFieldState) {
    var forbiddenCharactersTyped by remember { mutableStateOf(false) }
    CustomBaseTextField(
        fieldName = stringResource(R.string.product_price),
        fieldState = fieldState,
        transformation = transformationDigitsOnly { forbiddenCharactersTyped = true },
        forbiddenCharactersTyped = forbiddenCharactersTyped,
        modifier = Modifier.weight(1f)
    )
}

@Composable
private fun ProductDescription(fieldState: TextFieldState) {
    var forbiddenCharactersTyped by remember { mutableStateOf(false) }
    CustomBaseTextField(
        fieldName = stringResource(R.string.product_description),
        fieldState = fieldState,
        transformation = transformationTextOnly { forbiddenCharactersTyped = true },
        forbiddenCharactersTyped = forbiddenCharactersTyped
    )
}
