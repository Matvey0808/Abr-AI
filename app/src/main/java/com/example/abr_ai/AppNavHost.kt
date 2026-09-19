package com.example.abr_ai

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.abr_ai.ui.pages.ContentPage
import com.example.abr_ai.ui.pages.HomePage

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Content : Routes("content")
}

@ExperimentalMaterial3Api
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable (Routes.Home.route) { HomePage(navController = navController) }
        composable (Routes.Content.route) { ContentPage(navController = navController) }
    }
}
