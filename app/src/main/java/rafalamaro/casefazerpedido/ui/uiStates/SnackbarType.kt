package rafalamaro.casefazerpedido.ui.uiStates

import androidx.compose.ui.graphics.Color
import rafalamaro.casefazerpedido.R

sealed class SnackBarType(val message: Int, val labelColor: Color) {
    data object ProductAdded : SnackBarType(R.string.product_added, Color(0xFF66BB6A))
    data object MissingFields : SnackBarType(R.string.missing_fields, Color(0xFFF44336))
    data object EmptyProductsList : SnackBarType(R.string.empty_products_list, Color(0xFF2196F3))
    data object OrderPlaced : SnackBarType(R.string.order_placed_successfully, Color(0xFF66BB6A))
}
