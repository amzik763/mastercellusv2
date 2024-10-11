package com.amzi.mastercellusv2.utility

import androidx.navigation.NavHostController
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.allViewModels.factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.HomeAutoRepo


//@Module
//@Installin
//@Singleton
object myComponents {

    //  lateinit var
    //  mx: Context
    lateinit var authAPI: AuthAPIs
    lateinit var homeAutoApi: HomeAutoApi
    lateinit var authRepo: AuthRepo
    lateinit var homeAutoRepo: HomeAutoRepo
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