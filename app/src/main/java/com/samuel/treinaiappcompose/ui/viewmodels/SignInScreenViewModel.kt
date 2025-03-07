package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
) : ViewModel() {

  private val firebaseAuth = FirebaseAuth.getInstance()

  private val _email = mutableStateOf("")
  val email: MutableState<String> = _email
  private val _password = mutableStateOf("")
  val password: MutableState<String> = _password

  private val _isLoggedIn = mutableStateOf(false)
  val isLoggedIn: MutableState<Boolean> = _isLoggedIn

  fun signInWithEmailAndPassword() {
    firebaseAuth.signInWithEmailAndPassword(_email.value, _password.value)
      .addOnCompleteListener { task ->
        if(task.isSuccessful) {
          val user = firebaseAuth.currentUser
          println("Usuario: ${user?.uid}, ${user?.displayName}, ${user?.email}")
          _isLoggedIn.value = true
        } else {
          println("Deu erro no login")
        }
      }
  }
}