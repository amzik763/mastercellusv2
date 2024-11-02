package com.amzi.mastercellusv2.allViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceListResponse
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.HomeAutoRepo
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo, homeAutoRepo: HomeAutoRepo) : ViewModel() {


    // Use mutableStateOf to track UI-related state changes
    var username = mutableStateOf("")
    var mobNum = mutableStateOf("")
    var user = mutableStateOf("")
    var folderName = mutableStateOf("")


    // Folder names list
//    val folders = mutableStateListOf<String>()


    init {
        showLogs("ViewModel:", "Created")
    }

    var authRepo: AuthRepo = authRepo
    var hmeAutoRepo: HomeAutoRepo = homeAutoRepo

    fun login(email: String, password: String) {
        showLogs("LOGIN: ", mobNum.value)
        viewModelScope.launch {
            authRepo.login(email, password)
        }
    }


    fun register(username: String, fName: String, lName: String, email: String, mobile_number: String) {
        mobNum.value = mobile_number
        showLogs("ViewModel: register", mobNum.value)
        viewModelScope.launch {
            authRepo.register(username =  username, fName =  fName,lName= lName, email =  email, mobile_number = mobile_number)
            // Store username and mobile number for verification
            this@RegisterViewModel.username.value = username
            this@RegisterViewModel.mobNum.value = mobile_number
        }
    }

    fun verify(mobileNum: String, password: String, password_confirm: String, otp: String) {
        mobNum.value = mobileNum
        showLogs("LOGIN: ", mobNum.value)
        viewModelScope.launch {
            authRepo.verify(mobileNum, password, password_confirm, otp)
        }
    }

    private val _deviceList = MutableStateFlow<List<DeviceListResponse>>(emptyList())
    val deviceList: StateFlow<List<DeviceListResponse>> = _deviceList
    //Update device List
    fun updateDeviceList(newDevices: List<DeviceListResponse>) {
        _deviceList.value = newDevices
    }

    fun registerUserDevice( mobile_number: String, device_mac: String){
        viewModelScope.launch {
            hmeAutoRepo.registerUserDevice(mobile_number, device_mac)
            showLogs("ViewModel: ","registerUserDevice")
        }
    }

/*    fun createFolder(name: String, parent_id: String? = null, user: String) {
        viewModelScope.launch {
            hmeAutoRepo.createFolder(name, parent_id, user)
            this@RegisterViewModel.folderName.value = name
        }
    }*/

    private val _folders = MutableStateFlow<List<String>>(emptyList())
    val folders: StateFlow<List<String>> = _folders


    private val _isFolderCreatedSuccessfully = MutableStateFlow(false)
    val isFolderCreatedSuccessfully: StateFlow<Boolean> = _isFolderCreatedSuccessfully

    fun createFolder(name: String, parent_id: String? = null, user: String) {
        viewModelScope.launch {
            val isFolderCreated = hmeAutoRepo.createFolder(name, parent_id, user)
            if (isFolderCreated) {
                _isFolderCreatedSuccessfully.value = true
                _folders.value += name
            } else {
                showLogs("ViewModel", "Folder creation failed")
            }
        }
    }

    var isEnterPlaceDialogShown by mutableStateOf(false)
        private set

    fun showEnterPlaceDialog() {
        isEnterPlaceDialogShown = true
    }

    fun hideEnterPlaceDialog(){
        isEnterPlaceDialogShown = false
        _isFolderCreatedSuccessfully.value = false // Reset on hide
    }

}

