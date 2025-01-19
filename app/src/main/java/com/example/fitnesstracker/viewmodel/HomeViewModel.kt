package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.User
import com.example.fitnesstracker.data.model.UserExerciseCrossRef
import com.example.fitnesstracker.data.model.UserWithExercises
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

    // Insert a new user with the provided name.
    fun addUser(name: String) {
        viewModelScope.launch {
            // Insert user
            dao.insertUser(User(name = name))
        }
    }
//    val userWithExercises: LiveData<List<UserWithExercises>> = dao.getUserWithExercises(userId = 1).asLiveData()
//
//    fun addExercise(exercise: Exercise){
//        viewModelScope.launch {
//            dao.insertExercise(exercise)
//            dao.insertUserExerciseCrossRef(UserExerciseCrossRef(userId = 1, exerciseId = exercise.exerciseId))
//        }
//    }
}