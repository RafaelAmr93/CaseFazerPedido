package rafalamaro.casefazerpedido.ui.uiStates

sealed class MainScreenUiState {
    data object Loading : MainScreenUiState()
    data class Loaded(val ordersCount: Int?, val totalSales: Double?) : MainScreenUiState()
}
