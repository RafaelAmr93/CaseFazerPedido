package rafalamaro.casefazerpedido.datasource

import rafalamaro.casefazerpedido.model.OrderModel

interface IOrdersHistoryLocalDatasource {
    suspend fun getFirstOrder(): OrderModel
    suspend fun getNextOrder(id: Int): OrderModel
    suspend fun getPreviousOrder(id: Int): OrderModel
    suspend fun insertOrder(order: OrderModel)
}
