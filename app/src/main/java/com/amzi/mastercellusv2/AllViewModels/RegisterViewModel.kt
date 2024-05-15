package com.amzi.mastercellusv2.AllViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.Networks.RetrofitBuilder.instance
import com.amzi.mastercellusv2.Repository.AuthRepo
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo) : ViewModel() {


    var mobNum = ""
    var password = ""
    var otp = ""

    init {
        showLogs("ViewModel:", "Created")
    }

    var authRepo: AuthRepo = authRepo


    fun registerUser(mobNumt: String, name: String, dob: String) {
        mobNum = mobNumt
        showLogs("ViewModel: register", mobNum)

        viewModelScope.launch {
            authRepo.registerUser(mobNumt, name, dob)
        }
    }

//    fun setPassword(mobileNum: String, otp: String, password: String, confirm_password: String) {
//        mobNum = mobileNum
//        showLogs("PASSWORD: ", mobNum)
//
//        viewModelScope.launch {
//            authRepo.setPassword(mobileNum, otp, password, confirm_password)
//        }
//
//    }

    fun loginUser(mobileNum: String, password: String) {
        mobNum = mobileNum
        showLogs("LOGIN: ", mobNum)

        viewModelScope.launch {
            authRepo.loginUser(mobileNum, password)
        }
    }

    fun verify(mobileNum: String) {
        mobNum = mobileNum
        showLogs("LOGIN: ", mobNum)

        viewModelScope.launch {
            authRepo.verify(mobileNum)
        }
    }

    fun setPassword(register: String,mobileNum: String, otp: String, password: String, confirm_password: String) {
        mobNum = mobileNum
        showLogs("SET PASSWORD: ", mobNum)

        viewModelScope.launch {
            authRepo.setPassword(register, mobileNum, otp, password, confirm_password)
        }
    }
}

