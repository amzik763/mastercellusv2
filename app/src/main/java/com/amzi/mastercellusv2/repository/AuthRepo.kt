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

                navController.navigate(Screens.Verification.route)

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

/*    suspend fun registerUser(mobile_no: String, username: String, date_of_birth: String){

        try{
            val registerResponse = authAPI.registerUser(mobile_no, username, date_of_birth)

            if(registerResponse.isSuccessful){

                showLogs("auth","Registration Successful")

                Log.d("Register", "registerUser: Successful")

                navController.navigate(route = Screens.SetPassword.route + "/register")
                showLogs("ROUTE","register")


            }else{
                showLogs("auth","Registration Failed" + registerResponse.errorBody().toString())

//                showLogs("auth","Failed" + registerResponse.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }*/

/*
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
*/

    suspend fun verify( mobile_number: String, password: String, password_confirm: String, otp: String){

        try{
            val verifyRes = authAPI.verify(mobile_number, password, password_confirm, otp)

            if(verifyRes.isSuccessful){

                showLogs("VERIFICATION","Verification Successful")

//                navController.navigate(route = Screens.SetPassword.route + "/change_password")
//                showLogs("ROUTE","change_password")



            }else{
                showLogs("VERIFICATION","Verification unSuccessful" + verifyRes.errorBody().toString())

            }

        }

        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }



}
