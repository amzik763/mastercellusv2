package com.amzi.mastercellusv2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.utility.mFont
import com.example.homeapplication.ui.theme.blue
import com.example.homeapplication.ui.theme.paleWhite


//@Preview
@Composable
fun ImageButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 50.dp,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = blue,
            contentColor = paleWhite
        ),
        contentPadding = PaddingValues(0.dp),

        modifier = modifier
            .size(width = 80.dp, height = 32.dp)
//            .padding(top = 8.dp)
    ) {

        Image(painter = painterResource(id = R.drawable.add_home),
            contentDescription = "Home",
            modifier = Modifier
                .size(12.dp)
                .padding(start = 1.dp)
        )
        Text(text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.outfitRegular,
            color = paleWhite,
            modifier = Modifier
                .padding(start = 2.dp)
        )
    }
}




//@Preview
@Composable
fun ImageButtonLarge(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 50.dp,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = blue,
            contentColor = paleWhite
        ),
        contentPadding = PaddingValues(0.dp),

        modifier = modifier
            .size(width = 180.dp, height = 32.dp)
//            .padding(top = 8.dp)
    ) {

        Image(painter = painterResource(id = R.drawable.add_home),
            contentDescription = "Home",
            modifier = Modifier
                .size(12.dp)
                .padding(start = 1.dp)
        )
        Text(text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.outfitRegular,
            color = paleWhite,
            modifier = Modifier
                .padding(start = 2.dp)
        )
    }
}

