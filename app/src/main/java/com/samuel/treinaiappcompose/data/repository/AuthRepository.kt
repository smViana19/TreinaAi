package com.samuel.treinaiappcompose.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
  private val auth: FirebaseAuth,
  private val firestore: FirebaseFirestore
) {

  fun getCurrentUser(): FirebaseUser? {
    return auth.currentUser
  }

  suspend fun getCurrentUserNameById(uid: String): String {
    val findName = firestore.collection("users").document(uid).get().await()
    return findName.getString("name") ?: ""
  }

  suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult {
    return auth.createUserWithEmailAndPassword(email, password).await()
  }

  suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
    return auth.signInWithEmailAndPassword(email, password).await()
  }

  suspend fun saveUserData(uid: String, name: String) {
    val userMap = hashMapOf("name" to name)
    firestore.collection("users").document(uid).set(userMap).await()
  }

  fun signOut() {
    auth.signOut()
  }

}