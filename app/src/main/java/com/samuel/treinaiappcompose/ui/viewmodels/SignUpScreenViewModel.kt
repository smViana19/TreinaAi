package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class SignUpScreenViewModel @Inject constructor(

) : ViewModel() {
  private val firebaseAuth = FirebaseAuth.getInstance()
  private val _email = mutableStateOf("")
  val email: MutableState<String> = _email
  private val _password = mutableStateOf("")
  val password: MutableState<String> = _password

  private val _isRegistred = mutableStateOf(false)
  val isRegistred: MutableState<Boolean> = _isRegistred

  fun signUpWithEmailAndPassword() {
    viewModelScope.launch {
      try {
        val result =
          firebaseAuth.createUserWithEmailAndPassword(_email.value, _password.value).await()
        _isRegistred.value = true
        val user = result.user
        println("Cadastrado com sucesso: ${user?.email}")
      } catch (e: FirebaseAuthException) {
        println("erro ao logar: ${e.message}")
        _isRegistred.value = false
        e.printStackTrace()
      } catch (e: Exception) {
        e.printStackTrace()
        _isRegistred.value = false
      }
    }
  }
}