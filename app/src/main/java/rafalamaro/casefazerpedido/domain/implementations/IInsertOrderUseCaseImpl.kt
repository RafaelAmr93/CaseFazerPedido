package rafalamaro.casefazerpedido.domain.implementations

import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.domain.contracts.IInsertOrderUseCase

class IInsertOrderUseCaseImpl(
    private val ordersHistoryLocalDatasource: IOrdersHistoryLocalDatasource
) : IInsertOrderUseCase {
    override suspend fun insertOrder(order: OrderModel) {
        ordersHistoryLocalDatasource.insertOrder(order)
    }
}
