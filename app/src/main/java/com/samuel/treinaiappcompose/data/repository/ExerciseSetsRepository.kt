package com.samuel.treinaiappcompose.data.repository

import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseAndSets
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseDao
import com.samuel.treinaiappcompose.data.local.database.dao.ExerciseSetDao
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseSetModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseSetsRepository @Inject constructor(
  private val exerciseSetDao: ExerciseSetDao
) {
  suspend fun getExerciseSets(): List<ExerciseSetModel> {
    return exerciseSetDao.getAllExerciseSets()
  }

  suspend fun getSetsByExerciseId(exerciseId: Int): List<ExerciseSetModel> {
    return exerciseSetDao.getSetsByExerciseId(exerciseId)
  }

  suspend fun insertExerciseSet(exerciseSet: ExerciseSetModel): Long {
    return exerciseSetDao.insertExerciseSets(exerciseSet)
  }

  suspend fun bulkInsertExerciseSets(exerciseSets: List<ExerciseSetModel>) {
    return exerciseSetDao.bulkInsertExerciseSets(exerciseSets)
  }

  suspend fun updateExerciseSet(exerciseSet: ExerciseSetModel) {
    return exerciseSetDao.updateExerciseSet(exerciseSet)
  }
}