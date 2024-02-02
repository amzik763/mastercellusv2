package com.amzi.mastercellusv2.AllViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiViewmodel : ViewModel() {

    var showMacId by mutableStateOf(false)
        private set

//    var showMacId by mutableStateOf(false)
//        private set
}