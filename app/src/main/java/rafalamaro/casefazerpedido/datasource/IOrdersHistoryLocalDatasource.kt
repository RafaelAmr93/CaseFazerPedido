package rafalamaro.casefazerpedido.datasource

import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel

interface IOrdersHistoryLocalDatasource {
    suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel>
    suspend fun getOrderDetailed(orderNumber: Int): OrderModel
    suspend fun insertOrder(order: OrderModel)
    suspend fun getOrdersCount(): Flow<Int?>
    suspend fun getTotalSales(): Flow<Double?>
}
