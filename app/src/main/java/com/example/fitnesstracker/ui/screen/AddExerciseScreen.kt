package com.example.fitnesstracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.viewmodel.AddExerciseViewModel

@Composable
fun AddExerciseScreen(userId: Int, viewModel: AddExerciseViewModel = hiltViewModel(), onNavigateBack: () -> Unit
){
    // Collect the Flow from the VM
    val userWithExercises by viewModel.getUserWithExercises(userId).collectAsState(initial = null)

    var newExerciseName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // A back button or something similar
        Button(onClick = onNavigateBack) {
            Text("Back")
        }

        Text(
            text = userWithExercises?.user?.let {
                "Exercises for ${it.name}"
            } ?: "Loading user..."
        )

        // --- EXERCISE LIST ---
        val exercises = userWithExercises?.exercises.orEmpty()
        LazyColumn {
            items(exercises) { exercise ->
                Text(
                    text = "Exercise #${exercise.exerciseId}: ${exercise.name}",
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
            }
        }

        // --- ADD NEW EXERCISE ---
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = newExerciseName,
                onValueChange = { newExerciseName = it },
                label = { Text("New Exercise") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newExerciseName.isNotBlank()) {
                    viewModel.addExerciseForUser(userId, newExerciseName.trim())
                    newExerciseName = ""
                }
            }) {
                Text("Add")
            }
        }
    }
}