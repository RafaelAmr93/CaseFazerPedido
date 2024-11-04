package rafalamaro.casefazerpedido.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderDetailedUseCase
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderHistoryListUseCase

class OrderHistoryListViewModel(
    private val getOrdersHistoryListUseCase: IGetOrderHistoryListUseCase,
    private val getOrderDetailedUseCase: IGetOrderDetailedUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _orderHistoryList: MutableStateFlow<List<BaseOrderHistoryModel>> =
        MutableStateFlow(listOf())
    internal val orderHistoryList = _orderHistoryList.asStateFlow()

    private val _orderDetailed: MutableStateFlow<OrderModel?> =
        MutableStateFlow(null)
    internal val orderDetailed = _orderDetailed.asStateFlow()

    internal fun getOrdersHistoryList() {
        viewModelScope.launch(ioDispatcher) {
            _orderHistoryList.value = getOrdersHistoryListUseCase.getOrderHistoryList()

        }
    }

    internal fun getOrderDetails(orderNumber: Int) {
        viewModelScope.launch(ioDispatcher) {
            _orderDetailed.value = getOrderDetailedUseCase.getOrderDetailed(orderNumber)
        }
    }
}
