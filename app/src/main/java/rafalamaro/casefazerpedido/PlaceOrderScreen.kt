package rafalamaro.casefazerpedido

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PlaceOrderScreen(
    viewModel: PlaceOrderViewModel = koinViewModel()
) {
    Box() {
        Text(text = viewModel.testString)
        ClientName()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ClientName() {
    val state = rememberTextFieldState("")
    BasicTextField(
        state = state
    )
}


@Composable
@Preview
private fun PlaceOrderScreenPreview() {
    PlaceOrderScreen()
}