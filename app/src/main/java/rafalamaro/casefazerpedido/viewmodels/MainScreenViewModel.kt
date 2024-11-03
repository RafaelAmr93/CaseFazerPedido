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

    private val _ordersCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val ordersCount: StateFlow<Int?> = _ordersCount.asStateFlow()

    private val _totalSales: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val totalSales: StateFlow<Double?> = _totalSales.asStateFlow()

    internal fun getOrdersCount() {
        viewModelScope.launch(dispatcher) {
            orderHistoryLocalDatasource.getOrdersCount()
                .collect { count ->
                    _ordersCount.value = count ?: 0
                }
        }
    }

    internal fun getTotalSales() {
        viewModelScope.launch(dispatcher) {
            orderHistoryLocalDatasource.getTotalSales()
                .collect { sales ->
                    _totalSales.value = sales ?: 0.0
                }
        }
    }
}