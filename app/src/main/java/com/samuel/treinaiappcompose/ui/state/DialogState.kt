package com.samuel.treinaiappcompose.ui.state
/**
 * Represents the state of a dialog in the application.
 *
 * This data class defines the configuration for displaying a dialog, including its visibility,
 * type, message, button labels, and associated actions.
 *
 * @property open Whether the dialog is currently open. Defaults to `false`.
 * @property type The type of dialog, determining its appearance and behavior. Defaults to [DialogType.NONE].
 * @property msgResId The resource ID of the message to be displayed. If set, it takes precedence over [msg]. Defaults to `null`.
 * @property msg The message to be displayed. Used if [msgResId] is not provided. Defaults to `null`.
 * @property colors A lambda function for customizing the dialog’s colors. Defaults to an empty lambda.
 * @property confirmButton The label for the confirm button. Defaults to "Ok".
 * @property dismissButton The label for the dismiss button. If `null`, the button is hidden. Defaults to `null`.
 * @property onConfirm Action executed when the confirm button is clicked. Defaults to an empty lambda.
 * @property onDismiss Action executed when the dismiss button is clicked or the dialog is dismissed externally. Defaults to an empty lambda.
 *
 * @see DialogType
 */
data class DialogState(
  val open: Boolean = false,
  val type: DialogType = DialogType.NONE,
  val msgResId: Int? = null,
  val msg: String? = null,
  val colors: () -> Unit = {},
  val confirmButton: String = "Ok",
  val dismissButton: String? = null,
  val onConfirm: () -> Unit = {},
  val onDismiss: () -> Unit = {}
)

/**
 * Represents the different types of dialogs that can be displayed in the application.
 *
 * This enum defines the various categories of dialogs, each with its own
 * intended purpose and visual style.
 *
 *
 * @see DialogState
 */

enum class DialogType {
  NONE,
  SUCCESS,
  ERROR,
  ALERT
}