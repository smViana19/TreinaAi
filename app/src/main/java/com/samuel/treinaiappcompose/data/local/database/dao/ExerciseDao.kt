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

  //  @Query()
  suspend fun retrieveAndBulkInsertExercises() //TODO: AJUSTAR A QUERY CERTA PARA FAZER A SINCRONIZACAO

  //@Query()
  suspend fun retrieveAndInsertExercises()

  @Query(
    """
      SELECT * FROM exercises WHERE workout_id = :workoutId
    """
  )
  suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel>

  @Query(
    """
        SELECT 
            exercises.id AS exerciseId,
            exercises.name AS exerciseName,
            exercise_sets.reps AS reps,
            exercise_sets.weight AS weight
        FROM exercises
        INNER JOIN exercise_sets ON exercises.id = exercise_sets.exercise_id
        WHERE exercises.workout_id = :workoutId
    """
  )
  suspend fun getAllExercisesAndSets(workoutId: Int): List<ExerciseAndSets>
}

data class ExerciseAndSets(
  val exerciseId: Int = 0,
  val exerciseName: String = "",
  val reps: Int = 0,
  val weight: Double = 0.0,
)