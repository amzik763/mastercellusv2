package com.amzi.mastercellusv2.repository

import android.util.Log
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.utility.myComponents.navController
import com.amzi.mastercellusv2.utility.showLogs

class AuthRepo(authAPIs: AuthAPIs) {

    init {
        showLogs("Repo:","Created")
    }


    val authAPI = authAPIs



    suspend fun register(username: String, fName: String, lName: String, email: String, mobile_number: String){

        try{
            val registerResponse = authAPI.register( username =  username, fName =  fName, lName =  lName, email =  email,mobile_number = mobile_number)

            if(registerResponse.isSuccessful){
                showLogs("auth","Registration Successful")

                Log.d("Register", "registerUser: Successful")

//                navController.navigate(Screens.Verification.route)
                navController.navigate(Screens.Verification.route + "?username=$username&mobile_number=$mobile_number")

                showLogs("ROUTE","register")


            }else{
                showLogs("auth","Registration Failed" + registerResponse.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

    suspend fun login(email: String, password: String){

        try{
            val loginResponse = authAPI.login(email, password)

            if(loginResponse.isSuccessful){

                showLogs("LOGIN USER","Login Successful")

                mNavigator.navigateTo(Screens.Home.route)


            }else{
                showLogs("LOGIN USER","Login unSuccessful" + loginResponse.errorBody().toString())

                showLogs("LOGIN USER","Login unSuccessful")
            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

    suspend fun verify( mobile_number: String, password: String, password_confirm: String, otp: String){

        try{
            val verifyRes = authAPI.verify(mobile_number, password, password_confirm, otp)

            if(verifyRes.isSuccessful){

                showLogs("VERIFICATION","Verification Successful")

                mNavigator.navigateTo(Screens.Login.route)

            }else{
                showLogs("VERIFICATION","Verification unSuccessful" + verifyRes.errorBody().toString())

            }

        }

        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }
}
