package com.amzi.mastercellusv2.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
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
fun Add_FileDialog(
    onDismiss: () -> Unit,
    // Leave null if this is a top-level folder
) {

    var applianceName by remember { mutableStateOf("") }
    var channelName by remember { mutableStateOf("") }
    var MacId by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("Select Device") }
    var expanded by remember { mutableStateOf(false) } // Tracks dropdown state

    // List of options for the dropdown
    val dropdownOptions = registerViewModel.mDeviceListRes.drop(1).map { it?.device_mac ?: "NA" }
    showLogs("LIST", registerViewModel.mDeviceListRes.toList().toString())

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
            Column(
                modifier = Modifier
                    .background(pureWhite)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(220.dp) // Adjust height to fit dropdown
            ) {
                // Dropdown menu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) // Rounded corners
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)) // Border
                        .clickable { expanded = true } // Open dropdown on click
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Selected option text
                        Text(
                            text = selectedOption,
                            modifier = Modifier.weight(1f), // Take up remaining space
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Grey
                            )
                        )

                        // Arrow icon
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrowdown), // Replace with your arrow resource
                            contentDescription = "Dropdown arrow",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Dropdown menu
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(
                            Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) // Rounded corners for dropdown
                    ) {
                        dropdownOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    MacId = option
                                    selectedOption = option // Update selected option
                                    expanded = false // Close dropdown
                                }
                            ) {
                                Text(
                                    text = option, style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                /*Text(
                    text = "Enter place name",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Grey
                    )
                )*/

//                Spacer(modifier = Modifier.height(8.dp))

                InputText(
                    text = applianceName,
                    label = "Appliance Name",
                    onTextChange = { applianceName = it },
                    color = Color.Black,
                    iconResId = com.android.mushroomapplication.R.drawable.ic_electrical,
                    maxLength = 30,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                InputText(
                    text = channelName,
                    label = "Enter pin",
                    onTextChange = { channelName = it },
                    color = Color.Black,
                    iconResId = com.android.mushroomapplication.R.drawable.ic_electrical,
                    maxLength = 30,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    SmallButton(
                        onClick = {
                            // Use `selectedOption` as the chosen dropdown value
                            myComponents.registerViewModel.createFile(
                                applianceName,
                                MacId,
                                channelName,
                                registerViewModel.current_parent_id,
                                registerViewModel.user_id
                            )
                            showLogs("PLACE", "$selectedOption Folder Created: $applianceName")
                        },
                        text = "Add"
                    )
                }
            }
        }
    }
}


@Composable
fun PropertiesScreen2() {

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