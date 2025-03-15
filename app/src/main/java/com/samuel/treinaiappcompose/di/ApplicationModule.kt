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

/**
 * Dagger module responsible for providing application-level dependencies.
 *
 * This module supplies database-related dependencies, including the Room database instance
 * and DAOs for exercises, workouts, and exercise sets.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
  /**
   * Provides a singleton instance of [AppDatabase].
   *
   * @param application The application context required to build the Room database.
   * @return An instance of [AppDatabase].
   */
  @Provides
  @Singleton
  fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

  /**
   * Provides an instance of [ExerciseDao] for accessing exercise-related database operations.
   *
   * @param appDatabase The Room database instance.
   * @return An instance of [ExerciseDao].
   */
  @Provides
  fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
    return appDatabase.getExerciseDao()
  }

  /**
   * Provides an instance of [WorkoutDao] for accessing workout-related database operations.
   *
   * @param appDatabase The Room database instance.
   * @return An instance of [WorkoutDao].
   */
  @Provides
  fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
    return appDatabase.getWorkoutDao()
  }

  /**
   * Provides an instance of [ExerciseSetDao] for accessing exercise set-related database operations.
   *
   * @param appDatabase The Room database instance.
   * @return An instance of [ExerciseSetDao].
   */
  @Provides
  fun provideExerciseSetDao(appDatabase: AppDatabase): ExerciseSetDao {
    return appDatabase.getExerciseSetDao()
  }
}