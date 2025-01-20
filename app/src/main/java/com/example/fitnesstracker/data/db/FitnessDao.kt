package com.example.fitnesstracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.User
import com.example.fitnesstracker.data.model.UserExerciseCrossRef
import com.example.fitnesstracker.data.model.UserWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserExerciseCrossRef(crossRef: UserExerciseCrossRef)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserWithExercises(userId: Int): Flow<UserWithExercises>

    // Get all exercises
    @Transaction
    @Query("SELECT * FROM Exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?

}