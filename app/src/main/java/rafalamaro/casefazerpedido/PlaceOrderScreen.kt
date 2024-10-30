package rafalamaro.casefazerpedido

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.startKoin
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
internal fun PlaceOrderScreen(
    viewModel: PlaceOrderViewModel = koinViewModel()
) {
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
            onClick = {}
        )
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
        transformation = transformationDigitsOnly { forbiddenCharactersTyped = true },
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

@Composable
@Preview
private fun PlaceOrderScreenPreview() {
    startKoin {
        modules(appModule)
    }
    PlaceOrderScreen()
}