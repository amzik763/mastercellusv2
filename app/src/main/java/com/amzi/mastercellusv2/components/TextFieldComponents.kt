package com.android.updatedsoftstarter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LoginInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    iconResId: Int,
    maxLength: Int,
    keyboardOptions: KeyboardOptions

) {
    val limitedText = text.take(maxLength)
  OutlinedTextField(value = text,
      onValueChange = {val newText = it.take(maxLength)
            onTextChange(newText)},
      maxLines = maxLine,
      label = { Text(text = label) },
      keyboardOptions = keyboardOptions,
      leadingIcon = {
          Icon(
              painter = painterResource(id = iconResId),
              contentDescription = "Password Icon",
              modifier = Modifier
                  .size(24.dp)
                  .fillMaxWidth()
          )
      }
  )
}
