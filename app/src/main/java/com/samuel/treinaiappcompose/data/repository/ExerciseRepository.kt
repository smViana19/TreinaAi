package com.samuel.treinaiappcompose.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseDao
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
  private val exerciseDao: ExerciseDao,
  private val firestore: FirebaseFirestore
) {
  suspend fun getExercises(): List<ExerciseModel> {
    return exerciseDao.getAllExercises()
  }

  suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel> {
    return exerciseDao.getAllExercisesByWorkoutId(workoutId)
  }

  suspend fun insertExercise(exercise: ExerciseModel): Long {
    return exerciseDao.insertExercise(exercise)
  }

//  suspend fun getAllExercisesAndLogs(workoutId: Int): List<ExerciseAndSets> {
//    return exerciseDao.getAllExercisesAndSets(workoutId)
//  }

  suspend fun retrieveAndBulkInsertExercises(exercises: List<ExerciseModel>) {}

  suspend fun retrieveAndInsertExercises() {

  }
}