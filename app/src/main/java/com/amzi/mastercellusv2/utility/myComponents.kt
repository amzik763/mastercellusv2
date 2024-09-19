package com.amzi.mastercellusv2.utility

import androidx.navigation.NavHostController
import com.amzi.mastercellusv2.allViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.repository.AuthRepo


//@Module
//@Installin
//@Singleton
object myComponents {

    //  lateinit var
    //  mx: Context
    lateinit var authAPI: AuthAPIs
    lateinit var authRepo: AuthRepo
//    lateinit var mUiViewModelFactory:
//    lateinit var mUiViewModel: UiViewmodel
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    lateinit var registerViewModel: RegisterViewModel
//  lateinit var networkMonitor: NetworkMonitor
    lateinit var navController:NavHostController
}

object responses{

//    lateinit var loginResponse: Response<login_model>

}