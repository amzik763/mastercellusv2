package com.amzi.mastercellusv2.Repository

import android.util.Log
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.utility.showLogs

class AuthRepo(authAPIs: AuthAPIs) {

    init {
        showLogs("Repo:","Created")
    }


    val authAPI = authAPIs


    suspend fun registerUser(mobile_no: String, username: String, date_of_birth: String){

        try{
            val registerResponse = authAPI.registerUser(mobile_no, username, date_of_birth)

            if(registerResponse.isSuccessful){

                showLogs("auth","Registration Successful")

                Log.d("Register", "registerUser: Successful")

                mNavigator.navigateTo(Screens.SetPassword.route)

            }else{
                showLogs("auth","Registration Failed" + registerResponse.errorBody().toString())

//                showLogs("auth","Failed" + registerResponse.errorBody().toString())

            0}
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

    suspend fun setPassword(mobile_no: String, otp: String, password: String, confirm_password: String){

        try{
            val setPasswordRes = authAPI.setPassword(mobile_no, otp, password, confirm_password)

            if(setPasswordRes.isSuccessful){

                showLogs("SET PASSWORD","Password set Successfully")

            }else{
                showLogs("SET PASSWORD","Password not set" + setPasswordRes.errorBody().toString())

//                showLogs("auth","Failed" + registerResponse.errorBody().toString())

            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }
}
