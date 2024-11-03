package rafalamaro.casefazerpedido.datasource

import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel

interface IOrdersHistoryLocalDatasource {
    suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel>
    suspend fun insertOrder(order: OrderModel)
}
