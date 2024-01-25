package com.amzi.mastercellusv2.Repository

import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.utility.showLogs

class AuthRepo(private val authAPIs: AuthAPIs) {

    init {
        showLogs("Repo:","Created")
    }


    val authAPI = authAPIs


    suspend fun getUser() {

        // TODO: later
        //IF CONNECTED
        val resp = authAPI.getUser()

        if(resp.isSuccessful){

        }else{

        }
    }


    suspend fun registerUser(mobNum: String, name: String, dob: String){
        showLogs("values",mobNum+name+dob)
        showLogs("auth","Registering")

        try{
            val registerResponse = authAPI.registerUser(name,mobNum,dob,dob)

            if(registerResponse.isSuccessful){
                showLogs("auth","Successfull")

            }else{
                showLogs("auth","Failed" + registerResponse.errorBody().toString())

            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

    suspend fun setPassword(password: String, otp: Int, mobNum: String) {
        try {
            val setPasswordResponse = authAPI.setPassword(mobNum,password,otp)
            if (setPasswordResponse.isSuccessful){

                showLogs("auth", "PasswordSuccessful")
            }else{
                showLogs("auth", "Failed" + setPasswordResponse.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error:",e.toString())
        }

    }

    suspend fun loginUser(mobNum: String,password: String,fcm_token :String){
        try {
            showLogs("mob is", mobNum)
            val loginResponse = authAPI.login(mobNum,password,fcm_token)
            if (loginResponse.isSuccessful){
                showLogs("auth", "loginSuccessful")
            }else{
                showLogs("auth", "Failed" + loginResponse.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error:",e.toString())
        }
    }
    suspend fun forgetPassword(mobNum: String){
        try {
            showLogs("mob is", mobNum)
            val forgetResponse = authAPI.forgotPassword(mobNum)
            if (forgetResponse.isSuccessful){
                showLogs("auth", "forgetSuccessful")
            }else{
                showLogs("auth", "Failed" + forgetResponse.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error:",e.toString())
        }
    }
}
