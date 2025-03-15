package com.samuel.treinaiappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.state.DialogState
import com.samuel.treinaiappcompose.ui.state.DialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class SignUpScreenViewModel @Inject constructor(

) : ViewModel() {
  private val firebaseAuth = FirebaseAuth.getInstance()

  private val _name = mutableStateOf("")
  val name: MutableState<String> = _name
  private val _email = mutableStateOf("")
  val email: MutableState<String> = _email
  private val _password = mutableStateOf("")
  val password: MutableState<String> = _password

  private val _dialogState = mutableStateOf(DialogState(open = false))
  val dialogState: State<DialogState> = _dialogState

  /**
   * Registers a new user with Firebase Authentication and stores their name in Firestore.
   *
   * This function:
   * - Validates that the name, email, and password fields are not empty.
   * - Creates a new user in Firebase Authentication.
   * - Stores the user's name in Firestore under the "users" collection, using their UID.
   * - Handles authentication errors, such as weak passwords, email collisions, and invalid formats.
   * - Displays appropriate success or error dialogs based on the registration outcome.
   *
   * @throws FirebaseAuthWeakPasswordException If the password is too weak.
   * @throws FirebaseAuthUserCollisionException If an account with the given email already exists.
   * @throws FirebaseAuthInvalidCredentialsException If the email format is invalid.
   * @throws Exception For any other unexpected errors.
   */

  fun signUpWithEmailAndPassword() {
    viewModelScope.launch {
      try {
        if (_name.value.isEmpty() || _email.value.isEmpty() || _password.value.isEmpty()) {
          _dialogState.value = DialogState(
            open = true,
            type = DialogType.ALERT,
            msgResId = R.string.input_empty_error,
            onConfirm = {
              onClickButtonDismissDialog()
            }
          )
          return@launch
        }
        val result =
          firebaseAuth.createUserWithEmailAndPassword(_email.value, _password.value).await()
        val user = result.user
        if (user != null) {
          saveUserData(user.uid, _name.value)
        }
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.SUCCESS,
          msgResId = R.string.success_register,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
        _name.value = ""
        _email.value = ""
        _password.value = ""
      } catch (e: FirebaseAuthWeakPasswordException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.weak_password_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } catch (e: FirebaseAuthUserCollisionException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.email_used_exception,
          onConfirm = {
            onClickButtonDismissDialog()
          }
        )
      } catch (e: FirebaseAuthInvalidCredentialsException) {
        _dialogState.value = DialogState(
          open = true,
          type = DialogType.ERROR,
          msgResId = R.string.invalid_credentials_exception,
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
      }
    }
  }

  /**
   * Stores the user's name in Firestore.
   *
   * Saves the provided `name` in a Firestore document within the "users" collection, using the given `uid` as the document ID.
   *
   * @param uid The unique identifier of the user, used as the document ID in Firestore.
   * @param name The name of the user to be stored.
   *
   * @throws Exception If an error occurs while writing to Firestore.
   *
   * @see FirebaseFirestore
   */

  private suspend fun saveUserData(uid: String, name: String) {
    val userMap = hashMapOf(
      "name" to name,
    )
    try {
      FirebaseFirestore.getInstance().collection("users").document(uid).set(userMap).await()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  private fun onClickButtonDismissDialog() {
    _dialogState.value = DialogState(
      open = false
    )
  }
}





