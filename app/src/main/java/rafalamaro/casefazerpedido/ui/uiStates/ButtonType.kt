package rafalamaro.casefazerpedido.ui.uiStates

import androidx.compose.ui.graphics.Color

sealed class ButtonType(val color: Color) {
    data object Standard : ButtonType(Color.White)
    data object Primary : ButtonType(Color.Yellow)
}
