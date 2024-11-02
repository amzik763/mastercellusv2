package com.amzi.mastercellusv2.allViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapplication.mqtt.MqttClientHelper
import com.example.homeapplication.utility.KEY_HOMEAUTO_MACID
import com.example.homeapplication.utility.PREFERNCES_NAME
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class HomeAppViewModel(private val context: Context):ViewModel(){

        val mContext = context

        private val mqttClient = MqttClientHelper(context.applicationContext)
    var bulbsState = mutableListOf<Boolean>()
    var acState = mutableListOf<Boolean>()//fan and ac state

    init{
        viewModelScope.launch {
            Log.d("not","find me if u can")
            startFetchingData()
        }
        // Populate the list with initial states
        if (bulbsState.isEmpty()) {
            for(i in 0..64){
                bulbsState.add(false)
                acState.add(false)
            }
        }
    }

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

    fun bulbSwitch(id: Int) {

        val homeMac = getMacId(KEY_HOMEAUTO_MACID)

        try {
            bulbsState[id] = !bulbsState[id] // Toggle the state for the given ID
            Log.w("tag", bulbsState[id].toString())

            // Calculate the sum of all IDs
            val sumOfIds = bulbsState.indices.sumBy { if (bulbsState[it]) it else 0 }

            // Build the message with the sum
            val message = "UPDATE_HOME_AUTOMATION_DATA=$sumOfIds,0,0"
            Log.w("tag", message)
            mqttClient.publish("HA/$homeMac/$/command", message)

            mqttClient.subscribe("HA/HOME_AUTO/status${id}")
            // Show a Toast message when the status changes
            val statusMessage =
                if (bulbsState[id]) "Device id $id is on" else "Device id $id is off"
            Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
        }catch (e:Exception){

            Log.d("bulbSwitch Error: ",e.message.toString())
            e.printStackTrace()
        }
    }


    fun acSwitch(id: Int) {

        val homeMac = getMacId(KEY_HOMEAUTO_MACID)

        acState[id] = !acState[id] // Toggle the state for the given ID
        Log.w("tag","${acState[id].toString()}")

        // Calculate the sum of all IDs
        val sumOfIds = acState.indices.sumBy { if (acState[it]) it else 0 }

        // Build the message with the sum
        val message = "UPDATE_HOME_AUTOMATION_DATA=$sumOfIds,0,0"
        Log.w("tag", message)
        mqttClient.publish("HA/$homeMac/$/command", message)

        mqttClient.subscribe("HA/HOME_AUTO/status${id}")
        // Show a Toast message when the status changes
        val statusMessage = if (bulbsState[id]) "Device id $id is on" else "Device id $id is off"
        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
    }


    //SHARED PREFERENCES
    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences(PREFERNCES_NAME, Context.MODE_PRIVATE)

    fun getMacId(key: String): String {
        return sharedPreferences.getString(key, "Not Registered") ?: "Not Registered"
    }
}
