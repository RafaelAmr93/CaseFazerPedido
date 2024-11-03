package rafalamaro.casefazerpedido.datasource

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

    override suspend fun insertOrder(order: OrderModel) {
        dao.insertOrder(order.toEntity())
    }
}
