package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.ui.viewmodels.SignUpScreenViewModel

@Composable
fun SignUpScreen(
  viewModel: SignUpScreenViewModel
) {
  Column(
    modifier = Modifier.fillMaxHeight().padding(16.dp)
  ) {
    Row {
      OutlinedTextField(
        value = viewModel.email.value,
        onValueChange = { newValue ->
          viewModel.email.value = newValue
        },
        modifier = Modifier.fillMaxWidth()
      )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row{
      OutlinedTextField(
        value = viewModel.password.value,
        onValueChange = { newValue ->
          viewModel.password.value = newValue
        },
        modifier = Modifier.fillMaxWidth()
      )
    }

    Button(
      onClick = {
        viewModel.signUpWithEmailAndPassword()
      }
    ) {
      Text("Cadastrar")
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
  val viewModel = SignUpScreenViewModel()
  SignUpScreen(
    viewModel = viewModel
  )
}