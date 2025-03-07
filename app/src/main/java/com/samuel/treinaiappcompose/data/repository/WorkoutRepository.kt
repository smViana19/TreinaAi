package com.samuel.treinaiappcompose.data.repository

import com.samuel.treinaiappcompose.data.local.database.dao.WorkoutDao
import com.samuel.treinaiappcompose.data.local.database.model.WorkoutModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
  private val workoutDao: WorkoutDao
){
  suspend fun getAllWorkouts(): List<WorkoutModel> {
    val workouts = workoutDao.getAllWorkouts()
    return workouts
  }
  suspend fun insertWorkouts(name: String, description: String?) {
    val workout = WorkoutModel(name = name, description = description)
    workoutDao.insertWorkout(workout)
  }
  suspend fun updateWorkout() {}
  suspend fun deleteWorkout() {}
}