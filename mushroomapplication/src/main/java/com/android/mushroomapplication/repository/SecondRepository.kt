package com.example.demo.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.demo.ui.mqtt.MqttClientHelper
import com.example.demo.viewmodels.MqttDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.eclipse.paho.client.mqttv3.*

class SecondRepository (context: Context){
    // MQTT client
    private val mqttClient = MqttClientHelper(context.applicationContext)

    private val _state = MutableStateFlow(MqttDataState())
    val state: StateFlow<MqttDataState> = _state

    fun observeDataState(): StateFlow<MqttDataState> {
        return _state
    }
    suspend fun startFetchingData() {
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String) {
                Log.w(MqttClientHelper.TAG, "MQTT reconnect...$reconnect")
                mqttClient.publish("temperature","39")
                mqttClient.publish("humidity","39")
                mqttClient.publish("co2","39")
            }

            override fun connectionLost(cause: Throwable) {
                Log.e(MqttClientHelper.TAG, "MQTT lost..." + cause.message)
                val st = "MQTT lost! " + cause.message
            }

            override fun messageArrived(topic: String, message: MqttMessage) {
                val mess = message.toString()
                val log = String.format("MQTT RX [%s]: %s", topic, mess)
                when(topic){
                    "temperature" ->{
                        _state.value.temperature = mess.toString()
                    }
                    "humidity" ->{
                        _state.value.humidity = mess.toString()
                    }
                    "co2" ->{
                        _state.value.co2 = mess.toString()
                    }
                }
            //    Log.d("not","temp: ${_state.value.temperature} hum: ${_state.value.humidity} co2: ${_state.value.co2}")
//                Log.w(MqttClientHelper.TAG, log)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {
                Log.w(MqttClientHelper.TAG, "Publish success...")
            }
        })
    }

        // Subscribe to the MQTT topic
//        mqttClient.subscribe(mqttTopic) { topic, message ->
//            // When a new message is received, update the state
//            val receivedData = MqttDataState(message.toString()) // Parse the message to MqttDataState
//            _state.postValue(receivedData)
//        }
//    }

    suspend fun stopFetchingData() {
        // Unsubscribe and disconnect from the MQTT broker
//        mqttClient.unsubscribe(mqttTopic)
//        mqttClient.disconnect()
    }

}
