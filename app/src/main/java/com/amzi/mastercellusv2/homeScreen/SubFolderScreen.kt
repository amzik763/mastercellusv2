package com.amzi.mastercellusv2.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.components.ImageButton
import com.amzi.mastercellusv2.components.ImageButtonLarge
import com.amzi.mastercellusv2.models.File
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import com.android.homeapplication.R
import com.android.homeapplication.utility.mFont
import com.example.demo.ui.theme.pureWhite
import com.example.homeapplication.ui.theme.Grey

//@Preview
@Composable
fun SubFolderScreen() {
    showLogs("NAV SUB", "clicked and navigating 2")
    val folders by registerViewModel.folders.collectAsState()
//    val folders2 by registerViewModel.sub_folders.collectAsState()

    // When the screen is displayed, fetch folders
    /* LaunchedEffect(Unit) {
        registerViewModel.getFolderAndFile(registerViewModel.user_id.value, registerViewModel.parent_id.value)
    }*/

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = pureWhite)
            .padding(start = 16.dp, end = 16.dp, top = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
                        registerViewModel.showMacDialog()
                    },
                    text = "Add Device"
                )
            }
            Spacer(modifier = Modifier.height(36.dp))

            if(registerViewModel.mSubFolderRes.value?.files != null)
                TwoColumnGrid(registerViewModel.mSubFolderRes.value?.files!!)

                /*LazyColumn {
                itemsIndexed(registerViewModel.mSubFolderRes.value!!.files){
                    index, item ->
                    {
                        TwoColumnGrid()
                    }
                }
            }*/
        }
    }
}

@Composable
fun abc(){

}

@Composable
fun TwoColumnGrid(files: List<File>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Screen width
    val padding = 8.dp // Padding around the grid
    val columnWidth = (screenWidth) / 2 // Half of screen width minus padding

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Fixed to 2 columns
        contentPadding = PaddingValues(0.dp)
    ) {
        items(files) { item ->

            val keyToFind = item.channel_name // The key you want to find
            var clr = remember{ mutableStateOf(registerViewModel.sub_files.get(keyToFind.toString()))}

            Box(
                modifier = Modifier
                    .padding(12.dp) // Padding between items
                    .width(columnWidth)
                    .height(100.dp)// Set width for each item
//                    .aspectRatio(1f) // Ensure square items
                    .clip(RoundedCornerShape(8.dp)) // Apply corner radius
                    .background(Color.White) // Background color
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)) // Border with corner radius
//                    .shadow(4.dp, RoundedCornerShape(8.dp)) // Shadow with corner radius
                    .clickable {
                        registerViewModel.current_mac_id.value = item.device_mac
                        clr.value = !clr.value!!
                        myComponents.registerViewModel.sub_files.replace(item.channel_name.toString(), clr.value!!)
                        registerViewModel.bulbSwitch(item.channel_name.toString(),item.device_mac.toString())
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier

                    .background(
                        if (clr.value == true) Color(255, 255, 153) else Color.White,
                        RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                ){
                    Text(text = item.name, style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W400,
                        color = lightBlack
                    ),
                        modifier = Modifier.padding(top = 16.dp)
                            .align(Alignment.TopStart)
                    )
                    Text(text = "ID: "+item.id.toString(), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = lightBlack
                    ),
                        modifier = Modifier
                            .padding(top = 48.dp)
                    )

                    Text(text = "PIN: "+item.channel_name.toString(), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = lightBlack
                    ),
                        modifier = Modifier
                            .padding(top = 68.dp)
                    )

                    Image(painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.ic_bulb2), contentDescription = "",
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.BottomEnd)
                    )
                }

            }
        }
    }
}

