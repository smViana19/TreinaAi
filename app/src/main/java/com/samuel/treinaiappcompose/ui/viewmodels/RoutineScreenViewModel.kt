package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.samuel.treinaiappcompose.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RoutineScreenViewModel @Inject constructor(
  private val authRepository: AuthRepository,
) : ViewModel() {

  private val _username = mutableStateOf("")
  val username: MutableState<String> = _username

  fun getUsername() {
    val uid = authRepository.getCurrentUser()?.uid ?: ""
    viewModelScope.launch {
      _username.value = authRepository.getCurrentUserNameById(uid)
    }
  }


}