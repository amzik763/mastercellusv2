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
    var password =  ""
    var otp = ""
//    var name =  mutableStateOf("")
//    var dob =  mutableStateOf("")


init {
    showLogs("ViewModel:","Created")
}

    var authRepo:AuthRepo = authRepo

    fun getUser(){
        viewModelScope.launch {
            authRepo.getUser()
        }
    }

    fun registerUser(mobNumt: String, name: String, dob: String) {
       mobNum = mobNumt
        showLogs("ViewModel: register",mobNum)

        viewModelScope.launch {
            authRepo.registerUser(mobNumt,name,dob)
        }

    }
    fun setPassword(password : String, otp : Int){

        viewModelScope.launch {
            authRepo.setPassword(password,otp,mobNum)
        }

    }

    fun forgotPassword(mobNum: String){
        val mobNum2 = "+91$mobNum"
        viewModelScope.launch {
            authRepo.forgetPassword(mobNum2)
        }

    }
    fun loginUser(mobNum : String,password: String,fcm_token :String){
        val mobNum2 = "+91$mobNum"
        viewModelScope.launch {
            authRepo.loginUser(mobNum2,password,fcm_token)
        }
    }
}