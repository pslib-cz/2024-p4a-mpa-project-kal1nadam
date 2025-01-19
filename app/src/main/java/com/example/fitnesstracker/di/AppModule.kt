package com.example.fitnesstracker.di

import android.content.Context
import androidx.room.Room
import com.example.fitnesstracker.data.db.FitnessDao
import com.example.fitnesstracker.data.db.FitnessDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitnessDatabase {
        return Room.databaseBuilder(
            context,
            FitnessDatabase::class.java,
            "fitness_database"
        ).build()
    }

    @Provides
    fun provideFitnessDao(db: FitnessDatabase): FitnessDao = db.fitnessDao()
}

