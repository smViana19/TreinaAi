package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.viewmodels.WorkoutViewModel

@Composable
fun WorkoutScreen(
  workoutId: Int,
  navController: NavController,
  viewModel: WorkoutViewModel
) {
//  Scaffold(
//    topBar = {
//      WorkoutScreenTopAppBar(
//        workoutName = workoutDetails?.name ?: "Detalhes do Treino",
//        onNavigateBack = { navController.popBackStack() }
//      )
//    },
//    floatingActionButton = {
//      FloatingActionButton(onClick = {
//        // TODO: Navegar para tela de adicionar exercício ou mostrar um dialog
//        // viewModel.onAddExerciseClicked() // Exemplo de evento no ViewModel
//      }) {
//        Icon(Icons.Filled.Add, contentDescription = "Adicionar Exercício")
//      }
//    }
//  ) { paddingValues ->
//    if (isLoading) {
//      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        CircularProgressIndicator()
//      }
//    } else if (workoutDetails == null) {
//      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text("Treino não encontrado ou erro ao carregar.")
//      }
//    } else {
//      WorkoutDetailsContent(
//        modifier = Modifier.padding(paddingValues),
//        workoutDetails = workoutDetails!!, // Sabemos que não é nulo aqui
//        onExerciseClicked = { exerciseId ->
//          // TODO: Navegar para detalhes do exercício ou editar
//          // viewModel.onExerciseSelected(exerciseId)
//        }
//      )
//    }
//  }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun WorkoutScreenTopAppBar(
//  workoutName: String,
//  onNavigateBack: () -> Unit
//) {
//  TopAppBar(
//    title = { Text(workoutName) },
//    navigationIcon = {
//      IconButton(onClick = onNavigateBack) {
//        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
//      }
//    },
//    // actions = { /* Ações como editar nome do treino, etc. */ }
//  )
//}

//@Composable
//private fun WorkoutDetailsContent(
//  modifier: Modifier = Modifier,
//  workoutDetails: WorkoutDetails,
//  onExerciseClicked: (String) -> Unit
//) {
//  Column(
//    modifier = modifier
//      .fillMaxSize()
//      .padding(16.dp)
//  ) {
//    // Nome do Treino (já está na TopAppBar, mas pode ser repetido ou ter mais detalhes)
//    Text(
//      text = workoutDetails.name,
//      style = MaterialTheme.typography.headlineMedium,
//      modifier = Modifier.padding(bottom = 8.dp)
//    )
//
//    workoutDetails.description?.let {
//      Text(
//        text = it,
//        style = MaterialTheme.typography.bodyMedium,
//        modifier = Modifier.padding(bottom = 16.dp)
//      )
//    }
//
//    Text(
//      text = "Exercícios:",
//      style = MaterialTheme.typography.titleMedium,
//      modifier = Modifier.padding(bottom = 8.dp)
//    )
//
//    if (workoutDetails.exercises.isEmpty()) {
//      Text("Nenhum exercício adicionado ainda.")
//    } else {
//      ExerciseList(
//        exercises = workoutDetails.exercises,
//        onExerciseClicked = onExerciseClicked
//      )
//    }
//  }
//}

//@Composable
//private fun ExerciseList(
//  exercises: List<Exercise>,
//  onExerciseClicked: (String) -> Unit,
//  modifier: Modifier = Modifier
//) {
//  LazyColumn(modifier = modifier) {
//    items(exercises, key = { it.id }) { exercise ->
//      ExerciseItem(
//        exercise = exercise,
//        onClick = { onExerciseClicked(exercise.id) }
//      )
//      Divider()
//    }
//  }
//}


@Preview
@Composable
private fun WorkoutScreenPreview() {
  val navController = rememberNavController()
  val viewModel = WorkoutViewModel()
  AppTheme {
    WorkoutScreen(1, navController, viewModel)
  }
}