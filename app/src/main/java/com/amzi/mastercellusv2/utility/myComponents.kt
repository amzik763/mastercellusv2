package com.amzi.mastercellusv2.utility

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.amzi.mastercellusv2.AllViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.Repository.AuthRepo
import retrofit2.Response


//@Module
//@Installin
//@Singleton
object myComponents {


//  lateinit var mx: Context
    lateinit var authAPI: AuthAPIs
    lateinit var authRepo: AuthRepo
//    lateinit var mUiViewModelFactory: UiViewModelFactory
//    lateinit var mUiViewModel: UiViewModel
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    lateinit var registerViewModel: RegisterViewModel
//    lateinit var networkMonitor: NetworkMonitor
    lateinit var navController:NavHostController

}


//fun sendMsinviewmofel():Repository{
//    return myrepo
//}



object responses{

//    lateinit var loginResponse: Response<login_model>




}