package rafalamaro.casefazerpedido.domain.implementations

import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.domain.contracts.IGetOrdersCountUseCase

class GetOrdersCountUseCaseImpl(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource
) : IGetOrdersCountUseCase {
    override suspend fun getOrdersCount(): Flow<Int?> {
        return orderHistoryLocalDatasource.getOrdersCount()
    }
}