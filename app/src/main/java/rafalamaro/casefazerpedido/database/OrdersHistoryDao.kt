package rafalamaro.casefazerpedido.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrdersHistoryDao {
    @Query("SELECT o.id, o.client_name FROM orders o")
    fun getOrdersHistoryList(): List<BaseOrdersHistoryEntity>

    @Insert
    fun insertOrder(order: OrdersHistoryEntity)
}
