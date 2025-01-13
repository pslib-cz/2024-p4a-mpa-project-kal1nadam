package com.example.fitnesstracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnesstracker.ui.screen.AddExerciseScreen
import com.example.fitnesstracker.ui.screen.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("addExercise") { AddExerciseScreen { navController.navigate("home") } }
    }
}