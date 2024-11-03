package rafalamaro.casefazerpedido.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rafalamaro.casefazerpedido.ui.screens.MainScreen
import rafalamaro.casefazerpedido.ui.screens.OrderDetailedScreen
import rafalamaro.casefazerpedido.ui.screens.OrdersHistoryScreen
import rafalamaro.casefazerpedido.ui.screens.PlaceOrderScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN.route
    ) {
        composable(
            Routes.MAIN.route,
            arguments = listOf(navArgument("orderPlaced") { type = NavType.BoolType }),
            exitTransition = {
                slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700, easing = LinearEasing)
                    )
            },
            popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500, easing = LinearEasing)
                    )

            }
        ) { backStackEntry ->
            val orderPlaced = backStackEntry.arguments?.getBoolean("orderPlaced") ?: false
            MainScreen(
                onNavigateToOrderHistory = { navController.navigate(Routes.ORDER_HISTORY_LIST.route) },
                onNavigateToPlaceOrder = { navController.navigate(Routes.PLACE_ORDER.route) },
                orderPlaced
            )
        }

        composable(Routes.PLACE_ORDER.route) {
            PlaceOrderScreen(
                onOrderPlaced = { placed ->
                    navController.navigate(mainRouteWithArguments(placed)) {
                        popUpTo(Routes.MAIN.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Routes.ORDER_HISTORY_LIST.route,
            exitTransition = {
                if (targetState.destination.route == Routes.DETAILED_ORDER.route) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700, easing = LinearEasing)
                    )
                } else {
                    null
                }
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500, easing = LinearEasing)
                )

            }
        ) {
            OrdersHistoryScreen(
                onNavigateToDetailedOrder = { orderNumber ->
                    navController.navigate(detailedOrderRouteWithArguments(orderNumber))
                }
            )
        }

        composable(
            route = Routes.DETAILED_ORDER.route,
            arguments = listOf(navArgument("orderNumber") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val orderNumber = backStackEntry.arguments?.getInt("orderNumber") ?: 0
            OrderDetailedScreen(orderNumber)
        }
    }
}