package rafalamaro.casefazerpedido.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrdersHistoryDao {
    @Query("SELECT * FROM orders ORDER BY ROWID ASC LIMIT 1")
    fun getFirstOrder(): OrdersHistoryEntity

    @Query("SELECT * FROM orders WHERE id > :id ORDER BY id LIMIT 1")
    fun getNextOrder(id: Int): OrdersHistoryEntity

    @Query("SELECT * FROM orders WHERE id < :id ORDER BY id LIMIT 1")
    fun getPreviousOrder(id: Int): OrdersHistoryEntity

    @Insert
    fun insertOrder(order: OrdersHistoryEntity)
}
