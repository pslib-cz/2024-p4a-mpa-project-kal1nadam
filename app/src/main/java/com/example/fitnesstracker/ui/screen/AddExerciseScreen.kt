package com.example.fitnesstracker.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.viewmodel.HomeViewModel

@Composable
fun AddExerciseScreen(viewModel: HomeViewModel = hiltViewModel(), onSave: () -> Unit){
    var name by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Column {
        TextField(value = name, onValueChange = {name = it}, label = { Text("Exercise Name") })
        TextField(value = duration, onValueChange = {duration = it}, label = { Text("Duration (mins)") })
        Button(onClick = {
            viewModel.addExercise(Exercise(name = name, durationMinutes = duration.toInt(), date = "2024-12-28"))
            onSave()
        }) {
            Text("Save")
        }

    }
}