package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.byValue

internal fun transformationTextOnly(
    callback: () -> Unit
) = InputTransformation.byValue { current, proposed ->
    if (proposed.all { it.isLetter() || it.isWhitespace() }) {
        proposed
    } else {
        callback()
        current
    }
}

internal fun transformationDigitsOnly(
    callback: () -> Unit
) = InputTransformation.byValue { current, proposed ->
    if (proposed.all { it.isDigit() || it == ',' || it == '.'}) {
        proposed
    } else {
        callback()
        current
    }
}
