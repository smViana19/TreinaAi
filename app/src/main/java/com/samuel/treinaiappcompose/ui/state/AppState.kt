package com.samuel.treinaiappcompose.ui.state

data class AppState(
  val isSuccess: Boolean = false,
  val isLoading: Boolean = false,
  val isError: Boolean = false
)