package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.AppCheckbox
import com.samuel.treinaiappcompose.ui.components.AppDialog
import com.samuel.treinaiappcompose.ui.components.AppLoader
import com.samuel.treinaiappcompose.ui.components.AppPasswordTextField
import com.samuel.treinaiappcompose.ui.components.AppTextField
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography
import com.samuel.treinaiappcompose.ui.viewmodels.SignInScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
  viewModel: SignInScreenViewModel,
  navController: NavController
) {

  val focusManager = LocalFocusManager.current
  LaunchedEffect(viewModel.state.value.isSuccess) {
    if(viewModel.state.value.isSuccess) {
      navController.navigate(Screens.HOME_SCREEN.name)
      viewModel.resetSuccessState()
    }
  }
  Scaffold(
    modifier = Modifier.pointerInput(Unit) {
      detectTapGestures {
        focusManager.clearFocus()
      }
    },
    topBar = {
      TopAppBar(
        title = {},
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(Screens.ONBOARDING_SCREEN.name)
          }) {
            Icon(
              painter = painterResource(R.drawable.ic_back_24),
              contentDescription = stringResource(R.string.back_icon)
            )
          }
        }
      )
    },
    content = { paddingValues ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(color = MaterialTheme.colorScheme.background)
          .padding(paddingValues)
          .padding(24.dp),
      ) {
        Column(
          modifier = Modifier
        ) {
          Text(
            text = stringResource(R.string.sign_in_screen_title),
            color = MaterialTheme.colorScheme.primary,
            style = Typography.titleLarge
          )
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = stringResource(R.string.sign_in_subtitle),
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
            value = viewModel.email.value,
            placeholder = stringResource(R.string.email_placeholder),
            onValueChange = { newValue ->
              viewModel.email.value = newValue
            },
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
            value = viewModel.password.value,
            onValueChange = { newValue ->
              viewModel.password.value = newValue
            }
          )
          Spacer(modifier = Modifier.height(16.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              modifier = Modifier.padding(0.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {

              AppCheckbox(modifier = Modifier.padding(end = 8.dp))
              Text(
                text = stringResource(R.string.remember_credentials),
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
              )
            }

            Text(
              modifier = Modifier.clickable { },
              text = stringResource(R.string.forgot_password),
              color = MaterialTheme.colorScheme.secondary,
              style = Typography.titleMedium
            )
          }

          Spacer(modifier = Modifier.height(36.dp))
          DefaultAppButton(
            onClick = {
              viewModel.signInWithEmailAndPassword()
            },
            text = stringResource(R.string.login)
          )
        }
      }
    }
  )
  if (viewModel.state.value.isLoading) {
    AppLoader()
  }
  AppDialog(state = viewModel.dialogState.value)
}

@Preview
@Composable
private fun SignInScreenPreview() {
  val navController = rememberNavController()
  val viewModel = SignInScreenViewModel()
  AppTheme {
    SignInScreen(viewModel, navController)
  }
}