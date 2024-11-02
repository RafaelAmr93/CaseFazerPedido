package rafalamaro.casefazerpedido.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.ProductsListModel

@Entity(tableName = "orders")
data class OrdersHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("client_name") val clientName: String,
    @ColumnInfo("products_list") val productsList: ProductsListModel
)

fun OrdersHistoryEntity.toModel() = OrderModel(
    id = this.id,
    clientName = this.clientName,
    productsList = this.productsList
)
