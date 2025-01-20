package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.User
import com.example.fitnesstracker.data.model.UserExerciseCrossRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val dao: FitnessDao
) : ViewModel() {

    // Fetch all exercises
    val exercises: StateFlow<List<Exercise>> =
        dao.getAllExercises()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    // Cross-references for a specific user
    fun getCrossRefsForUser(userId: Int): StateFlow<List<UserExerciseCrossRef>> =
        dao.getCrossRefsForUser(userId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    // Create a cross-reference
    fun connectExerciseToUser(userId: Int, exerciseId: Int) {
        viewModelScope.launch {
            dao.insertUserExerciseCrossRef(
                UserExerciseCrossRef(userId = userId, exerciseId = exerciseId)
            )
        }
    }

    // Disconnect exercise from user
    fun disconnectExerciseFromUser(userId: Int, exerciseId: Int) {
        viewModelScope.launch {
            dao.deleteUserExerciseCrossRef(userId, exerciseId)
        }
    }

    suspend fun getUserName(userId: Int): String? {
        return dao.getUserById(userId)?.name
    }

}
