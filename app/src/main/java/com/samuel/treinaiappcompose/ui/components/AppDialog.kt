package com.samuel.treinaiappcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.state.DialogState
import com.samuel.treinaiappcompose.ui.state.DialogType
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography

@Composable
fun AppDialog(
  modifier: Modifier = Modifier,
  state: DialogState
) {
  if (state.open) {
    Dialog(
      onDismissRequest = state.onDismiss,
    ) {
      Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.background
        ),
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          when (state.type) {
            DialogType.SUCCESS -> LottieAnimation(
              modifier = modifier.size(72.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success_animation)).value,
            )

            DialogType.ERROR -> LottieAnimation(
              modifier = modifier.size(72.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_animation)).value,
            )

            DialogType.ALERT -> LottieAnimation(
              modifier = modifier.size(72.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alert_animation)).value,
            )

            else -> {}
          }

          Spacer(modifier = Modifier.height(8.dp))

          when (state.type) {
            DialogType.SUCCESS -> Text(
              text = stringResource(R.string.title_success),
              style = Typography.titleMedium,
              color = MaterialTheme.colorScheme.primary
            )

            DialogType.ERROR -> Text(
              text = stringResource(R.string.title_error),
              style = Typography.titleMedium,
              color = MaterialTheme.colorScheme.primary
            )

            DialogType.ALERT -> Text(
              text = stringResource(R.string.title_alert),
              style = Typography.titleMedium,
              color = MaterialTheme.colorScheme.primary
            )

            else -> {}
          }
          Spacer(modifier = Modifier.height(8.dp))

          if (state.msg != null) {
            Text(
              text = state.msg,
              style = Typography.titleSmall,
              color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
          } else if (state.msgResId != null) {
            Text(
              text = stringResource(state.msgResId),
              style = Typography.titleSmall,
              color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
          }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
          ) {
            state.dismissButton?.let {
              TextButton(onClick = state.onDismiss) {

              }
              Spacer(modifier = Modifier.width(8.dp))
            }

            AppButton(
              onClick = {
                state.onConfirm()
              },
              text = state.confirmButton,
              shape = RoundedCornerShape(4.dp),
              colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
              )
            )
          }
        }
      }
    }
  }
}


@Composable
fun SuccessDialog(
  modifier: Modifier = Modifier
) {
  AppDialog(
    state = DialogState(

    ),

    )
}

@Composable
fun ErrorDialog(modifier: Modifier = Modifier) {

}

@Composable
fun AlertDialog(modifier: Modifier = Modifier) {

}

@Composable
fun FormDialog(modifier: Modifier = Modifier) {
  
}

@Preview
@Composable
private fun AppDialogPreview() {
  AppTheme {
    AppDialog(
      state = DialogState(
        open = true,
        type = DialogType.ERROR,
        msgResId = R.string.title_error
      )
    )
  }
}