package rafalamaro.casefazerpedido

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rafalamaro.casefazerpedido.model.ProductModel
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
fun ProductComponent(productModel: ProductModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 20.dp)
    ) {
        ProductName(productModel.name)
        ProductQuantity(productModel.quantity)
        ProductValue(productModel.value)
        ProductDescription(productModel.description)
    }
}

@Composable
private fun ProductName(name: String) {
    Text(
        text = name,
        style = Typography.titleMedium
    )
}

@Composable
private fun ProductQuantity(quantity: Int) {
    Row {
        Text(
            text = stringResource(R.string.product_quantity_ab),
            style = Typography.bodyMedium
        )
        Text(
            text = quantity.toString(),
            style = Typography.bodyMedium
        )
    }
}

@Composable
private fun ProductValue(value: Double) {
    Row {
        Text(
            text = stringResource(R.string.money_symbol, value),
            style = Typography.bodyMedium
        )
    }
}

@Composable
private fun ProductDescription(description: String) {
    Text(
        text = description,
        style = Typography.bodyMedium.copy(fontStyle = FontStyle.Italic)
    )
}


@Composable
@Preview
private fun ProductComponentPreview() {
    ProductComponent(
        ProductModel(
            name = "Coca-Cola",
            quantity = 10,
            value = 10.0,
            description = "Pack lata 12x350ml"
        )
    )
}