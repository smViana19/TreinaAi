package com.samuel.treinaiappcompose.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.theme.AppTheme

@Composable
fun HomeScreen() {
  BackHandler { }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
  ) {
    TopAppBarTemporary()
  }
}

@Composable
fun TopAppBarTemporary(userName: String = "Samuel") {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .background(
        MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
      )
      .padding(16.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "Olá, $userName 👋",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
              text = "Vamos treinar hoje?",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
            )
          }
        }

        Row {
          IconButton(onClick = { /* Ação de Notificação */ }) {
            Icon(
              painter = painterResource(R.drawable.ic_notification_24),
              contentDescription = stringResource(R.string.notification_icon_description),
              tint = MaterialTheme.colorScheme.onSecondary,
              modifier = Modifier.size(28.dp)
            )
          }
          IconButton(onClick = { /* Ação de Configurações */ }) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = "Configurações",
              tint = MaterialTheme.colorScheme.onSecondary,
              modifier = Modifier.size(28.dp)
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
  AppTheme {
    HomeScreen()
  }
}

@Preview
@Composable
private fun TopAppBarTemporaryPreview() {
  AppTheme {
    TopAppBarTemporary()
  }
}