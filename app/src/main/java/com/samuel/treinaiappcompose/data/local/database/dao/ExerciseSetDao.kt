package com.samuel.treinaiappcompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseSetModel

@Dao
interface ExerciseSetDao {

  @Query("SELECT * FROM exercise_sets")
  suspend fun getAllExerciseSets(): List<ExerciseSetModel>

  @Query("SELECT * FROM exercise_sets WHERE exercise_id = :exerciseId")
  suspend fun getSetsByExerciseId(exerciseId: Int): List<ExerciseSetModel>

  @Insert
  suspend fun insertExerciseSets(exerciseSets: ExerciseSetModel): Long

  @Insert
  suspend fun bulkInsertExerciseSets(exerciseSets: List<ExerciseSetModel>)

  @Update
  suspend fun updateExerciseSet(exerciseSet: ExerciseSetModel)
}