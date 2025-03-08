package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuel.treinaiappcompose.R

@Composable
fun OnboardingScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
         // Ajuste de proporção baseado na imagem (exemplo)
    ) {
      Image(
        modifier = Modifier
          .fillMaxSize(),
        painter = painterResource(R.drawable.treinai_man_onboarding_fade),
        contentDescription = stringResource(R.string.man_onboarding_description),
      )
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = stringResource(R.string.onboarding_phrase)
        )
        Text(
          text = "Organize seus treinos de forma eficiente",
          style = TextStyle(
            fontSize = 16.sp
          ),
          color = Color.DarkGray
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
  OnboardingScreen()
}