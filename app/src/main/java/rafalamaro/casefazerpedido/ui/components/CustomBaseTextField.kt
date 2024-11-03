package rafalamaro.casefazerpedido.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import rafalamaro.casefazerpedido.R
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
internal fun CustomBaseTextField(
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
