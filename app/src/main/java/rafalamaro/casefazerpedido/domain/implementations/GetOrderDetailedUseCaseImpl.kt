package rafalamaro.casefazerpedido.domain.implementations

import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderDetailedUseCase

class GetOrderDetailedUseCaseImpl(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource
) : IGetOrderDetailedUseCase {
    override suspend fun getOrderDetailed(orderNumber: Int): OrderModel {
        return orderHistoryLocalDatasource.getOrderDetailed(orderNumber)
    }
}
