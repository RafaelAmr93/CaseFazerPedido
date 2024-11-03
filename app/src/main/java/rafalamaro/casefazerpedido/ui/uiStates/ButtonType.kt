package rafalamaro.casefazerpedido.ui.uiStates

import androidx.compose.ui.graphics.Color

sealed class ButtonType(val backGroundColor: Color, val textColor: Color) {
    data object Standard : ButtonType(
        backGroundColor = Color(0xFFFAFAFA),
        textColor = Color.Black
    )
    data object Primary : ButtonType(
        backGroundColor = Color(0xFF1976D2),
        textColor = Color.White
    )
}
