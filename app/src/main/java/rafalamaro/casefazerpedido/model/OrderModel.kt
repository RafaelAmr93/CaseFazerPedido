package rafalamaro.casefazerpedido.model

import rafalamaro.casefazerpedido.database.OrdersHistoryEntity

data class OrderModel(
    val id: Int? = null,
    val clientName: String,
    val productsList: ProductsListModel
)

fun OrderModel.toEntity() = OrdersHistoryEntity(
    clientName = this.clientName,
    productsList = this.productsList
)
