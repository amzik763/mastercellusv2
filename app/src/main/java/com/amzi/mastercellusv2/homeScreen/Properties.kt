package com.amzi.mastercellusv2.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amzi.mastercellusv2.components.Header
import com.amzi.mastercellusv2.dialog.Add_DeviceDialog

@Preview
@Composable
fun Properties(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {
        Header(
            text1 = "Properties",
            text2 = "Set-up your home, plant here to automate. Click add button"
        )

        Spacer(modifier = Modifier.height(80.dp))

        Add_DeviceDialog()
    }
}