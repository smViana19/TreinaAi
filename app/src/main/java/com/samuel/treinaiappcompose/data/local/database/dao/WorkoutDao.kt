package com.samuel.treinaiappcompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.samuel.treinaiappcompose.data.local.database.model.WorkoutModel

@Dao
interface WorkoutDao {
  @Query("SELECT * FROM workouts")
  suspend fun getAllWorkouts(): List<WorkoutModel>

  @Insert
  suspend fun insertWorkout(workout: WorkoutModel)

  @Update
  suspend fun updateWorkout(workout: WorkoutModel)

  @Delete
  suspend fun deleteWorkout(workout: WorkoutModel)
}