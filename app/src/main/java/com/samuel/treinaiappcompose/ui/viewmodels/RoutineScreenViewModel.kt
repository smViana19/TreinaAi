package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RoutineScreenViewModel @Inject constructor() : ViewModel() {
  private val auth = FirebaseAuth.getInstance()
  private val firestore = FirebaseFirestore.getInstance()

  private val _username = mutableStateOf("")
  val username: MutableState<String> = _username

  fun getUsername() {
    val uid = auth.currentUser?.uid ?: ""
    viewModelScope.launch {
      val findName = firestore.collection("users").document(uid).get().await()
      _username.value = findName.getString("name") ?: ""
    }
  }
}