package rafalamaro.casefazerpedido.data.database.entities

import androidx.room.Entity
import rafalamaro.casefazerpedido.data.model.ProductModel

@Entity
data class ProductEntity(
    val name: String,
    val quantity: Int,
    val value: Double,
    val description: String
)

fun ProductEntity.toModel() = ProductModel(
    name = this.name,
    quantity = this.quantity,
    value = this.value,
    description = this.description
)