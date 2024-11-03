package rafalamaro.casefazerpedido.ui.uiStates

import rafalamaro.casefazerpedido.model.ProductModel

sealed class ProductsListUiState {
    data object Loading : ProductsListUiState()
    data class Loaded(val list: List<ProductModel>) : ProductsListUiState()
    data object Empty : ProductsListUiState()
    data object Error : ProductsListUiState()
}
