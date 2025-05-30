package com.samuel.treinaiappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.samuel.treinaiappcompose.R

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
      .heightIn(50.dp)
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


@Composable
fun AppCardImage(
  modifier: Modifier = Modifier,
  title: String,
  subtitle: String? = null,
  imageUrl: String? = "https://i.pinimg.com/originals/9d/52/7b/9d527b5d3e90317734ff3325e88e9c18.gif",
  description: String? = "",
  onFavorite: () -> Unit,
  favorite: Boolean = false,
  onClick: () -> Unit
) {
  Card(
    onClick = onClick,
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.onSurface,
      contentColor = MaterialTheme.colorScheme.primary,
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
      disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    ),
    elevation = CardDefaults.cardElevation(4.dp),
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp)
      .heightIn(min = 120.dp)
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      if (!imageUrl.isNullOrEmpty()) {
        AsyncImage(
          modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(4.dp)),
          model = imageUrl,
          contentDescription = description,
          contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
      } else {
        Box(
          modifier = Modifier
            .size(80.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(40.dp)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_dumbbel_24), // Ícone de placeholder
            contentDescription = "Placeholder Image",
            tint = MaterialTheme.colorScheme.onPrimary
          )
        }
      }
      Column(modifier = Modifier.padding(start = 8.dp), verticalArrangement = Arrangement.Center) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        subtitle?.let {
          Text(text = it, style = MaterialTheme.typography.bodySmall)
        }
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
      ) {
        IconButton(onClick = onFavorite) {
          if (!favorite) {
            Icon(
              painter = painterResource(R.drawable.ic_star_unselected_24),
              tint = Color.Yellow,
              contentDescription = stringResource(R.string.star_unfavorite_icon)
            )

          } else {
            AsyncImage(
              modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp)),
              model = imageUrl,
              contentDescription = description,
              contentScale = ContentScale.Crop
            )
          }
        }
      }

    }
  }
}

@Composable
fun AppCardGridVertical(
  modifier: Modifier = Modifier,
  title: String,
  subtitle: String? = null,
  imageUrl: String? = "",
  description: String? = "",
  onFavorite: () -> Unit,
  favorite: Boolean = false,
  onClick: () -> Unit
) {
  Card(
    onClick = onClick,
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
      disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    ),
    elevation = CardDefaults.cardElevation(4.dp),
    modifier = modifier
      .fillMaxWidth()
      .width(200.dp)
      .padding(vertical = 4.dp)
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {

      if (!imageUrl.isNullOrEmpty()) {
        AsyncImage(
          model = imageUrl,
          contentDescription = description,
          contentScale = ContentScale.Crop, // Crop para preencher o espaço
          modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Dê uma altura fixa ou use aspect ratio
            // .aspectRatio(16f / 9f) // Alternativa para manter proporção
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)) // Arredondar só os cantos de cima da imagem
        )
      } else {
        // Placeholder ou imagem padrão se imageUrl for nula/vazia
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(MaterialTheme.colorScheme.tertiary) // Cor de placeholder
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_dumbbel_24), // Ícone de placeholder
            contentDescription = "Placeholder Image",
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.secondary
          )
        }
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 8.dp), // Ajuste o padding
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium,
          modifier = Modifier
            .weight(1f) // Permite que o texto ocupe o espaço disponível
            .padding(end = 8.dp), // Espaço antes do ícone
          maxLines = 2 // Evita que o título seja muito longo
        )
        IconButton(onClick = onFavorite) {
          Icon(
            painter = painterResource(
              if (favorite) R.drawable.ic_start_selected_24
              else R.drawable.ic_star_unselected_24
            ),
            tint = MaterialTheme.colorScheme.tertiary, // Ou MaterialTheme.colorScheme.tertiary para consistência de tema
            contentDescription = stringResource(
              if (favorite) R.string.star_favorite_icon
              else R.string.star_unfavorite_icon
            )
          )
        }
      }

    }
  }

}


