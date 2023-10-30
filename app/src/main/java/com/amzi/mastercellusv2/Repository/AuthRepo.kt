package com.amzi.mastercellusv2.Repository

import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.Networks.RetrofitBuilder
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
    }
