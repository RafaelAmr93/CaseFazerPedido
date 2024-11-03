package rafalamaro.casefazerpedido.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.datasource.IOrdersHistoryLocalDatasource

class MainScreenViewModel(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _ordersCount = MutableStateFlow(0)
    val ordersCount: StateFlow<Int> = _ordersCount.asStateFlow()

    private val _totalSales = MutableStateFlow(0.0)
    val totalSales: StateFlow<Double> = _totalSales.asStateFlow()

    init {
        getOrdersCount()
        getTotalSales()
    }

    private fun getOrdersCount() {
        viewModelScope.launch(dispatcher) {
            orderHistoryLocalDatasource.getOrdersCount()
                .collect { count ->
                    _ordersCount.value = count
                }
        }
    }

    private fun getTotalSales() {
        viewModelScope.launch(dispatcher) {
            orderHistoryLocalDatasource.getTotalSales()
                .collect { sales ->
                    _totalSales.value = sales
                }
        }
    }
}