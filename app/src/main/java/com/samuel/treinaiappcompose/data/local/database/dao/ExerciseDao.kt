package com.samuel.treinaiappcompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM exercises")
  suspend fun getAllExercises(): List<ExerciseModel>

  @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
  suspend fun bulkInsertExercises(exercise: List<ExerciseModel>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExercise(exercise: ExerciseModel): Long

  @Query(
    """
      SELECT * FROM exercises WHERE workout_id = :workoutId
    """
  )
  suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel>

  //  @Query()
//  suspend fun retrieveAndBulkInsertExercises() //TODO: AJUSTAR A QUERY CERTA PARA FAZER A SINCRONIZACAO

  //@Query()
//  suspend fun retrieveAndInsertExercises()

}
