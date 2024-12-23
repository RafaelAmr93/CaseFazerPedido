package rafalamaro.casefazerpedido.data.model

import rafalamaro.casefazerpedido.data.database.entities.OrdersHistoryEntity

data class OrderModel(
    val id: Int? = null,
    val clientName: String,
    val productsList: ProductsListModel,
    val orderTotalValue: Double
)

fun OrderModel.toEntity() = OrdersHistoryEntity(
    clientName = this.clientName,
    productsList = this.productsList,
    orderTotalValue = this.orderTotalValue
)
