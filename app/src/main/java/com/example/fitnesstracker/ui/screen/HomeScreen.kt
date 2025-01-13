package com.example.fitnesstracker.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.data.model.UserWithExercises
import com.example.fitnesstracker.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()){
    val exercises : List<UserWithExercises> = viewModel.userWithExercises.observeAsState(emptyList<UserWithExercises>()).value

    LazyColumn {
        items(exercises) { exercise ->
            Text(text = "${exercise.user.name}'s Exercise: ${exercise.exercises.joinToString { it.name }}")
            Text(text = "Duration: ${exercise.exercises.sumOf { it.durationMinutes }} minutes")
        }
    }
}

//@Composable
//fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
//    val exercises by viewModel.userWithExercises.observeAsState(emptyList())
//
//    LazyColumn {
//        items(exercises) { exercise ->
//            Text("${exercise.name} - ${exercise.durationMinutes} minutes")
//        }
//    }
//}