package com.amzi.mastercellusv2.Repository

import android.util.Log
import com.amzi.mastercellusv2.Networks.AuthAPIs
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

            }else{
                showLogs("auth","Registration Failed" + registerResponse.errorBody().toString())

//                showLogs("auth","Failed" + registerResponse.errorBody().toString())

            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }

}
