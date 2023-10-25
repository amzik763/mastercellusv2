package com.amzi.mastercellusv2.AllViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.Networks.RetrofitBuilder.instance
import com.amzi.mastercellusv2.Repository.AuthRepo
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    val authAPI: AuthAPIs by lazy {
        instance.create(AuthAPIs::class.java)
    }


    var authRepo:AuthRepo = AuthRepo(this,authAPI)

    fun getUser(){
        viewModelScope.launch {
            authRepo.getUser()
        }
    }




}