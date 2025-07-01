package com.samuel.treinaiappcompose.data.mocks

import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseDao
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel

class ExerciseDaoMock : ExerciseDao {
  override suspend fun getAllExercises(): List<ExerciseModel> {
    TODO("Not yet implemented")
  }

  override suspend fun bulkInsertExercises(exercise: List<ExerciseModel>) {
    TODO("Not yet implemented")
  }

  override suspend fun insertExercise(exercise: ExerciseModel): Long {
    TODO("Not yet implemented")
  }

//  override suspend fun retrieveAndBulkInsertExercises() {
//    TODO("Not yet implemented")
//  }
//
//  override suspend fun retrieveAndInsertExercises() {
//    TODO("Not yet implemented")
//  }

  override suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel> {
    TODO("Not yet implemented")
  }
}