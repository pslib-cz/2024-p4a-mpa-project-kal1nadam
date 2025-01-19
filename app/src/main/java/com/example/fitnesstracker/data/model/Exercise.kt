package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int = 0,
    val name: String,
    val durationMinutes: Int,
    val date: String
)