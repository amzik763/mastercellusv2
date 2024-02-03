package com.example.demo.viewmodels

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.Utility.sharedDataMushroom
import com.example.demo.data.modelData
import com.example.demo.ui.mqtt.ClientHelper
import com.example.demo.ui.mqtt.MAC_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
class SecondViewModel(
     context: Context,
     wifiManager: WifiManager
     ) : ViewModel() {

    //wifi pending
    var isWifiPending:Boolean = false
    var wifiCounter = 3

    //Preset pending
    var isPresetPending:Boolean = false
    var PresetCounter = 3

    //Temporary values
    var t = "0"
    var h = "0"
    var c = "0"



     val ct = context
        var myContext = context
    var currentSSID = ""
    var checkPermission by mutableStateOf(0)
    val mqttClient = ClientHelper(context.applicationContext,this)

     //shared Preferences
     val msharedDataMushroom =  sharedDataMushroom(context)

         //wifi var
         var wname =  MutableStateFlow("Waiting..")

     //new variable
    var temp2 =  MutableStateFlow("0")
     var hum2 = MutableStateFlow<String>("0")
     var co22 = MutableStateFlow<String>("0")

     var maxtemp2 = MutableStateFlow<String>("0")
     var maxhum2 = MutableStateFlow<String>("0")
     var maxco22 = MutableStateFlow<String>("0")

    var wifiList: MutableList<String> = mutableStateListOf<String>()

    lateinit var responseData:modelData
     var temperature by mutableStateOf("0")
          private set

     var humidity by mutableStateOf("0")
          private set

     var co2 by mutableStateOf("0")
          private set

     var maxTemperature by mutableStateOf("0")
          private set

     var maxHumidity by mutableStateOf("0")
          private set

     var maxCo2 by mutableStateOf("0")
          private set

    var isDialogShown by mutableStateOf(false)
        private set
    var dialogText by mutableStateOf("JYOTI")
        private set
    fun onByClick() {
        isDialogShown = true
    }
    fun onDismissDialog() {
        isDialogShown = false
    }


    //Network Dialog2
    var isNetworkDialogShown by mutableStateOf(false)
        private set
    var NetworkDialogText by mutableStateOf("")

    var NetworkDialogTextHeader by mutableStateOf("")
    fun showNetworkDialog() {
        isNetworkDialogShown = true
    }
    fun hideNetworkDialog() {
        isNetworkDialogShown = false
    }

    fun setMyDialogText(s: String) {
        dialogText = s
    }
    init {
          viewModelScope.launch {
               Log.d("not","find me")
               startFetchingData2()
          }


     }
    private fun startFetchingData2() {
         //if online
          mqttClient.connect(mqttClient.context!!,temp2,hum2,co22,maxtemp2,maxhum2,maxco22,msharedDataMushroom)
     }

    //setting maximum value of ...
     fun pubTemperature ( temperature: String ) {
          if (mqttClient.isConnected()){
               var formatedData = "UPDATE_PRESET_DATA=$temperature,$maxHumidity,$maxCo2"
               if(temperature.isNotEmpty())
                    mqttClient.publish("Mashroom/$MAC_ID/$/command",formatedData)
          }
     }

 //    setting maximum value of ...
     fun pubTempHumidCo2 ( temperature: String, humidity: String, co2: String) {

     if(isPresetPending){
         Toast.makeText(ct, "Update in Progress Already", Toast.LENGTH_LONG).show()

            return

     }

     Toast.makeText(ct, "Setting values: may take upto 20 sec", Toast.LENGTH_LONG).show()

    try {

        if (Integer.parseInt(temperature) > 100) {
            Toast.makeText(ct, "Temperature cannot exceed 100°C", Toast.LENGTH_LONG).show()
            return

        } else if (Integer.parseInt(temperature) < 1) {
            Toast.makeText(ct, "Temperature should be greater than 1°C", Toast.LENGTH_LONG).show()
            return
        }

        if (Integer.parseInt(humidity) > 100) {
            Toast.makeText(ct, "Humidity cannot exceed 100%", Toast.LENGTH_LONG).show()
            return

        } else if (Integer.parseInt(humidity) < 1) {
            Toast.makeText(ct, "Humidity should be greater than 1%", Toast.LENGTH_LONG).show()
        return
        }
//app crashing integer format exception
        if (Integer.parseInt(co2) > 30000) {
            Toast.makeText(ct, "Temperature cannot exceed 30000ppm", Toast.LENGTH_LONG).show()
            return

        } else if (Integer.parseInt(co2) < 100) {
            Toast.makeText(ct, "Temperature should be greater than 100ppm", Toast.LENGTH_LONG)
                .show()
            return
        }

        if (mqttClient.isConnected()){

            var formatedData = "UPDATE_PRESET_DATA=$temperature,$humidity,$co2"
            Log.d("AMZI FOR: ",formatedData)

            if(temperature.isNotEmpty()&&humidity.isNotEmpty()&&co2.isNotEmpty()) {
                t = temperature
                h = humidity
                c = co2
                msharedDataMushroom.commitPreset(temperature, humidity, co2)
                mqttClient.publish("Mashroom/$MAC_ID/$/command", formatedData)
                isPresetPending = true
                PresetCounter = 3

            }
        }else{
            Toast.makeText(ct,"Client not connected",Toast.LENGTH_LONG).show()

        }

    } catch (e:Exception){
        Toast.makeText(ct,"Please Enter some values",Toast.LENGTH_LONG).show()
    }
     }


    var updateText by mutableStateOf("JYOTI")
    fun updateTextFieldValue(newValue: String){
        updateText = newValue
    }

    fun updateWifiDetail(password:String){

        Log.d("AMAM:",password)
        var formatedData = "UPDATE_WIFI_CREDENTIALS=$currentSSID,$password"
        if (password.isNullOrEmpty()){
            isWifiPending = false
            wifiCounter = 3
            Toast.makeText(myContext,"Please enter Password",Toast.LENGTH_LONG).show()
        }else{
//            mqttClient.publish("PERFECT/$MAC_ID/$/command", formatedData)
            mqttClient.publish("Mashroom/$MAC_ID/$/command", formatedData)
        }
    }
    fun showToast() {
//        Toast.makeText(conte,"Please Enable Location",Toast.LENGTH_LONG).show()
    }
}