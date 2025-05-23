package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.treinaiappcompose.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
  private val workoutRepository: WorkoutRepository
) : ViewModel() {

  private val _workoutName = mutableStateOf("")
  val workoutName: MutableState<String> = _workoutName

  private val _workoutDescription = mutableStateOf<String?>("")
  val workoutDescription: MutableState<String?> = _workoutDescription


  fun getWorkout(workoutId: Int) {
    viewModelScope.launch {
      try {
        val workout = workoutRepository.getWorkoutById(workoutId)
        _workoutName.value = workout.name
        _workoutDescription.value = workout.description
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        //todo: estado de carregando
      }

    }
  }

  fun saveExercises() {
    viewModelScope.launch { }
  }

  fun getAllExercises() {
  }

  fun updateExercise() {}

}