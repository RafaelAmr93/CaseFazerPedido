package rafalamaro.casefazerpedido.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel

class OrderHistoryListViewModel(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _orderHistoryList: MutableStateFlow<List<BaseOrderHistoryModel>> =
        MutableStateFlow(listOf())
    internal val orderHistoryList = _orderHistoryList.asStateFlow()

    internal fun getOrdersHistoryList() {
        viewModelScope.launch(ioDispatcher) {
            _orderHistoryList.value = orderHistoryLocalDatasource.getOrderHistoryList()

        }
    }
}
