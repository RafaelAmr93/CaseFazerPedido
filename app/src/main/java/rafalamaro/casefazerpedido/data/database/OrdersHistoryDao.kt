package rafalamaro.casefazerpedido.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import rafalamaro.casefazerpedido.data.database.entities.BaseOrdersHistoryEntity
import rafalamaro.casefazerpedido.data.database.entities.OrdersHistoryEntity

@Dao
interface OrdersHistoryDao {
    @Query("SELECT o.id, o.client_name FROM orders o")
    fun getOrdersHistoryList(): List<BaseOrdersHistoryEntity>

    @Query("SELECT * FROM orders WHERE id = :orderNumber")
    fun getOrderDetailed(orderNumber: Int): OrdersHistoryEntity

    @Insert
    fun insertOrder(order: OrdersHistoryEntity)

    @Query("SELECT count(*) FROM orders")
    fun getOrdersCount(): Flow<Int>

    @Query("SELECT sum(o.order_total_value) FROM orders o")
    fun getTotalSales(): Flow<Double>
}
