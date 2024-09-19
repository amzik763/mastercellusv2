package com.amzi.mastercellusv2.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.example.homeapplication.ui.theme.lightOrange

@Composable
fun PasswordTextFiled(
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

    val keyboardController = LocalSoftwareKeyboardController.current

    var passwordVisible by remember { mutableStateOf(false) }

    val lockIcon = if (passwordVisible){
        painterResource(id = R.drawable.lock_open)
    } else{
        painterResource(id = R.drawable.lock_close)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = lightOrange,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.DarkGray
        ),
        value = text,
      onValueChange = {val newText = it.take(maxLength)
            onTextChange(newText)},
      maxLines = maxLine,
      label = { Text(text = label,
          style = TextStyle(
              fontSize = 12.sp
          ),) },
        keyboardOptions = keyboardOptions,
        keyboardActions =  KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 12.sp
        ),
      trailingIcon = {
          Icon(
              painter = lockIcon,
              contentDescription = "Password Icon",
              modifier = Modifier
                  .size(19.dp)
                  .fillMaxWidth()
                  .clickable {
                        passwordVisible = !passwordVisible
                  }
          )
      },
        shape = RoundedCornerShape(16.dp)
    )
}
