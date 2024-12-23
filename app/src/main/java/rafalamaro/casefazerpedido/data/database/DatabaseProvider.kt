package rafalamaro.casefazerpedido.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "case_fazer_pedido"
            )
                .build()
            INSTANCE = instance
            instance
        }
    }
}
