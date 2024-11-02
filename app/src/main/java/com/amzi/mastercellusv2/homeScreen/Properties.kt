package com.amzi.mastercellusv2.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.components.ImageButton
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.android.homeapplication.R
import com.android.homeapplication.utility.mFont
import com.example.demo.ui.theme.pureWhite
import com.example.homeapplication.ui.theme.Grey
import com.example.homeapplication.ui.theme.pureBlack

//@Preview
@Composable
fun Properties(){

    val folders by registerViewModel.folders.collectAsState()

    Row (modifier = Modifier
        .fillMaxSize()
        .background(color = pureWhite)
        .padding(start = 16.dp, end = 16.dp, top = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.skaio),
                    contentDescription = "Skaio Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(width = 150.dp, height = 55.dp)
                )
                ImageButton(
                    onClick = {
                        registerViewModel.showEnterPlaceDialog()
                    },
                    text = "Add"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Properties",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Set-up your home, plant here to automate. Click add button",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                ),
                modifier = Modifier.padding(end = 46.dp)
            )
            Spacer(modifier = Modifier.height(36.dp))

            for (i in folders.indices step 2) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Display two folders per row
                    CreateFolder(folders[i])
                    Spacer(modifier = Modifier.width(8.dp)) // Spacer between folders

                    if (i + 1 < folders.size) { // Check if there is a next folder to display
                        CreateFolder(folders[i + 1])
                    }
                }
                Spacer(modifier = Modifier.height(8.dp)) // Spacer between rows
            }
        }
    }
}

//@Preview
@Composable
fun CreateFolder(folderName: String) {
        Card (modifier = Modifier
            .width(150.dp)
            .height(120.dp)
            .background(color = Color.White, shape = RoundedCornerShape(36.dp))
            .border(width = 2.dp, color = Color.White)
        )
        {
            Column(modifier = Modifier
                .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = folderName,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = pureBlack,
                        fontFamily = mFont.outfitRegular
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.home_place),
                    contentDescription = "Home"
                )
            }
        }
}