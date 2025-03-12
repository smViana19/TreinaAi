package com.samuel.treinaiappcompose.ui.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography


enum class AppTextFieldVariant {
  FILLED,
  UNDERLINE,
  OUTLINED
}

@Composable
fun AppTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  singleLine: Boolean = true,
  placeholder: String? = null,
  shape: Shape = RoundedCornerShape(12.dp),
  isError: Boolean = false,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),

  ) {
  OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    singleLine = singleLine,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    visualTransformation = visualTransformation,
    isError = isError,
    shape = shape,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    placeholder = placeholder?.let {
      {
        Text(
          it, style = Typography.labelMedium
        )
      }
    },
    textStyle = LocalTextStyle.current,
    colors = OutlinedTextFieldDefaults.colors(
      unfocusedBorderColor = MaterialTheme.colorScheme.outline,
      focusedBorderColor = MaterialTheme.colorScheme.secondary,
    ),
  )
}

@Composable
fun AppPasswordTextField(
  modifier: Modifier = Modifier,
  value: String,
  placeholder: String? = null,
  onValueChange: (String) -> Unit,
) {
  var isPasswordVisible by remember { mutableStateOf(false) }
  AppTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    leadingIcon = {
      Icon(
        painter = painterResource(R.drawable.ic_lock_24),
        contentDescription = stringResource(R.string.password_icon)
      )
    },
    placeholder = placeholder,
    trailingIcon = {
      IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
        val icon = if (isPasswordVisible) {
          R.drawable.ic_visibility_off_24
        } else {
          R.drawable.ic_visibility_on_24
        }
        Icon(
          painter = painterResource(icon),
          contentDescription = if (isPasswordVisible) stringResource(R.string.hide_password) else stringResource(
            R.string.show_password
          )
        )
      }
    }
  )
}


@Preview(showBackground = true)
@Composable
private fun AppTextFieldOutlinedPreview() {
  AppTheme {
    AppTextField(
      value = "samuel@gmail.com",
      leadingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_email_24),
          contentDescription = stringResource(R.string.email_icon_description)
        )
      },
      onValueChange = {}
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun AppPasswordTextFieldPreview() {
  AppTheme {
    AppPasswordTextField(value = "1234", onValueChange = {})
  }
}



