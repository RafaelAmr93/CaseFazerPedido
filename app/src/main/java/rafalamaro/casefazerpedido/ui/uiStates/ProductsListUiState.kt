package rafalamaro.casefazerpedido.ui.uiStates

import rafalamaro.casefazerpedido.data.model.ProductModel

sealed class ProductsListUiState {
    data class Loaded(val list: List<ProductModel>) : ProductsListUiState()
    data object Empty : ProductsListUiState()
}
