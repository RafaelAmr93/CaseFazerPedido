package rafalamaro.casefazerpedido.ui.viewmodels

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.data.model.ProductModel
import rafalamaro.casefazerpedido.data.model.ProductsListModel
import rafalamaro.casefazerpedido.domain.contracts.IInsertOrderUseCase
import rafalamaro.casefazerpedido.ui.uiStates.ProductsListUiState
import rafalamaro.casefazerpedido.ui.uiStates.SnackBarType

class PlaceOrderViewModel(
    private val insertOrderUseCase: IInsertOrderUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    internal val clientName = TextFieldState()
    internal val productName = TextFieldState()
    internal val productQuantity = TextFieldState()
    internal val productValue = TextFieldState()
    internal val productDescription = TextFieldState()

    private var productBeingAdded: ProductModel? = null

    private val _productsList: MutableStateFlow<ProductsListUiState> = MutableStateFlow(
        ProductsListUiState.Empty
    )
    val productsList = _productsList.asStateFlow()

    private val _snackBarState: MutableStateFlow<SnackBarType?> = MutableStateFlow(null)
    val snackBarState = _snackBarState.asStateFlow()

    // Place order
    internal fun placeOrder() {
        viewModelScope.launch(ioDispatcher) {
            val order = OrderModel(
                clientName = clientName.text.toString(),
                productsList = ProductsListModel(
                    productsList = getLatestList()
                ),
                orderTotalValue = getOrderTotalValue()
            )
            insertOrderUseCase.insertOrder(order)
        }
    }

    // SnackBar manager
    internal fun updateSnackBarState(type: SnackBarType?) {
        _snackBarState.value = type
    }

    // Product list management
    internal fun addProduct() {
        productBeingAdded = ProductModel(
            name = productName.text.toString(),
            quantity = productQuantity.text.toString().toInt(),
            value = productValue.text.toString().toDouble(),
            description = productDescription.text.toString()
        )

        productBeingAdded?.let {
            _productsList.value = ProductsListUiState.Loaded(
                list = getLatestList().toMutableList().apply {
                    add(it)
                }
            )
        }
    }

    internal fun isProductsListEmpty(): Boolean {
        return (_productsList.value is ProductsListUiState.Empty)
    }

    internal fun getLatestList(): List<ProductModel> {
        return if (_productsList.value is ProductsListUiState.Loaded) {
            (_productsList.value as ProductsListUiState.Loaded).list
        } else {
            emptyList()
        }
    }

    internal fun getOrderTotalValue(): Double {
        return getLatestList().sumOf { it.value * it.quantity }
    }

    // Text Field methods
    internal fun clearAllText() {
        productName.clearText()
        productQuantity.clearText()
        productValue.clearText()
        productDescription.clearText()
    }

    internal fun checkForEmptyField(): Boolean {
        return clientName.text.isNotEmpty() &&
            productName.text.isNotEmpty() &&
            productQuantity.text.isNotEmpty() &&
            productValue.text.isNotEmpty() &&
            productDescription.text.isNotEmpty()
    }
}