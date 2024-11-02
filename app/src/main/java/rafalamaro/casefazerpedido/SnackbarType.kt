package rafalamaro.casefazerpedido

import androidx.compose.ui.graphics.Color

sealed class SnackBarType(val message: Int, val labelColor: Color) {
    data object ProductAdded : SnackBarType(R.string.product_added, Color.Green)
    data object MissingFields : SnackBarType(R.string.missing_fields, Color.Red)
    data object EmptyProductsList : SnackBarType(R.string.empty_products_list, Color.Blue)
    data object OrderPlaced : SnackBarType(R.string.order_placed_successfully, Color.Green)
}
