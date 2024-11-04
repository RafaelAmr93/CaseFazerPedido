package rafalamaro.casefazerpedido.domain.implementations

import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.domain.contracts.IGetTotalSalesUseCase

class GetTotalSalesUseCaseImpl(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource
) : IGetTotalSalesUseCase {
    override suspend fun getTotalSales(): Flow<Double?> {
        return orderHistoryLocalDatasource.getTotalSales()
    }
}
