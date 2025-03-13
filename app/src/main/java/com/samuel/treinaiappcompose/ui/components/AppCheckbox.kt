package com.samuel.treinaiappcompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.ui.theme.AppTheme

@Composable
fun AppCheckbox(modifier: Modifier = Modifier) {
  var checked by remember { mutableStateOf(false) }
  Checkbox(
    modifier = modifier.size(20.dp),
    checked = checked,
    onCheckedChange = { checked = !checked },
    colors = CheckboxDefaults.colors(
      checkedColor = MaterialTheme.colorScheme.secondary,
      uncheckedColor = MaterialTheme.colorScheme.primary
    )
  )
}

@Composable
fun CustomCheckbox(
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  val scale by animateFloatAsState(targetValue = if (checked) 1f else 0.8f, label = "")

  Box(
    modifier = Modifier
      .size(30.dp)
      .border(2.dp, if (checked) Color.Blue else Color.Gray, CircleShape)
      .background(if (checked) Color.Blue else Color.Transparent, CircleShape)
      .clickable { onCheckedChange(!checked) }
      .padding(4.dp),
    contentAlignment = Alignment.Center
  ) {
    if (checked) {
      Box(
        modifier = Modifier
          .size(16.dp)
          .scale(scale)
          .background(Color.White, CircleShape)
      )
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun AppCheckboxPreview() {
  AppTheme(darkTheme = false) {
    AppCheckbox()
  }
}

@Preview
@Composable
private fun CustomCheckboxPreview() {
  var checked by remember { mutableStateOf(false) }
  AppTheme {
    CustomCheckbox(
      checked = checked,
      onCheckedChange = { checked = it}
    )
  }
  
}