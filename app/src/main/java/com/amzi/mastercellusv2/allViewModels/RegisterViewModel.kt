package com.amzi.mastercellusv2.allViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo) : ViewModel() {


    // Use mutableStateOf to track UI-related state changes
    var username = mutableStateOf("")
    var mobNum = mutableStateOf("")

    init {
        showLogs("ViewModel:", "Created")
    }

    var authRepo: AuthRepo = authRepo

    fun login(email: String, password: String) {
        showLogs("LOGIN: ", mobNum.value)

        viewModelScope.launch {
            authRepo.login(email, password)
        }
    }

    fun register(username: String, fName: String, lName: String, email: String, mobile_number: String) {
        mobNum.value = mobile_number
        showLogs("ViewModel: register", mobNum.value)

        viewModelScope.launch {
            authRepo.register(username =  username, fName =  fName,lName= lName, email =  email, mobile_number = mobile_number)

            // Store username and mobile number for verification
            this@RegisterViewModel.username.value = username
            this@RegisterViewModel.mobNum.value = mobile_number
        }
    }

    fun verify(mobileNum: String, password: String, password_confirm: String, otp: String) {
        mobNum.value = mobileNum
        showLogs("LOGIN: ", mobNum.value)

        viewModelScope.launch {
            authRepo.verify(mobileNum, password, password_confirm, otp)
        }
    }

}

