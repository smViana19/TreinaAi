package com.samuel.treinaiappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samuel.treinaiappcompose.ui.navigation.MainNavigation
import com.samuel.treinaiappcompose.ui.screens.OnboardingScreen
import com.samuel.treinaiappcompose.ui.screens.SignUpScreen
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    enableEdgeToEdge()

    setContent {
      AppTheme {
        MainNavigation()
      }

    }
  }
}
