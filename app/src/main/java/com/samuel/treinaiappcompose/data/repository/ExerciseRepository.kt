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
  suspend fun findAllExercises(): List<ExerciseModel> {
    return exerciseDao.findAllExercises()
  }

  suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel> {
    return exerciseDao.getAllExercisesByWorkoutId(workoutId)
  }

  suspend fun insertExercise(exerciseName: String, exerciseDescription: String, workoutId: Int): Long {
    val exercise = ExerciseModel(name = exerciseName, description = exerciseDescription, workoutId= workoutId)
    return exerciseDao.insertExercise(exercise)
  }

//  suspend fun getAllExercisesAndLogs(workoutId: Int): List<ExerciseAndSets> {
//    return exerciseDao.getAllExercisesAndSets(workoutId)
//  }

  suspend fun retrieveAndBulkInsertExercises(exercises: List<ExerciseModel>) {}

  suspend fun retrieveAndInsertExercises() {

  }
}