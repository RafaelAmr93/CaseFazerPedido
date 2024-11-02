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
import rafalamaro.casefazerpedido.MainScreen
import rafalamaro.casefazerpedido.OrderHistoryScreen
import rafalamaro.casefazerpedido.PlaceOrderScreen

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
                when (targetState.destination.route) {
                    Routes.ORDER_HISTORY.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700, easing = LinearEasing)
                    )

                    Routes.PLACE_ORDER.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700, easing = LinearEasing)
                    )

                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Routes.ORDER_HISTORY.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500, easing = LinearEasing)
                    )

                    Routes.PLACE_ORDER.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500, easing = LinearEasing)
                    )

                    else -> null
                }
            }
        ) { backStackEntry ->
            val orderPlaced = backStackEntry.arguments?.getBoolean("orderPlaced") ?: false
            MainScreen(
                onNavigateToOrderHistory = { navController.navigate(Routes.ORDER_HISTORY.route) },
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
        composable(Routes.ORDER_HISTORY.route) {
            OrderHistoryScreen()
        }
    }
}