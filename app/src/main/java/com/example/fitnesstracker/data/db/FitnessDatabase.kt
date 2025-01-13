package com.example.fitnesstracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesstracker.data.model.Exercise
import com.example.fitnesstracker.data.model.User
import com.example.fitnesstracker.data.model.UserExerciseCrossRef

@Database(entities = [User::class, Exercise::class, UserExerciseCrossRef::class], version = 1)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao
}