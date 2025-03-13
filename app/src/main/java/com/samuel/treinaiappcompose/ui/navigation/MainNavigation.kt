package com.samuel.treinaiappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.ui.screens.OnboardingScreen
import com.samuel.treinaiappcompose.ui.screens.SignInScreen
import com.samuel.treinaiappcompose.ui.screens.SignUpScreen

@Composable
fun MainNavigation() {
  val navController = rememberNavController()
  //TODO: ADD INSTANCIAS VIEWMODELS

  NavHost(
    navController = navController,
    startDestination = Screens.ONBOARDING_SCREEN.name
  ) {
    composable(route = Screens.ONBOARDING_SCREEN.name) {
      OnboardingScreen(navController)
    }
    composable(route = Screens.SIGNUP_SCREEN.name) {
      SignUpScreen(navController)
    }
    composable(route = Screens.SIGNIN_SCREEN.name) {
      SignInScreen(navController)
    }

  }
}