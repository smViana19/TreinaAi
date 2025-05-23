package com.samuel.treinaiappcompose.data.mocks

import com.samuel.treinaiappcompose.data.local.database.dao.WorkoutDao
import com.samuel.treinaiappcompose.data.local.database.model.WorkoutModel

class WorkoutDaoMock: WorkoutDao {
  override suspend fun getAllWorkouts(): List<WorkoutModel> {
    TODO("Not yet implemented")
  }

  override suspend fun getWorkoutById(workoutId: Int): WorkoutModel {
    TODO("Not yet implemented")
  }

  override suspend fun insertWorkout(workout: WorkoutModel) {
    TODO("Not yet implemented")
  }

  override suspend fun updateWorkout(workout: WorkoutModel) {
    TODO("Not yet implemented")
  }

  override suspend fun deleteWorkout(workout: WorkoutModel) {
    TODO("Not yet implemented")
  }
}