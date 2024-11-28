package com.amzi.mastercellusv2.allViewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceListResponse
import com.amzi.mastercellusv2.models.CreateFolderRes
import com.amzi.mastercellusv2.models.Device
import com.amzi.mastercellusv2.models.Folder
import com.amzi.mastercellusv2.models.GetFolderResV2
import com.amzi.mastercellusv2.models.RootFolder
import com.amzi.mastercellusv2.models.fileCreatedRes
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.HomeAutoRepo
import com.amzi.mastercellusv2.utility.myComponents.homeAutoRepo
import com.amzi.mastercellusv2.utility.myComponents.navController
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.showLogs
import com.example.homeapplication.mqtt.MqttClientHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class RegisterViewModel(context: Context, authRepo: AuthRepo, homeAutoRepo: HomeAutoRepo) : ViewModel() {

    private val mqttClient = MqttClientHelper(context.applicationContext)


    var mPassword = mutableStateOf("")
    var mUsername = mutableStateOf("")

    // Use mutableStateOf to track UI-related state changes
    var username = mutableStateOf("")
    var mobNum = mutableStateOf("")
    var user_id = mutableStateOf("")
        var folderName = mutableStateOf("")
        var folder_id = mutableStateOf("")
        var parent_id = mutableStateOf("")
    var root_folders = mutableStateListOf<RootFolder>()
    var sub_folders = mutableStateListOf<Folder>()
    var sub_files = mutableStateMapOf<String,Boolean>()
    var current_parent_id = mutableStateOf("")
    var current_mac_id = mutableStateOf("")

    var mCreateFolderRes = mutableStateOf<CreateFolderRes?>(null)
    var mCreateFileRes = mutableStateOf<fileCreatedRes?>(null)
    var mSubFolderRes = mutableStateOf<GetFolderResV2?>(null)
    var mDeviceListRes = mutableStateListOf<Device?>(null)
    var showMacDialog by mutableStateOf(false)

    var fileStatus = mutableStateListOf<Map<String,Boolean>?>(null)

    // Folder names list
//    val folders = mutableStateListOf<String>()

    private suspend fun startFetchingData() {
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String) {
                Log.w(MqttClientHelper.TAG, "MQTT reconnect...$reconnect")
            }

            override fun connectionLost(cause: Throwable) {
                Log.e(MqttClientHelper.TAG, "MQTT lost..." + cause.message)
                //val st = "MQTT lost! " + cause.message
            }

            override fun messageArrived(topic: String, message: MqttMessage) {
                val mess = message.toString()
                val log = String.format("MQTT RX [%s]: %s", topic, mess)
                Log.w(MqttClientHelper.TAG, log)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {
                Log.w(MqttClientHelper.TAG, "Publish success...")
            }
        })
    }
    fun bulbSwitch(id: String, toString: String) {
//        bulbsState[id] = !bulbsState[id] // Toggle the state for the given ID
//        Log.w("tag","${bulbsState[id].toString()}")

        // Calculate the sum of all IDs
        val sumOfTrueKeys = sub_files.filter { it.value } // Filter entries with value `true`
            .keys // Extract the keys
            .map { it.toIntOrNull() ?: 0 } // Convert keys to Int, use 0 if conversion fails
            .sum() // Sum up the keys

        // Build the message with the sum
        val message = "UPDATE_HOME_AUTOMATION_DATA=$sumOfTrueKeys,0,0"
        Log.w("tag", message)
        mqttClient.publish("HA/${registerViewModel.current_mac_id.value}/$/command", message)

        mqttClient.subscribe("HA/HOME_AUTO/status${id}")
        // Show a Toast message when the status changes
//        val statusMessage = if (bulbsState[id]) "Device id $id is on" else "Device id $id is off"
//        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
    }

    init {
        showLogs("ViewModel:", "Created")
        viewModelScope.launch {
            startFetchingData()

        }

    }

    var authRepo: AuthRepo = authRepo
    var hmeAutoRepo: HomeAutoRepo = homeAutoRepo

    fun login(email: String, password: String) {
        showLogs("LOGIN: ", mobNum.value)
        viewModelScope.launch {
            authRepo.login(email, password)
        }
    }

    fun showMacDialog(){
        showMacDialog = true
    }

    fun hideMacDialog(){
        showMacDialog = false
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
        sub_files.clear()
        mSubFolderRes.value = null
        viewModelScope.launch {
            val foldersResponse = hmeAutoRepo.getFolderAndFile( user_id, id)
            foldersResponse?.let {
                showLogs("SUB FOLDERS ARE 2: ", foldersResponse.toString())
//                showLogs("SUB FOLDERS ARE 3: ", foldersResponse.folders.toString())
                mSubFolderRes.value = foldersResponse

                try{
                    sub_folders.addAll(mSubFolderRes.value!!.folders)
                    mSubFolderRes.value!!.files.forEach {
                        sub_files.put(it.channel_name.toString() , false)
                    }

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

    fun createFile(
        applianceName: String,
        MacId: String,
        channelName: String,
        currentParentId: MutableState<String>,
        userId: MutableState<String>
    ) {
        viewModelScope.launch {
            homeAutoRepo.createFile(applianceName, MacId, channelName, currentParentId, userId)
        }
    }

}



