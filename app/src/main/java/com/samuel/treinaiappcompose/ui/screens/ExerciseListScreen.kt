package com.samuel.treinaiappcompose.ui.screens

import AppBottomSheetTextFields
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.data.local.database.model.ExerciseModel
import com.samuel.treinaiappcompose.data.mocks.ExerciseDaoMock
import com.samuel.treinaiappcompose.data.repository.ExerciseRepository
import com.samuel.treinaiappcompose.ui.components.AppButton
import com.samuel.treinaiappcompose.ui.components.AppCardGridVertical
import com.samuel.treinaiappcompose.ui.components.AppCardImage
import com.samuel.treinaiappcompose.ui.components.AppOutlinedTextField
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.viewmodels.ExerciseListScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
  workoutId: Int,
  navController: NavController,
  viewModel: ExerciseListScreenViewModel
) {
  val isGridLayout = remember { mutableStateOf(false) }
  var showBottomSheet by remember { mutableStateOf(false) }
  val sheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = true,
    confirmValueChange = { true }
  )
  val scope = rememberCoroutineScope()

  LaunchedEffect(Unit) {

  }
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = stringResource(R.string.exercise_choose_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
          )
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate("${Screens.WORKOUT_SCREEN.name}?workoutId=${workoutId}")
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
          showBottomSheet = true
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
          .padding(paddingValues)
      ) {
        SearchInput(viewModel)
        Category(viewModel)
        HorizontalDivider()
        Filters(
          viewModel = viewModel,
          isGridLayout = isGridLayout.value,
          onSelectLayout = { isGridLayout.value = it })
        Column {
          if (isGridLayout.value) {
            LayoutGrid(viewModel.exercises.value)
          } else {
            LayoutColumn(viewModel.exercises.value)
          }
        }

      }
    }

  )
  if (showBottomSheet) {
    ModalBottomSheet(
      onDismissRequest = {
        showBottomSheet = false
      },
      sheetState = sheetState,
      containerColor = Color.White,
      dragHandle = {
        Spacer(
          modifier = Modifier
            .padding(bottom = 24.dp, top = 8.dp)
            .height(3.dp)
            .width(38.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0E0E0))
        )
      },
    ) {
      AppBottomSheetTextFields(
        onDismiss = {
          scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
              showBottomSheet = false
            }
          }
        },
        title = stringResource(R.string.exercise_bottom_sheet_title),
        formContent = {
          Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            AppOutlinedTextField(
              modifier = Modifier.fillMaxWidth(),
              value = "",//TODO viewmodel...
              onValueChange = {},
              placeholder = stringResource(R.string.placeholder_exercise_name)
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppOutlinedTextField(
              modifier = Modifier.fillMaxWidth(),
              value = "", //TODO viewmodel...
              onValueChange = {},
              placeholder = stringResource(R.string.placeholder_exercise_description)
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultAppButton(onClick = {}, text = stringResource(R.string.button_save))
            Spacer(modifier = Modifier.height(16.dp))
          }
        }
      )
    }
  }

}

@Composable
private fun SearchInput(
  viewModel: ExerciseListScreenViewModel
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current


    AppOutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = searchText,
      onValueChange = {
        searchText = it
        viewModel.filterExercises(searchText)
      },
      leadingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_search_24),
          contentDescription = stringResource(R.string.search_icon)
        )
      },
      placeholder = stringResource(R.string.exercise_find_placeholder),
      shape = RoundedCornerShape(30),
      trailingIcon = {
        if (searchText.isNotEmpty()) {
          IconButton(
            onClick = {
              searchText = ""
              viewModel.filterExercises(searchText)
              focusManager.clearFocus()
            }

          ) {
            Icon(
              painter = painterResource(R.drawable.ic_close_24),
              contentDescription = "Clear text"
            )
          }
        }
      },
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(
        onDone = { focusManager.clearFocus() }
      )

    )
  }
}

@Composable
private fun Category(
  viewModel: ExerciseListScreenViewModel
) {
  val aaa = listOf("Todos", "Grupos musculares", "Equipamentos")
  val searchText by remember { mutableStateOf(false) }
  LazyRow(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),

    ) {
    items(aaa) { category ->
      AppButton(
        modifier = Modifier.padding(4.dp),
        onClick = {

        },
        shape = RoundedCornerShape(4.dp),
        border = if (searchText) BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.secondary
        ) else BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = if (searchText) {
          ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
          )
        } else {
          ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
          )
        },
        text = category
      )

    }
  }
}

@Composable
private fun Filters(
  viewModel: ExerciseListScreenViewModel,
  isGridLayout: Boolean = false,
  onSelectLayout: (Boolean) -> Unit
) {

  val opcoes = listOf("Filtro A", "Filtro B", "Filtro C")
  var expandido by remember { mutableStateOf(false) }
  var selecionado by remember { mutableStateOf(opcoes[0]) }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Box {
      IconButton(onClick = { expandido = true }) {
        Icon(
          painter = painterResource(R.drawable.ic_filter_24),
          contentDescription = "Abrir filtro"
        )
      }
      DropdownMenu(
        expanded = expandido,
        onDismissRequest = { expandido = false }
      ) {
        opcoes.forEach { opcao ->
          DropdownMenuItem(
            text = {
              Text(
                text = opcao, style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
              )
            },
            onClick = {
              selecionado = opcao
              expandido = false
            }
          )
        }
      }
    }
    IconButton(onClick = { onSelectLayout(!isGridLayout) }) {
      if (isGridLayout) {
        Icon(
          painter = painterResource(R.drawable.ic_grade_layout_24),
          contentDescription = "Layout"
        )
      } else {
        Icon(
          painter = painterResource(R.drawable.ic_list_card_24),
          contentDescription = "Layout"
        )
      }
    }
  }


}

@Composable
private fun LayoutGrid(
  items: List<ExerciseModel>
) {
  var isItemFavorite by remember { mutableStateOf(false) }
  if (items.isNotEmpty()) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      modifier = Modifier.padding(horizontal = 8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(items) { i ->
        AppCardGridVertical(
          onClick = {},
          title = i.name,
          onFavorite = { isItemFavorite = !isItemFavorite },
          favorite = isItemFavorite
        )
      }
    }
  } else {
    EmptyExercises()
  }
}

@Composable
private fun LayoutColumn(
  items: List<ExerciseModel>
) {
  var favorite = false
  if (items.isNotEmpty()) {
    LazyColumn(
      modifier = Modifier.padding(horizontal = 16.dp)
    ) {
      items(items) { i ->
        AppCardImage(onClick = {}, title = i.name, onFavorite = {
          favorite = !favorite
        })
      }
    }
  } else {
    EmptyExercises()
  }
}

@Composable
private fun EmptyExercises() {
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
private fun ExerciseListScreenPreview() {
  val navController = rememberNavController()
  val exerciseDao = ExerciseDaoMock()
  val firebaseFirestoreMock = FirebaseFirestore.getInstance()
  val exerciseRepository = ExerciseRepository(exerciseDao, firebaseFirestoreMock)
  val viewModel = ExerciseListScreenViewModel(exerciseRepository)
  AppTheme {
    ExerciseListScreen(1, navController, viewModel)
  }
}