package com.samuel.treinaiappcompose.ui.state

data class DialogState(
  val open: Boolean = false,
  val type: DialogType = DialogType.NONE,
  val title: String? = null, // TODO: RETRIEVE THIS PARAMETER
  val titleResId: Int? = null,  // TODO: RETRIEVE THIS PARAMETER
  val msgResId: Int? = null,
  val msg: String? = null,
  val colors: () -> Unit = {},
  val confirmButton: String = "Ok",
  val dismissButton: String? = null,
  val onConfirm: () -> Unit = {},
  val onDismiss: () -> Unit = {}
)

enum class DialogType {
  NONE,
  SUCCESS,
  ERROR,
  ALERT
}