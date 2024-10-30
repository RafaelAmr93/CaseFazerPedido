package rafalamaro.casefazerpedido

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel

class PlaceOrderViewModel() : ViewModel() {

    internal val clientName = TextFieldState()
    internal val productName = TextFieldState()
    internal val productQuantity = TextFieldState()
    internal val productValue = TextFieldState()
    internal val productDescription = TextFieldState()
}