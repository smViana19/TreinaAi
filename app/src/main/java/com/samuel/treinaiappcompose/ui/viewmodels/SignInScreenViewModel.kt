package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWebException
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.data.repository.AuthRepository
import com.samuel.treinaiappcompose.ui.state.AppState
import com.samuel.treinaiappcompose.ui.state.DialogState
import com.samuel.treinaiappcompose.ui.state.DialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
  private val authRepository: AuthRepository
) : ViewModel() {


  private val _email = mutableStateOf("")
  val email: MutableState<String> = _email

  private val _password = mutableStateOf("")
  val password: MutableState<String> = _password

  private val _dialogState = mutableStateOf(DialogState(open = false))
  val dialogState: State<DialogState> = _dialogState

  private val _state = mutableStateOf(AppState())
  val state: State<AppState> = _state

  fun signInWithEmailAndPassword() {
    viewModelScope.launch {
      try {
        _state.value = _state.value.copy(isLoading = true)
        authRepository.signInWithEmailAndPassword(_email.value, _password.value)

        _dialogState.value = DialogState(
          open = true,
          type = DialogType.SUCCESS,
          msgResId = R.string.success_login,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )

        _email.value = ""
        _password.value = ""
        _state.value = _state.value.copy(isSuccess = true)
      } catch (e: FirebaseAuthInvalidCredentialsException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.invalid_credentials_login_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } catch (e: FirebaseAuthInvalidUserException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.invalid_user_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } catch (e: FirebaseTooManyRequestsException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.many_requests_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } catch (e: Exception) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.unknown_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } finally {
        _state.value = _state.value.copy(isLoading = false)
      }
    }
  }

  fun resetSuccessState() {
    _state.value = _state.value.copy(isSuccess = false)
  }

  private fun onClickButtonDismissDialog() {
    _dialogState.value = DialogState(
      open = false
    )
  }
}