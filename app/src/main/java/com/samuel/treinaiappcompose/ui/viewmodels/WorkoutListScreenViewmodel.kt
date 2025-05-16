package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.treinaiappcompose.data.local.database.model.WorkoutModel
import com.samuel.treinaiappcompose.data.repository.WorkoutRepository
import com.samuel.treinaiappcompose.ui.state.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListScreenViewmodel @Inject constructor(
  private val workoutRepository: WorkoutRepository
) : ViewModel() {
  //TODO: SINCRONIZAR COM O BD DO BACKEND

  private val _workouts = mutableStateOf<List<WorkoutModel>>(emptyList())
  val workouts: MutableState<List<WorkoutModel>> = _workouts

  private val _name = mutableStateOf("")
  val name: MutableState<String> = _name
  private val _description = mutableStateOf("")
  val description: MutableState<String> = _description

  private val _dialogState = mutableStateOf(DialogState(open = false))
  val dialogState: State<DialogState> = _dialogState

  fun getAllWorkouts() {
    viewModelScope.launch {
      _workouts.value = workoutRepository.getAllWorkouts()
    }
  }

  fun insertWorkout() {
    viewModelScope.launch {
      workoutRepository.insertWorkout(_name.value, _description.value)
      getAllWorkouts()
      _name.value = ""
      _description.value = ""

      _dialogState.value = DialogState(open = false)
    }
  }

  fun deleteWorkout(workoutModel: WorkoutModel) {
    viewModelScope.launch {
      workoutRepository.deleteWorkout(workoutModel)
      getAllWorkouts()
    }
  }

  fun openDialog() {
    _dialogState.value = DialogState(open = true)
  }
  fun closeDialog() {
    _dialogState.value = DialogState(open = false)
  }
}