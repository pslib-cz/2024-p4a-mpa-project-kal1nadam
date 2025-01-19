package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.UserExerciseCrossRef
import com.example.fitnesstracker.data.model.UserWithExercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val dao: FitnessDao
) : ViewModel() {

    /**
     * Observe user + exercises as a Flow, so Compose can re-render when data changes.
     */
    fun getUserWithExercises(userId: Int): Flow<UserWithExercises> {
        return dao.getUserWithExercises(userId)
    }

    /**
     * Insert a new Exercise, then link it to the user via cross-ref.
     */
    fun addExerciseForUser(userId: Int, exerciseName: String) {
        viewModelScope.launch {
            // Insert new exercise
            val dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val exerciseId = dao.insertExercise(Exercise(name = exerciseName, date = dateTime, durationMinutes = 10))

            // Insert cross-ref
            dao.insertUserExerciseCrossRef(
                UserExerciseCrossRef(
                    userId = userId,
                    exerciseId = exerciseId.toString().toInt()
                )
            )
        }
    }
}
