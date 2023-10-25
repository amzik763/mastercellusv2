package com.amzi.mastercellusv2.Repository

import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.Networks.AuthAPIs

class AuthRepo(registerViewModel: RegisterViewModel, authAPI: AuthAPIs) {

    var registerViewModel = registerViewModel
    var authAPI = authAPI

    suspend fun getUser() {




        // TODO: later
        //IF CONNECTED
        val resp = authAPI.getUser()

            if(resp.isSuccessful){

            }else{

            }
        }
    }
