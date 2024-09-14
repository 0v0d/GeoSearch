package com.example.geosearch.navigation

import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.geosearch.ui.screen.display.DisplayScreen
import com.example.geosearch.ui.screen.input.InputScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.INPUT_SCREEN.name,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(),
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(),
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(),
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(),
            )
        },
    ) {
        composable(NavigationGraph.INPUT_SCREEN.name) {
            InputScreen(
                onSearch = { text ->
                    val safeName = Uri.encode(text)
                    navController.navigateSingleTopTo(
                        NavigationGraph.DISPLAY_SCREEN.name +
                            "/$safeName",
                    )
                },
            )
        }
        composable(
            route = NavigationGraph.DISPLAY_SCREEN.name + "/{inputText}",
            arguments = listOf(navArgument("inputText") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(),
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(),
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(),
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(),
                )
            },
        ) { backStackEntry ->
            DisplayScreen(
                inputText = backStackEntry.arguments?.getString("inputText") ?: "",
                onClick = { navController.popBackStack() },
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id,
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

private enum class NavigationGraph {
    INPUT_SCREEN,
    DISPLAY_SCREEN,
}
