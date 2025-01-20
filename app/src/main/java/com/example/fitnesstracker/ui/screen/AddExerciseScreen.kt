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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.viewmodel.AddExerciseViewModel

@Composable
fun AddExerciseScreen(
    userId: Int,
    viewModel: AddExerciseViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val exercises by viewModel.exercises.collectAsState()
    val crossRefs by viewModel.getCrossRefsForUser(userId).collectAsState()

    var userName by remember { mutableStateOf<String?>(null) }

    // Fetch the user's name when the composable is displayed
    LaunchedEffect(userId) {
        userName = viewModel.getUserName(userId)
    }

    // Keep track of connected exercise IDs
    val connectedExerciseIds = crossRefs.map { it.exerciseId }.toSet()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Back button
        Button(onClick = onNavigateBack) {
            Text("Back")
        }

        // Title
        Text(
            text = "Exercises for $userName",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

        // List all exercises
        LazyColumn {
            items(exercises) { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Exercise name
                    Text("Exercise: ${exercise.name}")

                    // Connect/Disconnect button
                    if (connectedExerciseIds.contains(exercise.exerciseId)) {
                        Button(
                            onClick = { viewModel.disconnectExerciseFromUser(userId, exercise.exerciseId) },
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text("Disconnect", color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = { viewModel.connectExerciseToUser(userId, exercise.exerciseId) },
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text("Connect", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
