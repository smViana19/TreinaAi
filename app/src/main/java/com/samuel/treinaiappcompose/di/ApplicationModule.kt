package com.samuel.treinaiappcompose.di

import android.app.Application
import androidx.room.Room
import com.samuel.treinaiappcompose.data.local.database.AppDatabase
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseDao
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseSetDao
import com.samuel.treinaiappcompose.data.local.database.dao.WorkoutDao
import com.samuel.treinaiappcompose.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
  @Provides
  @Singleton
  fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }
  @Provides
  fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
    return appDatabase.getExerciseDao()
  }

  @Provides
  fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
    return appDatabase.getWorkoutDao()
  }

  @Provides
  fun provideExerciseSetDao(appDatabase: AppDatabase): ExerciseSetDao {
    return appDatabase.getExerciseSetDao()
  }
}