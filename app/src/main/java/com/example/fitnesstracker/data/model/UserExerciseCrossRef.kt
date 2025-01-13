package com.example.fitnesstracker.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "exerciseId"])
data class UserExerciseCrossRef(
    val userId: Int,
    val exerciseId: Int
)