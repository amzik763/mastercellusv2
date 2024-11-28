package com.amzi.mastercellusv2.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.amzi.mastercellusv2.models.GetFolderResV2
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.amzi.mastercellusv2.utility.TokenStorage
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.showLogs

class HomeAutoRepo(val homeAutoApi: HomeAutoApi, private val context: Context) {

    init {
        showLogs("Home Repo:","Home Repo Created")
    }

    suspend fun registerUserDevice( mobile_number: String, device_mac: String){
        try {

            // Retrieve the access token
            val accessToken = TokenStorage.getToken(context = context)

            // Log the raw access token for debugging
            showLogs("Home Repo:", "Raw Access Token: ${accessToken?.first ?: "No Token Found"}")

            // Ensure the access token is not null, remove all whitespace, and trim
            val token = accessToken?.first?.replace("\\s".toRegex(), "")?.trim()

            if (token.isNullOrBlank()) {
                showLogs("Home Repo Error:", "Access token is missing or empty.")
                return
            }

            // Append "Bearer " before the token with only one space
            val authorizationHeader = "Bearer $token"

            // Log the complete Authorization header
            showLogs("Home Repo:", "Authorization Header: '$authorizationHeader'")

            // Make the API call with the Authorization header
            val registerUserDevice = homeAutoApi.registerUserDevice(authorizationHeader, mobile_number, device_mac)
            if(registerUserDevice.isSuccessful){

                showLogs("Home Repo:","registerUserDevice Successful")

            }else{
                showLogs("Home Repo:","registerUserDevice Unsuccessful")
            }

        }catch (e: Exception){
            showLogs("Home Error",e.toString())

        }
    }

    suspend fun createFolder(name: String, parent_id: String?, user: String): Boolean {

        if (name.isBlank() || user.isBlank()) {
            Log.d("Home Repo", "Name or User ID cannot be empty.")
            return false
        }
        Log.d("Home Repo", "Name: $name, Parent ID: $parent_id, User ID: $user")

        return try {
            // Retrieve the access token
            val accessToken = TokenStorage.getToken(context)
            val token = accessToken?.first?.replace("\\s".toRegex(), "")?.trim()
            if (token.isNullOrBlank()) {
                showLogs("Home Repo Error:", "Access token is missing or empty.")
                return false
            }
            val authorizationHeader = "Bearer $token"
            // Call the API with authorization header
            val response = homeAutoApi.createFolder(
                authorizationHeader = authorizationHeader,
                name = name,
                parent_id = parent_id ?: "", //Pass empty string if parent_id is null
                user = user
            )
            if (response.isSuccessful) {
                myComponents.registerViewModel.mCreateFolderRes.value = response.body()
                showLogs("Home Repo:", "Folder Created Successfully")
                true // Return true if the response is successful
            } else {
                showLogs("Home Repo:", "Folder creation failed.")
                false // Return false if the response is unsuccessful
            }
        } catch (e: Exception) {
            showLogs("Home Error", e.toString())
            false // Return false if an exception occurs
        }
    }

    suspend fun getFolderAndFile(user_id: String, parent_id: String): GetFolderResV2? {
        return try {
            // Retrieve and clean up the access token
            val token = TokenStorage.getToken(context = context)
                ?.first
                ?.replace("\\s".toRegex(), "")
                ?.trim()
                ?: run {
                    showLogs("Home Repo Error:", "Access token is missing or empty.")
                    return null
                }

            // Prepare the authorization header
            val authorizationHeader = "Bearer $token"
            showLogs("Home Repo:", "Authorization Header: '$authorizationHeader'")

            // Make the API call
            val response = homeAutoApi.getFolderAndFile(authorizationHeader, user_id, parent_id)

            if (response.isSuccessful) {
                showLogs("Home Repo:", "Get Folder and files Successful")
                showLogs("Home Repo:", response.body().toString())
                myComponents.registerViewModel.mSubFolderRes.value = response.body()// Returns FolderResponse if successful
                response.body()
            } else {
                showLogs("Home Repo:", "Get Folder and files Unsuccessful: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            showLogs("Home Error", e.toString())
            null
        }
    }

    suspend fun createFile(
        applianceName: String,
        MacId: String,
        channelName: String,
        currentParentId: MutableState<String>,
        userId: MutableState<String>
    ) {

        try {
            // Retrieve and clean up the access token
            val token = TokenStorage.getToken(context = context)
                ?.first
                ?.replace("\\s".toRegex(), "")
                ?.trim()
                ?: run {
                    showLogs("Home Repo Error:", "Access token is missing or empty.")
//                    return null
                }

            // Prepare the authorization header
            val authorizationHeader = "Bearer $token"
            showLogs("Home Repo:", "Authorization Header: '$authorizationHeader'")
            showLogs("Create file response:", applianceName + " " + MacId + " " + channelName + " " + currentParentId.toString() + " " + userId.toString())

            // Make the API call
            val response = homeAutoApi.createFile(authorizationHeader, applianceName, MacId, channelName,
                currentParentId.value.toString(),
                userId.value.toString()
            )

            if (response.isSuccessful) {
                showLogs("Home Repo:", "Create files Successful")
                showLogs("Home Repo:", response.body().toString())
                myComponents.registerViewModel.mCreateFileRes.value = response.body()
                myComponents.registerViewModel.sub_files.clear()
                myComponents.registerViewModel.mSubFolderRes.value = null
                myComponents.registerViewModel.hideMacDialog()
                myComponents.registerViewModel.hideEnterPlaceDialog()
                myComponents.navController.popBackStack()
//                myComponents.registerViewModel.mSubFolderRes.value = response.body()// Returns FolderResponse if successful
//                response.body()
            } else {
                showLogs("Home Repo:", "Create files Unsuccessful: ${response.message()}")
                myComponents.registerViewModel.hideMacDialog()
                myComponents.registerViewModel.hideEnterPlaceDialog()


            }
        } catch (e: Exception) {
            showLogs("Home Error", e.toString())
            myComponents.registerViewModel.hideEnterPlaceDialog()
            myComponents.registerViewModel.hideMacDialog()


        }

    }
}




/*
try {
    // Retrieve the access token
    val accessToken = TokenStorage.getToken(context = ct)

    // Log the raw access token for debugging
    showLogs("PROTECTED", "Raw Access Token: ${accessToken?.first ?: "No Token Found"}")

    // Ensure the access token is not null, remove all whitespace, and trim
    val token = accessToken?.first?.replace("\\s".toRegex(), "")?.trim()

    // Log the formatted token
    showLogs("DEBUG", "Formatted Token: '$token'")

    if (token.isNullOrBlank()) {
        showLogs("ERROR", "Access token is missing or empty.")
        return@launch
    }

    // Append "Bearer " before the token with only one space
    val authorizationHeader = "Bearer $token"

    // Log the complete Authorization header
    showLogs("DEBUG", "Authorization Header: '$authorizationHeader'")

    // Make the API call with the Authorization header
    val response = myComponents.otherAPI.protected(authorizationHeader)

    if (response.isSuccessful) {
        // Handle success
        val result = response.body()
        showLogs("SUCCESS", "Protected API response: $result")
    } else {
        // Handle failure and log the error body
        val error = response.errorBody()?.string()
        showLogs("ERROR", "Protected API error: $error")
    }
} catch (e: Exception) {
    showLogs("EXCEPTION", "Exception occurred: ${e.message}")
    e.printStackTrace()
}*/
