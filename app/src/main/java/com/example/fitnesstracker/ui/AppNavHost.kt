package com.example.fitnesstracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesstracker.ui.screen.AddExerciseScreen
import com.example.fitnesstracker.ui.screen.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "home") {
        // HOME
        composable("home") {
            HomeScreen(
                onUserClicked = { userId ->
                    // Navigate to the exercise screen with userId
                    navController.navigate("addExercise/$userId")
                }
            )
        }

        // Add Exercise
        composable(
            route = "addExercise/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            AddExerciseScreen(
                userId = userId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}