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
            }
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

    suspend fun loginUser(mobile_no: String, password: String){

        try{
            val loginResponse = authAPI.loginUser(mobile_no, password)

            if(loginResponse.isSuccessful){

                showLogs("LOGIN USER","Login Successful")

                mNavigator.navigateTo(Screens.Home.route)


            }else{
                showLogs("LOGIN USER","Login unSuccessful" + loginResponse.errorBody().toString())

            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

    suspend fun verify(mobile_no: String){

        try{
            val verifyRes = authAPI.verify(mobile_no)

            if(verifyRes.isSuccessful){

                showLogs("VERIFICATION","Verification Successful")

                mNavigator.navigateTo(Screens.SetPassword.route)


            }else{
                showLogs("LOGIN USER","Login unSuccessful" + verifyRes.errorBody().toString())

            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

}
