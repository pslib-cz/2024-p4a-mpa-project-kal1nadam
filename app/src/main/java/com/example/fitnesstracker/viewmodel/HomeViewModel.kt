package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.UserExerciseCrossRef
import com.example.fitnesstracker.data.model.UserWithExercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dao: FitnessDao) : ViewModel(){
    val userWithExercises: LiveData<List<UserWithExercises>> = dao.getUserWithExercises(userId = 1).asLiveData()

    fun addExercise(exercise: Exercise){
        viewModelScope.launch {
            dao.insertExercise(exercise)
            dao.insertUserExerciseCrossRef(UserExerciseCrossRef(userId = 1, exerciseId = exercise.exerciseId))
        }
    }
}