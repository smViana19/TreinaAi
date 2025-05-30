package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.treinaiappcompose.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListScreenViewModel @Inject constructor(
  private val exerciseRepository : ExerciseRepository
): ViewModel() {


  fun filterExercises(searchText: String) {
    viewModelScope.launch {
      //todo
    }
  }

}