package rafalamaro.casefazerpedido.navigation

enum class Routes(val route: String) {
    MAIN("main/{orderPlaced}"),
    PLACE_ORDER("place_order"),
    ORDER_HISTORY_LIST("order_history_list"),
    DETAILED_ORDER("detailed_order/{orderNumber}")
}

fun mainRouteWithArguments(orderPlaced: Boolean) = "main/$orderPlaced"
fun detailedOrderRouteWithArguments(orderNumber: Int) = "detailed_order/$orderNumber"
