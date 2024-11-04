package rafalamaro.casefazerpedido.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.data.model.ProductsListModel

@Entity(tableName = "orders")
data class OrdersHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("client_name") val clientName: String,
    @ColumnInfo("products_list") val productsList: ProductsListModel,
    @ColumnInfo("order_total_value") val orderTotalValue: Double
)

fun OrdersHistoryEntity.toModel() = OrderModel(
    id = this.id,
    clientName = this.clientName,
    productsList = this.productsList,
    orderTotalValue = this.orderTotalValue
)
