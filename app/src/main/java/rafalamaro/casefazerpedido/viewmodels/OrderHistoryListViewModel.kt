package rafalamaro.casefazerpedido.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.ProductModel

class OrderHistoryListViewModel(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource,
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
            _orderHistoryList.value = orderHistoryLocalDatasource.getOrderHistoryList()

        }
    }

    internal fun getOrderDetails(orderNumber: Int) {
        viewModelScope.launch(ioDispatcher) {
            _orderDetailed.value = orderHistoryLocalDatasource.getOrderDetailed(orderNumber)
        }
    }
}
