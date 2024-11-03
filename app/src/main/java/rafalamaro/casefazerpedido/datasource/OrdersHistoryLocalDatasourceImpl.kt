package rafalamaro.casefazerpedido.datasource

import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.database.OrdersHistoryDao
import rafalamaro.casefazerpedido.database.toModel
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.toEntity

class OrdersHistoryLocalDatasourceImpl(
    private val dao: OrdersHistoryDao
) : IOrdersHistoryLocalDatasource {
    override suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel> {
        return dao.getOrdersHistoryList().map { it.toModel() }
    }

    override suspend fun getOrderDetailed(orderNumber: Int): OrderModel {
        return dao.getOrderDetailed(orderNumber).toModel()
    }

    override suspend fun insertOrder(order: OrderModel) {
        dao.insertOrder(order.toEntity())
    }

    override suspend fun getOrdersCount(): Flow<Int> {
        return dao.getOrdersCount()
    }

    override suspend fun getTotalSales(): Flow<Double> {
        return dao.getTotalSales()
    }
}
