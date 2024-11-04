package com.amzi.mastercellusv2.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceListResponse
import com.amzi.mastercellusv2.models.RootFolder
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.utility.TokenStorage
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.myComponents.navController
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepo(authAPIs: AuthAPIs,private val context: Context) {

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

    suspend fun login(email: String, password: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val loginResponse = authAPI.login(email, password)

                if (loginResponse.isSuccessful) {

                    loginResponse.body()?.let { loginRes ->
                        // Save tokens securely
                        TokenStorage.saveToken(context, loginRes.access, loginRes.refresh)
                        Log.d("AuthRepo", "Login Successful")
                        Log.d("AuthRepo", loginResponse.body().toString())


                        // Update the folders in the ViewModel
                        myComponents.registerViewModel.updateFolders(
                            loginRes.root_folders.map { it.name }
                        )

                        // Extract the device list
                        val deviceList = loginRes.devices.map {
                            DeviceListResponse(it.device_name, it.device_mac)
                        }

                        myComponents.registerViewModel.root_folders.clear() // Clear existing items
                        loginResponse.body()?.root_folders?.let { myComponents.registerViewModel.root_folders.addAll(it) }

                        myComponents.registerViewModel.user_id.value = loginRes.user_id.toString()
                        showLogs("USER_ID", myComponents.registerViewModel.user_id.value)

                        // Switch to Main thread to handle UI-related actions like navigation
                        withContext(Dispatchers.Main) {

                            myComponents.registerViewModel.updateDeviceList(deviceList)
                            mNavigator.navigateTo(Screens.PropertiesScreen.route)
                        }

                        Result.success("Login Successful")
                    } ?: run {
                        Log.e("AuthRepo", "Login failed: Empty response body")
                        Result.failure(Exception("Empty response body"))
                    }

                } else {
                    val errorMsg = loginResponse.errorBody()?.string() ?: "Login Failed"
                    Log.e("AuthRepo", "Login Unsuccessful: $errorMsg")
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Log.e("AuthRepo", "Error during login: ${e.message}")
                Result.failure(e)
            }
        }
    }


    // Optional: Logout function to clear tokens
    fun logout() {
        TokenStorage.clearToken(context)
    }
}
