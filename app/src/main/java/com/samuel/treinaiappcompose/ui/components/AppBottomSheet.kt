import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.components.AppOutlinedTextField
import com.samuel.treinaiappcompose.ui.components.DefaultAppButton
import com.samuel.treinaiappcompose.ui.theme.AppTheme
import com.samuel.treinaiappcompose.ui.theme.Typography

@Composable
fun AppBottomSheet(
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit,
  title: String,
  content: (@Composable ColumnScope.() -> Unit)? = {}

) {
  val focusManager = LocalFocusManager.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
      .pointerInput(Unit) {
        detectTapGestures {
          focusManager.clearFocus()
        }
      },
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically

    ) {
      Text(
        text = title,
        style = Typography.titleLarge
      )
    }
    HorizontalDivider(
      modifier = Modifier
        .padding(vertical = 14.dp),
    )
    if (content != null) {
      content()
    }
  }
}

@Composable
fun AppBottomSheetTextFields(
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit,
  title: String,
  formContent: @Composable ColumnScope.() -> Unit
) {
  AppBottomSheet(
    onDismiss = onDismiss,
    title = title,
    content = {
      formContent()
      }
  )

}


@Preview
@Composable
private fun AppBottomSheetTextFieldsPreview() {
  AppTheme {
    AppBottomSheetTextFields(
      onDismiss = {},
      title = stringResource(R.string.exercise_bottom_sheet_title),
      formContent = {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "" ,//TODO viewmodel...
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
          Spacer(modifier = Modifier.height(8.dp))
        }
      }
    )
  }

}
@Preview(showBackground = true)
@Composable
private fun AppBottomSheetPreview() {
  AppTheme {
    AppBottomSheet(
      onDismiss = {},
      title = "Teste",
      content = null
      )

  }

}