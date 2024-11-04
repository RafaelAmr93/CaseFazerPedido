package rafalamaro.casefazerpedido.domain.contracts

import rafalamaro.casefazerpedido.data.model.OrderModel

interface IGetOrderDetailedUseCase {
    suspend fun getOrderDetailed(orderNumber: Int): OrderModel
}
