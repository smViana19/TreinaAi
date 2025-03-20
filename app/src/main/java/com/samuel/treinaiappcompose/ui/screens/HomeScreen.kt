package com.samuel.treinaiappcompose.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
  BackHandler {  }

  Column(
    modifier = Modifier.fillMaxSize()
  ) {

  }
}

@Preview
@Composable
private fun HomeScreenPreview() {
  HomeScreen()
}