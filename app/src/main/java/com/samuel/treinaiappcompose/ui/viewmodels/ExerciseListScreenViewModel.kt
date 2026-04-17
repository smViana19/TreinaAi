package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import com.samuel.treinaiappcompose.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListScreenViewModel @Inject constructor(
  private val exerciseRepository : ExerciseRepository
): ViewModel() {

  private val _exercises = mutableStateOf<List<ExerciseModel>>(emptyList())
  val exercises: MutableState<List<ExerciseModel>> = _exercises
  private val _exerciseName = mutableStateOf("")
  val name: MutableState<String> = _exerciseName
  private val _exerciseDescription = mutableStateOf("")
  val description: MutableState<String> = _exerciseDescription

  fun findAllExercises() {
    viewModelScope.launch {
      try
      {
        exerciseRepository.findAllExercises()
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
  fun insertExercise(workoutId: Int) {
    viewModelScope.launch {
      try
      {

        exerciseRepository.insertExercise(_exerciseName.value,_exerciseDescription.value, workoutId)
        findAllExercises()
        _exerciseName.value = ""
        _exerciseDescription.value = ""
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
  fun filterExercises(searchText: String) {
    viewModelScope.launch {
    }
  }
}