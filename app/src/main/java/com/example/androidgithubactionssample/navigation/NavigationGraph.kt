package com.example.androidgithubactionssample.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidgithubactionssample.ui.screen.display.DisplayScreen
import com.example.androidgithubactionssample.ui.screen.input.InputScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "inputScreen",
    ) {
        composable("inputScreen") {
            InputScreen(
                onSearch = { text ->
                    val safeName = Uri.encode(text)
                    navController.navigateSingleTopTo("displayScreen/$safeName")
                },
            )
        }
        composable(
            "displayScreen/{inputText}",
            arguments = listOf(navArgument("inputText") { type = NavType.StringType }),
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
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }