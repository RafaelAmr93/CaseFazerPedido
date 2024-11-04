package rafalamaro.casefazerpedido.domain.contracts

import kotlinx.coroutines.flow.Flow

interface IGetTotalSalesUseCase {
    suspend fun getTotalSales(): Flow<Double?>
}
