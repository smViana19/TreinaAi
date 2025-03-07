package com.samuel.treinaiappcompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseDao
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseSetDao
import com.samuel.treinaiappcompose.data.local.database.dao.WorkoutDao
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseSetModel
import com.samuel.treinaiappcompose.data.local.database.model.WorkoutModel

@Database(
  entities = [ExerciseModel::class, WorkoutModel::class, ExerciseSetModel::class],
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  /**
   * WORKOUT DAO
   */
  abstract fun getWorkoutDao(): WorkoutDao

  /**
   * EXERCISES DAO
   */
  abstract fun getExerciseDao(): ExerciseDao

  /**
   * EXERCISE_SETS DAO
   */
  abstract fun getExerciseSetDao(): ExerciseSetDao
}