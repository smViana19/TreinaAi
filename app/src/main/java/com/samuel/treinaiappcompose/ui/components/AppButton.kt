package com.samuel.treinaiappcompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.ui.theme.AppTheme


@Composable
fun AppButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  isLoading: Boolean = false,
  text: String? = null,
  icon: (@Composable (() -> Unit))? = null,
  //trail icon
  //leading icon
  textColor: Color = Color.White,
  enabled: Boolean = true,
  shape: Shape = ButtonDefaults.shape,
  colors: ButtonColors = ButtonDefaults.buttonColors(),
  elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
  border: BorderStroke? = null,
  fontSize: TextUnit = TextUnit.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  iconSpacing: Dp = 8.dp
) {
  val alpha by animateFloatAsState(if (isLoading) 0.5f else 1f, label = "")
  val buttonColors = if (enabled) {
    colors
  } else {
    ButtonDefaults.buttonColors(
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
      disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    )
  }
  Button(
    modifier = modifier.alpha(alpha),
    onClick = {
      if (!isLoading) {
        onClick()
      }
    },
    enabled = enabled && !isLoading,
    shape = shape,
    colors = buttonColors,
    elevation = elevation,
    border = border,
    contentPadding = contentPadding
  ) {
    if (isLoading) {
      CircularProgressIndicator(
        modifier = modifier.size(24.dp),
        color = textColor,
        strokeWidth = 2.dp
      )
    } else {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        when {
          text != null -> {
            Text(
              text = text,
              color = textColor,
              fontSize = fontSize,
              fontStyle = fontStyle,
              fontWeight = fontWeight,
              fontFamily = fontFamily
            )
          }

          icon != null -> {
            icon()
          }
        }
      }
    }
  }
}

@Composable
fun DefaultAppButton(
  onClick: () -> Unit,
  text: String,
  shape: Shape = RoundedCornerShape(12.dp),

) {
  AppButton(
    modifier = Modifier.fillMaxWidth(),
    onClick = {
      onClick()
    },
    shape = shape,
    colors = ButtonColors(
      containerColor = Color(0xFF111827),
      contentColor = Color.White,
      disabledContainerColor = Color(0xFF111827),
      disabledContentColor = Color.White
    ),
    text = text,
    
  )
}

@Preview(showBackground = true)
@Composable
private fun AppButtonPreview() {
  AppTheme {
    var isLoading by remember { mutableStateOf(false) }
    AppButton(
      onClick = {},
      isLoading = isLoading,
      text = "Basic button"
    )
  }

}

@Preview(showBackground = true)
@Composable
private fun DefaultAppButtonPreview() {
  AppTheme {
    Row (
      modifier = Modifier
        .width(327.dp)
        .padding(24.dp)
    ){
      DefaultAppButton(
        onClick = {},
        text = "Create Account",
      )
    }

  }
}
