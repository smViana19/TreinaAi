package com.samuel.treinaiappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.samuel.treinaiappcompose.ui.navigation.MainNavigation
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.screens.OnboardingScreen
import com.samuel.treinaiappcompose.ui.screens.SignUpScreen
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val user = Firebase.auth.currentUser

    val splashScreen = installSplashScreen()
    var keepSplashScreenVisible = true
    splashScreen.setKeepOnScreenCondition {
      keepSplashScreenVisible
    }
    lifecycleScope.launch {
      delay(2000)
      keepSplashScreenVisible = false
    }

    setContent {
      AppTheme {
        val startNavigation = if (user != null) {
          Screens.HOME_SCREEN.name
        } else {
          Screens.ONBOARDING_SCREEN.name
        }
        MainNavigation(startNavigation)
      }
    }
  }
}
