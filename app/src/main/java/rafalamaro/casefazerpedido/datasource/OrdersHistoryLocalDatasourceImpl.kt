package rafalamaro.casefazerpedido.datasource

import rafalamaro.casefazerpedido.database.OrdersHistoryDao
import rafalamaro.casefazerpedido.database.toModel
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.toEntity

class OrdersHistoryLocalDatasourceImpl(
    private val dao: OrdersHistoryDao
) : IOrdersHistoryLocalDatasource {
    override suspend fun getFirstOrder(): OrderModel {
        return dao.getFirstOrder().toModel()
    }

    override suspend fun getNextOrder(id: Int): OrderModel {
        return dao.getNextOrder(id).toModel()
    }

    override suspend fun getPreviousOrder(id: Int): OrderModel {
        return dao.getPreviousOrder(id).toModel()
    }

    override suspend fun insertOrder(order: OrderModel) {
        dao.insertOrder(order.toEntity())
    }
}
