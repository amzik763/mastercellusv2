package com.amzi.mastercellusv2.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.ui.theme.blue
import com.amzi.mastercellusv2.utility.mFont
import com.example.demo.ui.theme.pureWhite

//@Preview
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
){
    Surface(
        color = blue,
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        border = BorderStroke(width = 1.dp, color = blue)
    ) {
        ClickableText(
            text = AnnotatedString(text),
            style = TextStyle(
                color = pureWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = mFont.outfitRegular,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            onClick = {
                    onClick()
            }
        )
    }
}