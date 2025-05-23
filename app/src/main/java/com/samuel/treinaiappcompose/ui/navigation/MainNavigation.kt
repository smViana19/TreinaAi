package com.samuel.treinaiappcompose.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.AppBottomBar
import com.samuel.treinaiappcompose.ui.components.BottomNavItems
import com.samuel.treinaiappcompose.ui.screens.RoutineScreen
import com.samuel.treinaiappcompose.ui.screens.OnboardingScreen
import com.samuel.treinaiappcompose.ui.screens.ProfileScreen
import com.samuel.treinaiappcompose.ui.screens.ProgressScreen
import com.samuel.treinaiappcompose.ui.screens.SettingScreen
import com.samuel.treinaiappcompose.ui.screens.SignInScreen
import com.samuel.treinaiappcompose.ui.screens.SignUpScreen
import com.samuel.treinaiappcompose.ui.screens.WorkoutListScreen
import com.samuel.treinaiappcompose.ui.screens.WorkoutScreen
import com.samuel.treinaiappcompose.ui.viewmodels.RoutineScreenViewModel
import com.samuel.treinaiappcompose.ui.viewmodels.SignInScreenViewModel
import com.samuel.treinaiappcompose.ui.viewmodels.SignUpScreenViewModel
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutListScreenViewmodel
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutViewModel

@Composable
fun MainNavigation(startNavigation: String) {
  val navController = rememberNavController()
  val signUpScreenViewModel: SignUpScreenViewModel = hiltViewModel()
  val signInScreenViewModel: SignInScreenViewModel = hiltViewModel()
  val routineScreenViewmodel: RoutineScreenViewModel = hiltViewModel()
  val workoutScreenViewModel: WorkoutViewModel = hiltViewModel()
  val workoutListScreenViewmodel: WorkoutListScreenViewmodel = hiltViewModel()

  val bottomNavItems = listOf(
    BottomNavItems(
      route = Screens.ROUTINE_SCREEN.name,
      icon = painterResource(R.drawable.ic_home_24),
      label = stringResource(R.string.home_label)
    ),
    BottomNavItems(
      route = Screens.PROGRESS_SCREEN.name,
      icon = painterResource(R.drawable.ic_routine_outlined_24),
      label = stringResource(R.string.progress_label)
    ),
    BottomNavItems(
      route = Screens.PROFILE_SCREEN.name,
      icon = painterResource(R.drawable.ic_profile_placeholder),
      label = stringResource(R.string.profile_label)
    ),
  )

  Scaffold(
    bottomBar = {
      if (navController.currentBackStackEntryAsState().value?.destination?.route == Screens.ROUTINE_SCREEN.name) {
        AppBottomBar(navController, bottomNavItems)
      }
    },
    content = { innerPadding ->
      NavHost(
        navController = navController,
        startDestination = startNavigation,
        modifier = Modifier.padding(innerPadding)
      ) {
        composable(route = Screens.ONBOARDING_SCREEN.name) {
          OnboardingScreen(navController)
        }

        composable(route = Screens.SIGNUP_SCREEN.name) {
          SignUpScreen(signUpScreenViewModel, navController)
        }

        composable(route = Screens.SIGNIN_SCREEN.name) {
          SignInScreen(signInScreenViewModel, navController)
        }

        composable(route = Screens.ROUTINE_SCREEN.name) {
          RoutineScreen(navController, routineScreenViewmodel)
        }

        composable(route = Screens.PROFILE_SCREEN.name) {
          ProfileScreen()
        }

        composable(route = Screens.PROGRESS_SCREEN.name) {
          ProgressScreen()
        }

        composable(route = Screens.SETTING_SCREEN.name) {
          SettingScreen()
        }
        composable(route = Screens.WORKOUT_LIST_SCREEN.name) {
          WorkoutListScreen(workoutListScreenViewmodel, navController)
        }

        composable(
          route = "${Screens.WORKOUT_SCREEN.name}?workoutId={workoutId}",
          arguments = listOf(
            navArgument("workoutId") {
              type = NavType.IntType
            }
          )
        ) { navBackStackEntry ->
          val workoutId = navBackStackEntry.arguments?.getInt("workoutId") ?: throw IllegalStateException("workoutId não encontrado nos argumentos de navegação")
          WorkoutScreen(workoutId, navController, workoutScreenViewModel)
        }
      }
    }
  )


}