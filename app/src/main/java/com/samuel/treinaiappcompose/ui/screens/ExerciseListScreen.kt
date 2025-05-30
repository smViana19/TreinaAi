package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.data.mocks.ExerciseDaoMock
import com.samuel.treinaiappcompose.data.repository.ExerciseRepository
import com.samuel.treinaiappcompose.ui.components.AppButton
import com.samuel.treinaiappcompose.ui.components.AppCardGridVertical
import com.samuel.treinaiappcompose.ui.components.AppCardImage
import com.samuel.treinaiappcompose.ui.components.AppTextField
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.viewmodels.ExerciseListScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
  workoutId: Int,
  navController: NavController,
  viewModel: ExerciseListScreenViewModel
) {
  val isGridLayout = remember { mutableStateOf(false) }

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
//          viewModel.openDialog()
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
            LayoutGrid()
          } else {
            LayoutColumn()
          }
        }

      }
    }
  )
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


    AppTextField(
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
private fun LayoutGrid() {
  val items = listOf(
    "Supino",
    "Pull over",
    "Agachamento",
    "Rosca Direta",
    "Tríceps Testa",
    "Elevação Lateral"
  )
  var isItemFavorite by remember { mutableStateOf(false) }
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.padding(horizontal = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento horizontal ENTRE os itens
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(items) { itemName ->
      AppCardGridVertical(
        onClick = {},
        title = itemName,
        onFavorite = { isItemFavorite = !isItemFavorite },
        favorite = isItemFavorite
      )
    }
  }
}

@Composable
private fun LayoutColumn() {
  val items = listOf("Supino", "Pull over", "Agachamento")
  var favorite = false
  LazyColumn(
    modifier = Modifier.padding(horizontal = 16.dp)
  ) {
    items(items) { i ->
      AppCardImage(onClick = {}, title = i, onFavorite = {
        favorite = !favorite
      })

    }
  }

}


@Preview(showBackground = true)
@Composable
private fun ExerciseListScreenPreview() {
  val navController = rememberNavController()
  val exerciseDao = ExerciseDaoMock()
  val exerciseRepository = ExerciseRepository(exerciseDao)
  val viewModel = ExerciseListScreenViewModel(exerciseRepository)
  AppTheme {
    ExerciseListScreen(1, navController, viewModel)
  }
}