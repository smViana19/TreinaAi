package com.samuel.treinaiappcompose.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.AppCard
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutScreenViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
  viewModel: WorkoutScreenViewmodel,
  navController: NavController
) {
  val items = listOf(
    "Treino 1",
    "Treino 2",
    "Treino 3",
  )

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
        onClick = { /* ação do botão */ },
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
          items(items) { item ->
            AppCard(title = item, onClick = {})
          }
        }
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun WorkoutScreenPreview() {
  val navController = rememberNavController()
  val viewModel = WorkoutScreenViewmodel()
  AppTheme {
    WorkoutScreen(viewModel, navController)
  }
}