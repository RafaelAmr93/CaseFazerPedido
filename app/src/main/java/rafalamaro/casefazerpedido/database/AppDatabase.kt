package rafalamaro.casefazerpedido.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rafalamaro.casefazerpedido.model.Converter

@Database(entities = [OrdersHistoryEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ordersHistoryDao(): OrdersHistoryDao
}