package rafalamaro.casefazerpedido.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromProductListModel(value: ProductsListModel): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProductListModel(value: String): ProductsListModel {
        val type = object : TypeToken<ProductsListModel>() {}.type
        return Gson().fromJson(value, type)
    }
}