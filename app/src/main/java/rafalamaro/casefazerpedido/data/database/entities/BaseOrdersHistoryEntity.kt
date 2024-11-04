package rafalamaro.casefazerpedido.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel

@Entity
data class BaseOrdersHistoryEntity(
    val id: Int = 0,
    @ColumnInfo("client_name") val clientName: String
)

fun BaseOrdersHistoryEntity.toModel() = BaseOrderHistoryModel(
    orderNumber = this.id,
    clientName = this.clientName,
)
