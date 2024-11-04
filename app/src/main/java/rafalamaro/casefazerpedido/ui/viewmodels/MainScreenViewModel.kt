package rafalamaro.casefazerpedido.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rafalamaro.casefazerpedido.domain.contracts.IGetOrdersCountUseCase
import rafalamaro.casefazerpedido.domain.contracts.IGetTotalSalesUseCase
import rafalamaro.casefazerpedido.ui.uiStates.MainScreenUiState

class MainScreenViewModel(
    private val getTotalSalesUseCase: IGetTotalSalesUseCase,
    private val getOrdersCountUseCase: IGetOrdersCountUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val ordersCount: MutableStateFlow<Int?> = MutableStateFlow(null)

    private val totalSales: MutableStateFlow<Double?> = MutableStateFlow(null)

    val mainScreenUiState: StateFlow<MainScreenUiState> = combine(
        ordersCount,
        totalSales
    ) { ordersCount, totalSales ->
        MainScreenUiState.Loaded(ordersCount, totalSales)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainScreenUiState.Loading
    )


    internal fun getOrdersCount() {
        viewModelScope.launch(dispatcher) {
            getOrdersCountUseCase.getOrdersCount()
                .collect { count ->
                    ordersCount.value = count
                }
        }
    }

    internal fun getTotalSales() {
        viewModelScope.launch(dispatcher) {
            getTotalSalesUseCase.getTotalSales()
                .collect { sales ->
                    totalSales.value = sales
                }
        }
    }
}