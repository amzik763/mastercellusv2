package com.amzi.mastercellusv2.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.amzi.mastercellusv2.components.ImageButtonLarge
import com.amzi.mastercellusv2.models.Folder
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import com.android.homeapplication.R
import com.android.homeapplication.utility.mFont
import com.example.demo.ui.theme.pureWhite
import com.example.homeapplication.ui.theme.Grey
import com.example.homeapplication.ui.theme.paleWhite
import com.example.homeapplication.ui.theme.pureBlack

//@Preview
@Composable
fun SubFolderScreen(){
    showLogs("NAV SUB","clicked and navigating 2")
    val folders by registerViewModel.folders.collectAsState()
//    val folders2 by registerViewModel.sub_folders.collectAsState()

    // When the screen is displayed, fetch folders
   /* LaunchedEffect(Unit) {
        registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
    }*/

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
                text = "Devices",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Set-up your devices and sub folders here. Click add button",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                ),
                modifier = Modifier.padding(end = 46.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                ImageButtonLarge(
                    onClick = {
                        registerViewModel.showEnterPlaceDialog()
                    },
                    text = "Add Device"
                )
            }
            Spacer(modifier = Modifier.height(36.dp))

            /*FolderRow(folders = folders){ folder ->
                registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
//                registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
            }*/

            LazyColumn {
                itemsIndexed(registerViewModel.sub_folders.chunked(2)) { chunkIndex, folderPair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Calculate absolute indices
                        val firstIndex = chunkIndex * 2
                        val secondIndex = firstIndex + 1
                        // Access the original list registerViewModel.root_folders using calculated indices
                        if (firstIndex < registerViewModel.root_folders.size) {
                            CreateFolder(registerViewModel.root_folders[firstIndex].name, onClick = {
                                /* registerViewModel.getFolderAndFile(
                                     registerViewModel.user_id.value,
                                     registerViewModel.root_folders[firstIndex].id.toString()
                                 )*/
                            })
                        }
                        if (secondIndex < registerViewModel.root_folders.size) {
                            CreateFolder(registerViewModel.root_folders[secondIndex].name, onClick = {
                                /*registerViewModel.getFolderAndFile(
                                    registerViewModel.user_id.value,
                                    registerViewModel.root_folders[secondIndex].id.toString()
                                )*/
                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp)) // Spacer between rows
                }
            }

        }
       /* Column {
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
                    text = "Device"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Devices",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Set-up your devices and sub folders here. Click add button",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Grey,
                    fontFamily = mFont.outfitRegular
                ),
                modifier = Modifier.padding(end = 46.dp)
            )
            Spacer(modifier = Modifier.height(36.dp))

            *//*FolderRow(folders = folders){ folder ->
                registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
//                registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
            }*//*

            LazyColumn {
                itemsIndexed(registerViewModel.root_folders.chunked(2)) { chunkIndex, folderPair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Calculate absolute indices
                        val firstIndex = chunkIndex * 2
                        val secondIndex = firstIndex + 1
                        // Access the original list registerViewModel.root_folders using calculated indices
                        if (firstIndex < registerViewModel.root_folders.size) {
                            CreateFolder(registerViewModel.root_folders[firstIndex].name, onClick = {
                                *//* registerViewModel.getFolderAndFile(
                                     registerViewModel.user_id.value,
                                     registerViewModel.root_folders[firstIndex].id.toString()
                                 )*//*
                            })
                        }
                        if (secondIndex < registerViewModel.root_folders.size) {
                            CreateFolder(registerViewModel.root_folders[secondIndex].name, onClick = {
                                *//*registerViewModel.getFolderAndFile(
                                    registerViewModel.user_id.value,
                                    registerViewModel.root_folders[secondIndex].id.toString()
                                )*//*
                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp)) // Spacer between rows
                }
            }

        }*/
    }
}

//@Preview
/*
@Composable
fun
        CreateFolder(folderName: String,onClick: (String) -> Unit) {
        Card (modifier = Modifier
            .width(150.dp)
            .height(120.dp)
            .background(color = paleWhite, shape = RoundedCornerShape(36.dp))
            .border(width = 2.dp, color = paleWhite)
            .clickable { onClick(folderName) } // Add click event here
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
*/
/*

@Composable
fun FolderRow(folders: List<String>,
              onFolderClick: (String) -> Unit) { // Replace FolderType with your actual folder data type
    LazyColumn {
        items(folders.chunked(2)) { folderPair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Create the first folder
                CreateFolder(folderPair[0], onClick = onFolderClick)

                // Create the second folder if it exists
                if (folderPair.size > 1) {
                    CreateFolder(folderPair[1], onClick = onFolderClick)
                }
            }
            Spacer(modifier = Modifier.height(12.dp)) // Spacer between rows
        }
    }
}*/
