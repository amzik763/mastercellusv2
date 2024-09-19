package com.amzi.mastercellusv2.allViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo) : ViewModel() {


    var mobNum = ""
    var password = ""
    var otp = ""

    init {
        showLogs("ViewModel:", "Created")
    }

    var authRepo: AuthRepo = authRepo

    fun login(email: String, password: String) {
        showLogs("LOGIN: ", mobNum)

        viewModelScope.launch {
            authRepo.login(email, password)
        }
    }

    fun register(username: String, fName: String, lName: String, email: String, mobile_number: String) {
        mobNum = mobile_number
        showLogs("ViewModel: register", mobNum)

        viewModelScope.launch {
            authRepo.register(username =  username, fName =  fName,lName= lName, email =  email, mobile_number = mobile_number)
        }
    }

/*    fun registerUser(mobNumt: String, name: String, dob: String) {
        mobNum = mobNumt
        showLogs("ViewModel: register", mobNum)

        viewModelScope.launch {
            authRepo.registerUser(mobNumt, name, dob)
        }
    }*/



//    }

    fun verify(mobileNum: String, password: String, password_confirm: String, otp: String) {
        mobNum = mobileNum
        showLogs("LOGIN: ", mobNum)

        viewModelScope.launch {
            authRepo.verify(mobileNum, password, password_confirm, otp)
        }
    }

}

