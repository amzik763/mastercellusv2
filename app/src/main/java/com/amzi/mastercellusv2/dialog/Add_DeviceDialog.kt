package com.amzi.mastercellusv2.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.components.SmallButton
import com.amzi.mastercellusv2.homeScreen.Properties
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import com.example.homeapplication.ui.theme.Grey
import com.example.homeapplication.ui.theme.pureWhite

@OptIn(ExperimentalMaterialApi::class)
//@Preview
@Composable
fun Add_DeviceDialog(
    onDismiss: () -> Unit,
    parentId: String? = null // Leave null if this is a top-level folder
){

    var folderName by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
                .border(width = 1.dp, color = pureWhite)
        )
        {
            Column(modifier = Modifier
                .background(pureWhite)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(170.dp)
            )  {
                Text(text = "Enter place name",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Grey
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
//                var home by remember { mutableStateOf("")}

                InputText(
                    text = folderName,
                    label = "Home",
                    onTextChange = {folderName = it},
                    color = Color.Black,
                    iconResId = R.drawable.home_home,
                    maxLength = 30,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)){

                    Image(painter = painterResource(id = R.drawable.home_place),
                        contentDescription = "Home Place",
                        modifier = Modifier.size(36.dp)
                            .clickable {
                                showLogs("Fardeen", "Hello")
                            }
                    )

                    Image(painter = painterResource(id = R.drawable.place),
                        contentDescription = "Place",
                        modifier = Modifier.size(36.dp)
                    )

                    Image(painter = painterResource(id = R.drawable.place2),
                        contentDescription = "Place2",
                        modifier = Modifier.size(36.dp)
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End)
                {
                    SmallButton(onClick = {
                        myComponents.registerViewModel.createFolder(folderName, registerViewModel.current_parent_id.value, myComponents.registerViewModel.user_id.value)

                        showLogs("KITCHEN", "Kitchen Folder Created")
                    }, text = "Add")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Add_SubDeviceDialog(
    onDismiss: () -> Unit,
    parentId: String? = null // Leave null if this is a top-level folder
){

    var folderName by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
                .border(width = 1.dp, color = pureWhite)
        )
        {
            Column(modifier = Modifier
                .background(pureWhite)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(170.dp)
            )  {
                Text(text = "Enter place name",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Grey
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
//                var home by remember { mutableStateOf("")}

                InputText(
                    text = folderName,
                    label = "Home",
                    onTextChange = {folderName = it},
                    color = Color.Black,
                    iconResId = R.drawable.home_home,
                    maxLength = 30,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)){

                    Image(painter = painterResource(id = R.drawable.home_place),
                        contentDescription = "Home Place",
                        modifier = Modifier.size(36.dp)
                            .clickable {
                                showLogs("Fardeen", "Hello")
                            }
                    )

                    Image(painter = painterResource(id = R.drawable.place),
                        contentDescription = "Place",
                        modifier = Modifier.size(36.dp)
                    )

                    Image(painter = painterResource(id = R.drawable.place2),
                        contentDescription = "Place2",
                        modifier = Modifier.size(36.dp)
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End)
                {
                    SmallButton(onClick = {
                        myComponents.registerViewModel.createSubFolder(folderName, registerViewModel.current_parent_id.value, myComponents.registerViewModel.user_id.value)

                        showLogs("KITCHEN", "Sub Folder Created")
                    }, text = "Add")
                }
            }
        }
    }
}


@Composable
fun PropertiesScreen() {

    val isFolderCreatedSuccessfully by registerViewModel.isFolderCreatedSuccessfully.collectAsState()

    Properties()

    if (registerViewModel.isEnterPlaceDialogShown) {
        Add_DeviceDialog(
            onDismiss = { registerViewModel.hideEnterPlaceDialog() }
        )
    }

    LaunchedEffect(isFolderCreatedSuccessfully) {
        if (isFolderCreatedSuccessfully) {
            registerViewModel.hideEnterPlaceDialog() // Hide dialog on success
        }

    }
}