package com.cti.softStarter.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WIInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
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
  )
}
