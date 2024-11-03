package rafalamaro.casefazerpedido.database

import androidx.room.Entity
import rafalamaro.casefazerpedido.model.ProductModel

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