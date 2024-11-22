package com.amzi.mastercellusv2.allViewModels

import android.icu.text.Normalizer.NO
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceListResponse
import com.amzi.mastercellusv2.models.CreateFolderRes
import com.amzi.mastercellusv2.models.Folder
import com.amzi.mastercellusv2.models.GetFolderRes
import com.amzi.mastercellusv2.models.GetFolderResV2
import com.amzi.mastercellusv2.models.RootFolder
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.HomeAutoRepo
import com.amzi.mastercellusv2.utility.myComponents.navController
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo, homeAutoRepo: HomeAutoRepo) : ViewModel() {
    var mPassword = mutableStateOf("")
    var mUsername = mutableStateOf("")

    // Use mutableStateOf to track UI-related state changes
    var username = mutableStateOf("")
    var mobNum = mutableStateOf("")
    var user_id = mutableStateOf("")
    var folderName = mutableStateOf("")
    var parent_id = mutableStateOf("")
    var folder_id = mutableStateOf("")
    var root_folders = mutableStateListOf<RootFolder>()
    var sub_folders = mutableStateListOf<Folder>()
    var current_parent_id = mutableStateOf("")

    var mCreateFolderRes = mutableStateOf<CreateFolderRes?>(null)
    var mSubFolderRes = mutableStateOf<GetFolderResV2?>(null)
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

    fun updateFolders(newFolders: List<String>) {
        _folders.value = newFolders
    }

    private val _isFolderCreatedSuccessfully = MutableStateFlow(false)
    val isFolderCreatedSuccessfully: StateFlow<Boolean> = _isFolderCreatedSuccessfully

    fun createFolder(name: String, parent_id: String? = null, user: String) {
        viewModelScope.launch {
            val isFolderCreated = hmeAutoRepo.createFolder(name, parent_id, user)
            if (isFolderCreated) {
                _isFolderCreatedSuccessfully.value = true
                _folders.value += name
                root_folders.add(RootFolder(mCreateFolderRes.value?.created_at?:"",mCreateFolderRes.value?.id?:0,mCreateFolderRes.value?.name?:"",mCreateFolderRes.value?.parent_id?:0,mCreateFolderRes.value?.user?:0))
            } else {
                showLogs("ViewModel", "Folder creation failed")
            }
        }
    }

    fun createSubFolder(name: String, parent_id: String? = null, user: String) {
        viewModelScope.launch {
            val isFolderCreated = hmeAutoRepo.createFolder(name, parent_id, user)
            if (isFolderCreated) {
                _isFolderCreatedSuccessfully.value = true
                _folders.value += name
                sub_folders.add(Folder(mCreateFolderRes.value?.created_at?:"",mCreateFolderRes.value?.id?:0,mCreateFolderRes.value?.name?:"",mCreateFolderRes.value?.parent_id?:0,mCreateFolderRes.value?.user?:0))
            } else {
                showLogs("ViewModel", "Folder creation failed")
            }
        }
    }


    private val _getFolders = MutableStateFlow<List<Folder>>(emptyList())
    val getFolders: StateFlow<List<Folder>> = _getFolders


    fun getFolderAndFile(user_id: String, id: String, parent_id: String) {
        registerViewModel.current_parent_id.value = id
        sub_folders.clear()
        viewModelScope.launch {
            val foldersResponse = hmeAutoRepo.getFolderAndFile( user_id, id)
            foldersResponse?.let {
                showLogs("SUB FOLDERS ARE 2: ", foldersResponse.toString())
                showLogs("SUB FOLDERS ARE 3: ", foldersResponse.folders.toString())
                mSubFolderRes.value = foldersResponse
                try{
                    sub_folders.addAll(mSubFolderRes.value!!.folders)
                }catch (e:Exception){
                    showLogs("NO SUB FOLDERS: ", "NO SUB FOLDERS")

                }
                showLogs("SUB FOLDERS ARE: ", mSubFolderRes.value.toString())

                _getFolders.value = it.folders
            } ?: showLogs("ViewModel", "Failed to fetch folders")
        }
        showLogs("NAV SUB","clicked and navigating " + user_id + " " + id + " " + parent_id)
        navController.navigate(Screens.SubFolderScreen.route)
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

