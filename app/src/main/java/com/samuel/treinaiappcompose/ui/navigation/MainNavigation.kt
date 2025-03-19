package com.samuel.treinaiappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.ui.screens.HomeScreen
import com.samuel.treinaiappcompose.ui.screens.OnboardingScreen
import com.samuel.treinaiappcompose.ui.screens.SignInScreen
import com.samuel.treinaiappcompose.ui.screens.SignUpScreen
import com.samuel.treinaiappcompose.ui.viewmodels.SignInScreenViewModel
import com.samuel.treinaiappcompose.ui.viewmodels.SignUpScreenViewModel

@Composable
fun MainNavigation() {
  val navController = rememberNavController()
  val signUpScreenViewModel: SignUpScreenViewModel = hiltViewModel()
  val signInScreenViewModel: SignInScreenViewModel = hiltViewModel()


  NavHost(
    navController = navController,
    startDestination = Screens.ONBOARDING_SCREEN.name
  ) {
    composable(route = Screens.ONBOARDING_SCREEN.name) {
      OnboardingScreen(navController)
    }
    composable(route = Screens.SIGNUP_SCREEN.name) {
      SignUpScreen(signUpScreenViewModel,navController)
    }
    composable(route = Screens.SIGNIN_SCREEN.name) {
      SignInScreen(signInScreenViewModel, navController)
    }

    composable(route = Screens.HOME_SCREEN.name) {
      HomeScreen()
    }
  }
}