package com.example.demo.ui.mqtt

import android.content.Context
import android.util.Log
import com.example.demo.Utility.sharedDataMushroom
import com.example.demo.data.PresetData
import com.example.demo.data.modelData
import com.example.demo.viewmodels.SecondViewModel
import com.google.gson.GsonBuilder
import com.somsakelect.android.mqtt.MqttAndroidClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class ClientHelper(context: Context?, secondViewModel: SecondViewModel) {


    var svm:SecondViewModel = secondViewModel
    lateinit var mqttClient: MqttAndroidClient
    var context = context

    lateinit var temp:String
    lateinit var hum:String
    lateinit var co2:String


    // TAG
    companion object {
        const val TAG = "Amzi: AndroidMqttClient"
    }

    val serverUri:String = "tcp://3.110.187.253:1883"
    private val clientId: String = MqttClient.generateClientId()


    fun connect(
        context: Context,
        temp2: MutableStateFlow<String>,
        hum2: MutableStateFlow<String>,
        co22: MutableStateFlow<String>,
        maxtemp2: MutableStateFlow<String>,
        maxhum2: MutableStateFlow<String>,
        maxco22: MutableStateFlow<String>,
        msharedDataMushroom: sharedDataMushroom

    ){
        val serverURI = serverUri
        Log.d(ClientHelper.TAG, "Starting connection")

        mqttClient = MqttAndroidClient(context, serverURI, clientId)
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d(ClientHelper.TAG, "Receive message: ${message.toString()} from topic: $topic")

                when(topic){
                    "temperature" ->{
                        // temp2.value = message.toString()
                    }
                    "humidity" ->{
                        hum = message.toString()
                    }
                    "co2" ->{
                        co2 = message.toString()
                    }
                    "maxTemperature" ->{
//                              maxTemperature = mess.toString()
                    }
                    "maxHumidity" ->{
//                              maxHumidity = mess.toString()
                    }
                    "maxCo2" ->{
//                              maxCo2 = mess.toString()
                    }
                    MAC_ID -> {
                        Log.d(TAG,"TEMP2: " + "largeData")
                        val gson = GsonBuilder().create()
                        val responseData = gson.fromJson(message.toString(),modelData::class.java)
                        temp2.value = responseData.d.LIVE_TEMP.toString()
                        hum2.value = responseData.d.LIVE_HUMIDITY.toString()
                        co22.value = responseData.d.LIVE_CO2.toString()
                        svm.wname.value = responseData.info.ssid

                        if(svm.isPresetPending && svm.PresetCounter>0){
                            Log.d("UPDATE: ","PRESET PASSED")

                            if(!svm.t.equals(responseData.d.PRESET_TEMP.toString())||!svm.h.equals(responseData.d.PRESET_HUMIDITY.toString())||!svm.c.equals(responseData.d.PRESET_CO2.toString()))
                            {
                                Log.d("UPDATE: ","PRESET NOT CORRECT")
//                                Log.d("UPDATE: ",svm.t + " " + )

                                svm.NetworkDialogTextHeader = "Retrying.."
                                svm.NetworkDialogText = "Trying to fetch values again"
                                svm.showNetworkDialog()
                            }else{
                                Log.d("UPDATE: ","PRESET CORRECT")

                                svm.PresetCounter = 3
                                svm.NetworkDialogTextHeader = "Success.."
                                svm.NetworkDialogText = "Values are updated"
                                svm.showNetworkDialog()
                                svm.isPresetPending = false
                                svm.PresetCounter = 3
                                GlobalScope.launch {
                                    delay(3000)
                                    svm.hideNetworkDialog()
                                }
                            }

                        }else if(svm.isPresetPending && svm.PresetCounter == 0){
                            Log.d("UPDATE: ","PRESET FAILED")
                            svm.isPresetPending = false
                            svm.PresetCounter--
                            svm.NetworkDialogTextHeader = "Failed!!"
                            svm.NetworkDialogText = "Preset values not updated. Try Again"
                            svm.showNetworkDialog()
                            GlobalScope.launch {
                                delay(3000)
                                svm.hideNetworkDialog()
                            }

                        }

                        if(svm.isWifiPending && svm.wifiCounter>0) {

                            if (responseData.info.ssid.equals(svm.currentSSID)) {
                                svm.isWifiPending = false
                                svm.NetworkDialogTextHeader = "Wifi Updated";
                                svm.NetworkDialogText = "Wifi setup completed";
                                svm.wifiCounter = 0
                                GlobalScope.launch {
                                    delay(3000)
                                    svm.hideNetworkDialog()
                                }
                            }else{
                                svm.wifiCounter--
                                svm.NetworkDialogTextHeader = "Retrying..";
                                svm.NetworkDialogText = "Retrying to update wifi";
                            }

                        }else if(svm.isWifiPending && svm.wifiCounter==0){
                            svm.isWifiPending = false
                            svm.NetworkDialogTextHeader = "Wifi Not Updated";
                            svm.NetworkDialogText = "Wifi setup failed!! Retry again";
                            GlobalScope.launch {
                                delay(3000)
                                svm.hideNetworkDialog()
                            }
                        }

                        var shouldUpdateValues = false;
                        val mPresetData: PresetData = msharedDataMushroom.getPreset()
                        if(Integer.parseInt(mPresetData.temp.toString())>=100){
                            mPresetData.temp = "65"
                            shouldUpdateValues = true;
                        }

                        if(Integer.parseInt(mPresetData.hum.toString())>=100){
                            mPresetData.hum = "85"
                            shouldUpdateValues = true;
                        }

                        if(Integer.parseInt(mPresetData.co2.toString())>=100){
                            mPresetData.co2 = "500"
                            shouldUpdateValues = true;
                        }


                       /* if(shouldUpdateValues){
                            var formatedData = "UPDATE_PRESET_DATA=${mPresetData.temp.toString()},${mPresetData.hum.toString()},${mPresetData.co2.toString()}"

                            maxtemp2.value = mPresetData.temp.toString()
                            maxhum2.value = mPresetData.hum.toString()
                            maxco22.value = mPresetData.co2.toString()
                            publish("Mashroom/$MAC_ID/$/command", formatedData)
                            shouldUpdateValues = false
                            Log.d("AMZI, SHOULD UPDATE: ","called")

                        }*/
//                        if(msharedDataMushroom.getIsFirst()=="NOT"){
//                            msharedDataMushroom.commitPreset("40","60","1000")
//                            msharedDataMushroom.setIsFirst()

//
                        if(responseData.d.counter<=3){
//                            val mPresetData: PresetData = msharedDataMushroom.getPreset()

                            var formatedData = "UPDATE_PRESET_DATA=${mPresetData.temp.toString()},${mPresetData.hum.toString()},${mPresetData.co2.toString()}"
                            Log.d("AMZI FOR: + ${responseData.d.counter}",formatedData)

                            maxtemp2.value = mPresetData.temp.toString()
                            maxhum2.value = mPresetData.hum.toString()
                            maxco22.value = mPresetData.co2.toString()

                            if(!svm.isPresetPending)
                                svm.pubTempHumidCo2(maxtemp2.value,maxhum2.value,maxco22.value)
//                          publish("Mashroom/$MAC_ID/$/command", formatedData)

                            Log.d("AMZI6: counter + ${responseData.d.counter} ",mPresetData.temp + " " + mPresetData.hum + " " + mPresetData.co2)

                        }else{

                            msharedDataMushroom.commitPreset(responseData.d.PRESET_TEMP.toString(),responseData.d.PRESET_HUMIDITY.toString(),responseData.d.PRESET_CO2.toString())
                            maxtemp2.value = responseData.d.PRESET_TEMP.toString()
                            maxhum2.value = responseData.d.PRESET_HUMIDITY.toString()
                            maxco22.value = responseData.d.PRESET_CO2.toString()

                            Log.d(TAG,"AMZI FOR: counter is greater than 2 " + hum2.value)
//                        Log.d(TAG,"TEMP2: " + "largeData")

                            Log.d(TAG,"TEMPO: " + responseData.d.LIVE_CO2)
                            Log.d(TAG,"TEMPO: " + responseData.d.LIVE_HUMIDITY)
                            Log.d(TAG,"TEMPO: " + responseData.d.LIVE_TEMP)
                        }


                    }
                }


            }

            override fun connectionLost(cause: Throwable?) {
                Log.d(ClientHelper.TAG, "Connection lost ${cause.toString()}")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                if (token != null && token.isComplete) {
                    println("Message published successfully.")
                }
            }

            override fun connectComplete(reconnect: Boolean, serverURI: String?) {

                Log.d(ClientHelper.TAG, "reconnect: "+reconnect)
                 subscribe(MAC_ID,1)
                subscribe("PERFECT/$MAC_ID/$/command")
                subscribe("Mashroom/$MAC_ID/$/command")
            }

        })

        val options = MqttConnectOptions()
        options.isAutomaticReconnect = CONNECTION_RECONNECT
        options.isCleanSession = CONNECTION_CLEAN_SESSION
        options.connectionTimeout = CONNECTION_TIMEOUT
        options.keepAliveInterval = CONNECTION_KEEP_ALIVE_INTERVAL
        try {
            mqttClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "Connection success")
                    subscribe(MAC_ID,1)
                    subscribe("PERFECT/$MAC_ID/$/command")
                    subscribe("Mashroom/$MAC_ID/$/command")
//                    subscribe("Mashroom/Green/status",1)
//                    subscribe("Mashroom/70:04:1D:55:94:9A/$/command",1)
//                    subscribe("humidity",1)
//                    subscribe("temperature",1)
//                    subscribe("co2",1)
//                    subscribe("maxTemperature")
//                    subscribe("maxHumidity")
//                    subscribe("maxCo2")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Connection failure")
                    Log.d(TAG, "Connection failure" + exception?.fillInStackTrace())
                    Log.d(TAG, "Connection failure" + exception?.message.toString())
                    exception?.printStackTrace()
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }

    }

    fun subscribe(topic: String, qos: Int = 1) {
        Log.d(ClientHelper.TAG, "Starting subscription")

        try {
            mqttClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "Subscribed to $topic")
//                    publish(topic,"39")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Failed to subscribe $topic")

                    exception?.printStackTrace()
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }


    fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
        Log.d(ClientHelper.TAG, "Starting publish")


        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttClient.publish(topic, message, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "$msg published to $topic")
                    if(msg.contains("WIFI",true)){

                        Log.d("Wifi pushed","Wifi pushed")
                        svm.NetworkDialogTextHeader = "Updating Wifi.."
                        svm.NetworkDialogText = "Command received to controller"
                        svm.showNetworkDialog()
                    }else if (msg.contains("PRESET",false)){
                        svm.NetworkDialogText = "Updating new values"
                        svm.NetworkDialogTextHeader = "Preset values received"
                        svm.showNetworkDialog()
                    }
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Failed to publish $msg to $topic")

                    if(msg.contains("WIFI",true)){
                        svm.isWifiPending = false
                        svm.wifiCounter = 3
                        Log.d("Wifi failed","Wifi failed")
                        svm.NetworkDialogTextHeader = "No Connectivity"
                        svm.NetworkDialogText = "Make sure mobile data is on"
                        svm.showNetworkDialog()
                    }else if (msg.contains("PRESET",false)){
                        svm.NetworkDialogText = "Failed!! Try again"
                        svm.NetworkDialogTextHeader = "Preset values"
                        svm.showNetworkDialog()
                        svm.isPresetPending = false
                        svm.PresetCounter = 3
                    }

                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            Log.d(TAG, "TRY RECONNECTING $e")
            svm.isWifiPending = false
            svm.wifiCounter = 3
            svm.isPresetPending = false
            svm.PresetCounter = 3
            svm.NetworkDialogTextHeader = "Failed!! Try again"
            svm.NetworkDialogText = "Make sure stable network is available"
            svm.showNetworkDialog()
        }
    }


    fun disconnect() {
        try {
            mqttClient.disconnect(null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "Disconnected")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Failed to disconnect")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun isConnected() : Boolean {
        return mqttClient!!.isConnected
    }

    fun destroy() {
        mqttClient!!.unregisterResources()
        mqttClient!!.disconnect()
    }

}
