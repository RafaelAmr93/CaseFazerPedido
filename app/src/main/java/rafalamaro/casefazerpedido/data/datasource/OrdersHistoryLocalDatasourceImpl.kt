package rafalamaro.casefazerpedido.data.datasource

import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.data.database.OrdersHistoryDao
import rafalamaro.casefazerpedido.data.database.entities.toModel
import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.data.model.toEntity

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
