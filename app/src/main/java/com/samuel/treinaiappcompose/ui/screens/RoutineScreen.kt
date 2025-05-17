package com.samuel.treinaiappcompose.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.navigation.Screens
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography
import com.samuel.treinaiappcompose.ui.viewmodels.RoutineScreenViewModel

@Composable
fun RoutineScreen(
  navController: NavController,
  viewmodel: RoutineScreenViewModel = hiltViewModel()
) {

  LaunchedEffect(Unit) {
    viewmodel.getUsername()
  }
  val userName = viewmodel.username.value
  BackHandler { }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(horizontal = 16.dp)
  ) {
    Header(navController = navController, userName = userName)
    MyWorkouts(navController = navController)
  }
}

@Composable
fun Header(
  userName: String,
  navController: NavController,
  profileImage: Painter? = null
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(110.dp)
      .background(MaterialTheme.colorScheme.background)
      .padding(vertical = 12.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        if (profileImage != null) {
          Image(
            painter = profileImage,
            contentDescription = "Foto de perfil",
            modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
          )
        } else {
          Box(
            modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.secondary)
          ) {
            Text(
              text = if (userName.isNotEmpty()) userName.first().toString() else "?",
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSecondary,
              modifier = Modifier.align(Alignment.Center)
            )
          }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
          Text(
            text = "Olá, $userName 👋",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
          )
          Text(
            text = "Vamos treinar hoje?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
          )
        }
      }

      Row {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
          onClick = {
            navController.navigate(Screens.SETTING_SCREEN.name)
          },
        ) {
          Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Configurações",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(26.dp)
          )
        }
      }
    }
  }
}

@Composable
fun MyWorkouts(navController: NavController) {
  Text(
    text = "Meus treinos",
    style = Typography.titleLarge,
    color = MaterialTheme.colorScheme.primary
  )

  Text(
    text = "Gerencie e acompanhe seus treinos facilmente.",
    style = Typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurfaceVariant
  )

  Spacer(modifier = Modifier.height(24.dp))

  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    DefaultAppButton(
      onClick = {
        navController.navigate(Screens.WORKOUT_LIST_SCREEN.name)
      },
      text = "Treino rápido",
      leadingIcon = {
        Icon(painter = painterResource(R.drawable.ic_add_24), contentDescription = "")
      }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
      DefaultAppButton(
        modifier = Modifier.weight(1f),
        onClick = {},
        text = "Nova rotina",
        leadingIcon = {
          Icon(
            painter = painterResource(R.drawable.ic_routine_outlined_24),
            contentDescription = ""
          )
        }
      )
      Spacer(modifier = Modifier.width(8.dp))
      DefaultAppButton(
        modifier = Modifier.weight(1f),
        onClick = {},
        text = "Buscar rotinas",
        leadingIcon = {
          Icon(painter = painterResource(R.drawable.ic_search_24), contentDescription = "")
        }
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
  AppTheme {
    val navController = rememberNavController()
    RoutineScreen(navController)
  }
}
