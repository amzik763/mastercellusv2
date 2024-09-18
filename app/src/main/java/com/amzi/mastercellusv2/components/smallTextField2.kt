package com.amzi.mastercellusv2.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallInputText2(
//    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
//    iconResId: Int,
    maxLength: Int,
    keyboardOptions: KeyboardOptions

) {

    val widthDP = LocalConfiguration.current.screenWidthDp.dp

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(value = text,
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
        modifier = Modifier
            .height(55.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 12.sp
        ),
        shape = RoundedCornerShape(16.dp)
    )
}
