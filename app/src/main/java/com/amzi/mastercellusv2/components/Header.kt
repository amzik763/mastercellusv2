package com.amzi.mastercellusv2.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.ui.theme.lightGrey
import com.amzi.mastercellusv2.utility.mFont
import com.example.demo.ui.theme.Grey
import com.example.demo.ui.theme.cgrey
import com.example.demo.ui.theme.darkestGrey

//@Preview
@Composable
fun Header(text1 : String, text2 : String){
    Column{
        Icon(
            painter = painterResource(id = R.drawable.skaio),
            contentDescription = "Skaio Icon",
            tint = Color.Unspecified,
            modifier = Modifier.size(width = 150.dp, height = 55.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = text1,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = text2,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Grey,
                fontFamily = mFont.outfitRegular
            ),
            modifier = Modifier.padding(end = 46.dp)
        )
    }
}