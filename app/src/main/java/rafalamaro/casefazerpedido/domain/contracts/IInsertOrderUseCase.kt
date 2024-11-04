package rafalamaro.casefazerpedido.domain.contracts

import rafalamaro.casefazerpedido.data.model.OrderModel

interface IInsertOrderUseCase {
    suspend fun insertOrder(order: OrderModel)
}
