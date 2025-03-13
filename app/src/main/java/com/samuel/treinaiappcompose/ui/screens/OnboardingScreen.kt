package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography
import com.samuel.treinaiappcompose.ui.theme.primaryFontFamilyRegular

@Composable
fun OnboardingScreen() {

  Box(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Image(
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
      painter = painterResource(R.drawable.treinai_man_training_onboarding),
      contentDescription = stringResource(R.string.man_onboarding_description),
    )

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color.Transparent,
              MaterialTheme.colorScheme.background.copy(alpha = 1f),
              MaterialTheme.colorScheme.background
            ),
            startY = 200f
          )
        )
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        ) {
          Text(
            text = stringResource(R.string.onboarding_phrase),
            color = MaterialTheme.colorScheme.primary,
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
          )
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = stringResource(R.string.onboarding_app_description),
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = primaryFontFamilyRegular,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      Column(modifier = Modifier.fillMaxWidth()) {
        DefaultAppButton(
          onClick = {},
          text = stringResource(R.string.sign_up)
        )
        Spacer(modifier = Modifier.height(8.dp))
        DefaultAppButton(
          onClick = {},
          text = stringResource(R.string.login)
        )
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
  AppTheme {
    OnboardingScreen()
  }
}