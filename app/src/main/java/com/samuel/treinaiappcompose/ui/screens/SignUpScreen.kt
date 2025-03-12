package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.viewmodels.SignUpScreenViewModel

@Composable
fun SignUpScreen(
//  viewModel: SignUpScreenViewModel = hiltViewModel()
) {
  Column(
    modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)
  ) {
    DefaultAppButton(
      onClick = {},
      text = stringResource(R.string.sign_up)
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
//  val viewModel = SignUpScreenViewModel()
  SignUpScreen(
//    viewModel = viewModel
  )
}