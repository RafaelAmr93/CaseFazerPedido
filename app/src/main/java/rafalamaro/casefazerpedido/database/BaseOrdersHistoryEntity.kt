package rafalamaro.casefazerpedido.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rafalamaro.casefazerpedido.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.model.OrderModel
import rafalamaro.casefazerpedido.model.ProductsListModel

@Entity
data class BaseOrdersHistoryEntity(
    val id: Int = 0,
    @ColumnInfo("client_name") val clientName: String
)

fun BaseOrdersHistoryEntity.toModel() = BaseOrderHistoryModel(
    orderNumber = this.id,
    clientName = this.clientName,
)
