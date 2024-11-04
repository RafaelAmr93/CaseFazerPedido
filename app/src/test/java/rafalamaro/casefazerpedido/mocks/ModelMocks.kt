package rafalamaro.casefazerpedido.mocks

import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.data.model.OrderModel
import rafalamaro.casefazerpedido.data.model.ProductModel
import rafalamaro.casefazerpedido.data.model.ProductsListModel

internal val productsListModelMock = ProductsListModel(
    productsList = List(size = 10) { productMock }
)

internal val productMock = ProductModel(
    name = "Coca-cola",
    quantity = 10,
    value = 10.0,
    description = "Coca-cola lata"
)

internal val orderModelMock = OrderModel(
    id = 1,
    clientName = "Name",
    productsList = productsListModelMock,
    orderTotalValue = 100.00
)

internal val baseOrderHistoryMock = BaseOrderHistoryModel(
    clientName = "Name",
    orderNumber = 1
)
