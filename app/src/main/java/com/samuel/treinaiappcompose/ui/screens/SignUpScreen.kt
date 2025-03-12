package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.AppPasswordTextField
import com.samuel.treinaiappcompose.ui.components.AppTextField
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography

@Composable
fun SignUpScreen(
//  viewModel: SignUpScreenViewModel = hiltViewModel()
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(top = 24.dp, start = 24.dp, end = 24.dp),

  ) {
    Column (
      modifier = Modifier
    ){
      Text(
        text = stringResource(R.string.sign_up_screen_title),
        color = MaterialTheme.colorScheme.primary,
        style = Typography.titleLarge
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = stringResource(R.string.sign_up_screen_subtitle),
        color = MaterialTheme.colorScheme.tertiary,
        style = Typography.titleSmall,
      )
    }

    Spacer(modifier = Modifier.height(32.dp))
    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {
      AppTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        placeholder = stringResource(R.string.name_placeholder),
        onValueChange = {},
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Next
        ),
        leadingIcon = {
          Icon(
            painter = painterResource(R.drawable.ic_person_24),
            contentDescription = stringResource(R.string.name_icon)
          )
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
      AppTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        placeholder = stringResource(R.string.email_placeholder),
        onValueChange = {},
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Email,
          imeAction = ImeAction.Next
        ),
        leadingIcon = {
          Icon(
            painter = painterResource(R.drawable.ic_email_24),
            contentDescription = stringResource(R.string.email_icon_description)
          )
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
      AppPasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = stringResource(R.string.password_placeholder),
        value = "",
        onValueChange = {}
      )
      Spacer(modifier = Modifier.height(36.dp))
      DefaultAppButton(
        onClick = {},
        text = stringResource(R.string.sign_up)
      )
    }

  }

}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
  AppTheme {
//  val viewModel = SignUpScreenViewModel()
    SignUpScreen(
//    viewModel = viewModel
    )
  }
}