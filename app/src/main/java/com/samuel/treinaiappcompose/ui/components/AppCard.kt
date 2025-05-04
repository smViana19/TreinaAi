package com.samuel.treinaiappcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppCard(
  modifier: Modifier = Modifier,
  title: String,
  subtitle: String? = null,
  onClick: () -> Unit
) {
  Card(
    onClick = onClick,
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
      disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    ),
    elevation = CardDefaults.cardElevation(4.dp),
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(start = 8.dp)) {
          Text(text = title, style = MaterialTheme.typography.titleMedium)
          subtitle?.let {
            Text(text = it, style = MaterialTheme.typography.bodySmall)
          }
        }
      }
    }
  }
}