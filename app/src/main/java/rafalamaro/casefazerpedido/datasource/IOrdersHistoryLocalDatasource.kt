package rafalamaro.casefazerpedido.datasource

import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.ProductModel

interface IOrdersHistoryLocalDatasource {
    suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel>
    suspend fun getOrderDetailed(orderNumber: Int): OrderModel
    suspend fun insertOrder(order: OrderModel)
}
