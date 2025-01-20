package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dao: FitnessDao) : ViewModel(){

    // Observe the list of users from DB
    val users: StateFlow<List<User>> =
        dao.getAllUsers()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Observe all exercises
    val exercises: StateFlow<List<Exercise>> =
        dao.getAllExercises()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Insert a new user with the provided name.
    fun addUser(name: String) {
        viewModelScope.launch {
            // Insert user
            dao.insertUser(User(name = name))
        }
    }

    fun addExercise(name: String, date: String, duration: Int) {
        viewModelScope.launch {
            dao.insertExercise(Exercise(name = name, date = date, durationMinutes = duration))
        }
    }

    // Delete an exercise
    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            dao.deleteExercise(exercise)
        }
    }
}