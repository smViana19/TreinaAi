package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import com.samuel.treinaiappcompose.data.mocks.WorkoutDaoMock
import com.samuel.treinaiappcompose.data.repository.WorkoutRepository
import com.samuel.treinaiappcompose.ui.components.AppButton
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
  workoutId: Int,
  navController: NavController,
  viewModel: WorkoutViewModel
) {
  LaunchedEffect(Unit) {
    viewModel.getWorkout(workoutId)
  }
  Scaffold(
    topBar = {
      TopAppBar(
        title = {},
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(Screens.WORKOUT_LIST_SCREEN.name)
          }) {
            Icon(
              painter = painterResource(R.drawable.ic_back_24),
              tint = MaterialTheme.colorScheme.primary,
              contentDescription = stringResource(R.string.back_icon)
            )
          }
        },
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          navController.navigate("${Screens.EXERCISE_SCREEN.name}?workoutId=${workoutId}")
        },
        containerColor = MaterialTheme.colorScheme.secondary
      ) {
        Icon(
          painter = painterResource(R.drawable.ic_add_24),
          contentDescription = stringResource(R.string.back_icon),
        )
      }
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier.padding(innerPadding)
      ) {
        Header(viewModel.workoutName.value)
        WorkoutInfo(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        ExerciseList(navController = navController)
      }
    }
  )
}

@Composable
private fun Header(
  workoutName: String,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp)
  ) {
    Text(
      text = workoutName,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.primary,
    )
  }

}

@Composable
private fun WorkoutInfo(
  navController: NavController
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Column {
      Row {
        Icon(
          painter = painterResource(R.drawable.ic_calories_24),
          tint = MaterialTheme.colorScheme.secondary,
          contentDescription = stringResource(R.string.calories_icon)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column {
          Text(
            text = stringResource(R.string.workout_calories_label),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
          )
          Text(
            "- kcal",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
          )

        }
      }
    }
    Column {
      Row {
        Icon(
          painter = painterResource(R.drawable.ic_timer_24),
          tint = MaterialTheme.colorScheme.secondary,
          contentDescription = stringResource(R.string.duration_icon)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column {
          Text(
            text = stringResource(R.string.workout_duration_label),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
          )
          Text(
            "- min",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
          )

        }
      }
    }
    Column {
      Row {
        Icon(
          painter = painterResource(R.drawable.ic_dumbbel_24),
          tint = MaterialTheme.colorScheme.secondary,
          contentDescription = stringResource(R.string.dumbbel_icon)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column {
          Text(
            text = stringResource(R.string.workout_total_weight_label),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
          )
          Text(
            "- kg",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
          )
        }

      }
    }
  }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    DefaultAppButton(
      onClick = {
        navController.navigate(Screens.SIGNUP_SCREEN.name)
      },
      text = stringResource(R.string.workout_start)
    )
  }
}

@Composable
private fun ExerciseList(
  navController: NavController,
  exercises: List<ExerciseModel> = emptyList()
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Text(
      text = stringResource(R.string.workout_exercise_list_tittle),
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.primary,
    )
  }
  if (exercises.isEmpty()) {
    EmptyExercise()
  } else {

  }
}

@Composable
private fun EmptyExercise() {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_dumbbel_24),
      tint = MaterialTheme.colorScheme.outline,
      contentDescription = stringResource(R.string.dumbbel_icon)
    )
    Text(
      text = stringResource(R.string.workout_exercise_label),
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.primary,
    )
    Text(
      text = stringResource(R.string.workout_exercise_alert_empty_list),
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.tertiary,
    )

  }
}

@Preview(showBackground = true)
@Composable
private fun WorkoutScreenPreview() {
  val navController = rememberNavController()
  val workoutDao = WorkoutDaoMock()
  val workoutRepository = WorkoutRepository(workoutDao)
  val viewModel = WorkoutViewModel(workoutRepository)
  AppTheme {
    WorkoutScreen(1, navController, viewModel)
  }
}
