package rafalamaro.casefazerpedido.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.byValue
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import rafalamaro.casefazerpedido.viewmodels.PlaceOrderViewModel
import rafalamaro.casefazerpedido.ui.components.ProductList
import rafalamaro.casefazerpedido.ui.uiStates.ProductsListUiState
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.components.SnackBarComponent
import rafalamaro.casefazerpedido.ui.uiStates.SnackBarType
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
internal fun PlaceOrderScreen(
    onOrderPlaced: (Boolean) -> Unit
) {
    val viewModel: PlaceOrderViewModel = koinViewModel()
    val uiState by viewModel.productsList.collectAsState()
    val snackBarState by viewModel.snackBarState.collectAsState()
    var showSnackBar by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(snackBarState) {
        showSnackBar = true
        delay(3000)
        showSnackBar = false
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(
                    vertical = 24.dp,
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
            FooterButtons(
                text = stringResource(R.string.place_product_button_label),
                onClick = {
                    if (viewModel.checkForEmptyField()) {
                        viewModel.addProduct()
                    } else {
                        viewModel.updateSnackBarState(SnackBarType.MissingFields)
                    }

                    viewModel.clearAllText()
                }
            )
            FooterButtons(
                text = stringResource(R.string.place_order_button_label),
                onClick = {
                    if (viewModel.isProductsListEmpty()) {
                        viewModel.updateSnackBarState(SnackBarType.EmptyProductsList)
                    } else {
                        viewModel.placeOrder()
                        onOrderPlaced(true)
                    }
                }
            )

            //ARRUMAR PRA ACEITAR CAMPO VAZIO? VALIDAR
            // precisa do botão pra fazer o pedido
            // validação dos campos
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

@Composable
private fun CustomBaseTextField(
    fieldName: String,
    fieldState: TextFieldState,
    transformation: InputTransformation,
    forbiddenCharactersTyped: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column {
            Text(
                text = fieldName,
                style = Typography.bodyMedium
            )
            BasicTextField(
                state = fieldState,
                decorator = { innerTextField ->
                    BaseLine(
                        innerTextField = innerTextField,
                        onClearText = fieldState::clearText
                    )
                },
                inputTransformation = transformation
            )
            if (forbiddenCharactersTyped) {
                Text(
                    text = stringResource(R.string.characters_forbidden),
                    style = Typography.bodySmall,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
private fun BaseLine(
    innerTextField: @Composable () -> Unit,
    onClearText: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, size.height),
                end = Offset(x = size.width, size.height),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Butt
            )
        }
) {
    Icon(
        Icons.Rounded.Clear,
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .clickable(
                onClick = { onClearText() },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            ),
        tint = Color.LightGray
    )
    innerTextField()
}

private fun transformationTextOnly(
    callback: () -> Unit
) = InputTransformation.byValue { current, proposed ->
    if (proposed.all { it.isLetter() || it.isWhitespace() }) {
        proposed
    } else {
        callback()
        current
    }
}

private fun transformationDigitsOnly(
    callback: () -> Unit
) = InputTransformation.byValue { current, proposed ->
    if (proposed.all { it.isDigit() || it == ',' || it == '.'}) {
        proposed
    } else {
        callback()
        current
    }
}
