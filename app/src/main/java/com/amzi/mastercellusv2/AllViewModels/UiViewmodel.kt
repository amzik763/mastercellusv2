package com.amzi.mastercellusv2.AllViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiViewmodel : ViewModel() {

    var showHomeMacId by mutableStateOf(false)
        private set

    var showMushMacId by mutableStateOf(false)
        private set

    fun toggleHomeMacIdVisibility() {
        showHomeMacId = !showHomeMacId
    }

    fun toggleMushMacIdVisibility() {
        showMushMacId = !showMushMacId
    }
}
