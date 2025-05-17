package com.samuel.treinaiappcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.data.mocks.WorkoutDaoMock
import com.samuel.treinaiappcompose.data.repository.WorkoutRepository
import com.samuel.treinaiappcompose.ui.components.AppCard
import com.samuel.treinaiappcompose.ui.components.AppTextField
import com.samuel.treinaiappcompose.ui.components.FormDialog
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.state.DialogState
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutListScreenViewmodel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListScreen(
  viewModel: WorkoutListScreenViewmodel = hiltViewModel(),
  navController: NavController
) {
  val context = LocalContext.current
  LaunchedEffect(Unit) {
    viewModel.getAllWorkouts()
    viewModel.toastMessage.collectLatest { messageId ->
      Toast.makeText(context, context.getString(messageId), Toast.LENGTH_SHORT).show()
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {},
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(Screens.ROUTINE_SCREEN.name)
          }) {
            Icon(
              painter = painterResource(R.drawable.ic_back_24),
              contentDescription = stringResource(R.string.back_icon)
            )
          }
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          viewModel.openDialog()
        },
        containerColor = MaterialTheme.colorScheme.secondary
      ) {
        Icon(
          painter = painterResource(R.drawable.ic_add_24),
          contentDescription = stringResource(R.string.back_icon),
        )
      }
    },
    content = { paddingValues ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(color = MaterialTheme.colorScheme.background)
          .padding(paddingValues)
          .padding(16.dp),
      ) {
        Column(
          modifier = Modifier
        ) {
          Text(
            text = stringResource(R.string.workout_title),
            color = MaterialTheme.colorScheme.primary,
            style = Typography.titleLarge
          )
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = stringResource(R.string.workout_subtitle),
            color = MaterialTheme.colorScheme.tertiary,
            style = Typography.titleSmall,
          )
        }
        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
          modifier = Modifier
            .weight(1f),
          contentPadding = PaddingValues(bottom = 80.dp)
        ) {
          items(viewModel.workouts.value) { item ->
            AppCard(title = item.name, onClick = {
              //navigate
            })
          }
        }
      }
      if (viewModel.dialogState.value.open) {
        FormDialog(
          title = stringResource(R.string.workout_dialog_title),
          state = DialogState(
            open = true,
            onConfirm = {
              viewModel.insertWorkout()
            },
            onDismiss = {
              viewModel.closeDialog()
            }
          ),
          formContent = {
            AppTextField(
              value = viewModel.name.value,
              onValueChange = { newValue ->
                viewModel.name.value = newValue
              },
              placeholder = "Digite o nome do treino"
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppTextField(
              value = viewModel.description.value,
              onValueChange = { newValue ->
                viewModel.description.value = newValue
              },
              placeholder = "Digite a descrição"
            )

          }
        )
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun WorkoutScreenPreview() {
  val navController = rememberNavController()
  val workoutDao = WorkoutDaoMock()
  val repository = WorkoutRepository(workoutDao)
  val viewModel = WorkoutListScreenViewmodel(repository)
  AppTheme {
    WorkoutListScreen(viewModel, navController)
  }
}