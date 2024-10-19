package com.amzi.mastercellusv2.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.utility.mFont
import com.example.homeapplication.ui.theme.blue
import com.example.homeapplication.ui.theme.darkBlue
import com.example.homeapplication.ui.theme.paleWhite


@Composable
fun SmallButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 50.dp,
    backgroundColor: Color = blue,
    contentColor: Color = paleWhite,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(0.dp),

        modifier = modifier
            .size(width = 50.dp, height = 24.dp)
    ) {
        Text(text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.outfitRegular,
            color = paleWhite,
            modifier = Modifier
                .padding(horizontal = 8.dp))
    }
}