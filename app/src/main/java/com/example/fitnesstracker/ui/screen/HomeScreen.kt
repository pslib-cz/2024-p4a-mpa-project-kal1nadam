package com.example.fitnesstracker.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.fitnesstracker.viewmodel.HomeViewModel
import com.example.fitnesstracker.viewmodel.StepCounterViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    stepCounterViewModel: StepCounterViewModel = hiltViewModel(),
    onUserClicked: (Int) -> Unit
){
    val userList by viewModel.users.collectAsState()

    var newUserName by remember { mutableStateOf("") }
    var newExerciseName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // --- CREATE NEW USER ---
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = newUserName,
                onValueChange = { newUserName = it },
                label = { Text("New User Name") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newUserName.isNotBlank()) {
                    viewModel.addUser(newUserName.trim())
                    newUserName = ""
                }
            }) {
                Text("Add")
            }
        }

        // --- LIST USERS ---
        LazyColumn {
            items(userList) { user ->
                // Each user row is clickable
                Text(
                    text = "User #${user.userId}: ${user.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onUserClicked(user.userId)
                        }
                        .padding(8.dp)
                )
            }
        }

        // --- ADD NEW EXERCISE ---
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = newExerciseName,
                onValueChange = { newExerciseName = it },
                label = { Text("New Exercise Name") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newExerciseName.isNotBlank()) {
                    viewModel.addExercise(newExerciseName.trim(), "", 10)
                    newExerciseName = ""
                }
            }) {
                Text("Add Exercise")
            }
        }

        val exerciseList by viewModel.exercises.collectAsState()

        // --- LIST ALL EXERCISES ---
        LazyColumn {
            items(exerciseList) { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Exercise: ${exercise.name}")
                    IconButton(onClick = { viewModel.deleteExercise(exercise) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Exercise"
                        )
                    }
                }
            }
        }

        val stepCount by stepCounterViewModel.stepCount.collectAsState()

        // Display Step Count
        Text(
            text = "Steps: $stepCount",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

    }
}
