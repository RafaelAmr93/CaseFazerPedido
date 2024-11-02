package rafalamaro.casefazerpedido.navigation

enum class Routes(val route: String) {
    MAIN("main/{orderPlaced}"),
    PLACE_ORDER("place_order"),
    ORDER_HISTORY("order_history")
}

fun mainRouteWithArguments(orderPlaced: Boolean) = "main/$orderPlaced"
