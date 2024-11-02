package rafalamaro.casefazerpedido.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        ) {
            MainScreen(
                onNavigateToOrderHistory = { navController.navigate(Routes.ORDER_HISTORY.route) },
                onNavigateToPlaceOrder = { navController.navigate(Routes.PLACE_ORDER.route) }
            )
        }
        composable(Routes.PLACE_ORDER.route) {
            PlaceOrderScreen()
        }
        composable(Routes.ORDER_HISTORY.route) {
            OrderHistoryScreen()
        }
    }
}