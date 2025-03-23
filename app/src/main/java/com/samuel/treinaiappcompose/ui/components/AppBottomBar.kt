package com.samuel.treinaiappcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography

data class BottomNavItems(
  val route: String,
  val icon: Painter,
  val label: String,
)

@Composable
fun AppBottomBar(
  navController: NavController,
  items: List<BottomNavItems>
) {
  val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    tonalElevation = 1.dp,
    modifier = Modifier
      .fillMaxWidth()
      .height(60.dp)
  ) {

    items.forEach { item ->
      NavigationBarItem(
        modifier = Modifier.padding(0.dp),
        selected = currentRoute == item.route,
        onClick = {
          if (currentRoute != item.route) {
            navController.navigate(item.route)
          }
        },
        icon = {
          Icon(painter = item.icon, item.label, modifier = Modifier.padding(end = 4.dp))
        },
        label = {
          Text(
            text = item.label,
            color = if (currentRoute == item.route) MaterialTheme.colorScheme.secondary else  MaterialTheme.colorScheme.onBackground,
            style = Typography.labelMedium
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.secondary,
          unselectedIconColor = MaterialTheme.colorScheme.onBackground,
          selectedTextColor = MaterialTheme.colorScheme.secondary,
          unselectedTextColor = MaterialTheme.colorScheme.onBackground,
          indicatorColor = Color.Transparent
        )
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun AppBottomBarPreview() {
  AppTheme {
    val navController = rememberNavController()
    val items = listOf(
      BottomNavItems(
        route = "inicio",
        icon = painterResource(R.drawable.ic_home_24),
        label = "Inicio"
      ),
      BottomNavItems(
        route = "agendamentos",
        icon = painterResource(R.drawable.ic_routine_outlined_24),
        label = "Progresso"
      ),
      BottomNavItems(
        route = "perfil",
        icon = painterResource(R.drawable.ic_person_24),
        label = "Perfil"
      )
    )


    AppBottomBar(navController, items)
  }
}