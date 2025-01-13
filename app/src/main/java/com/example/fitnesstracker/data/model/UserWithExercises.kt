package com.example.fitnesstracker.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithExercises(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "exerciseId",
        associateBy = Junction(UserExerciseCrossRef::class)
    )
    val exercises: List<Exercise>
)
