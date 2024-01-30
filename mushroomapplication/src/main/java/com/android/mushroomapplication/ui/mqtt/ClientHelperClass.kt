package com.example.demo.ui.mqtt

import android.content.Context
import android.util.Log
import com.android.mqtt.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttClientHelper(context: Context?) {

    companion object {
        const val TAG = "MqttClientHelper"
    }

    var mqttAndroidClient: MqttAndroidClient? = null

    val serverUri = "tcp://$MQTT_HOST:$MQTT_PORT"
    private val clientId: String = MqttClient.generateClientId()

    fun setCallback(callback: MqttCallbackExtended?) {
        mqttAndroidClient!!.setCallback(callback)
    }

    init {
        if(mqttAndroidClient == null){
            mqttAndroidClient = MqttAndroidClient(context, serverUri, clientId)
            mqttAndroidClient!!.setCallback(object : MqttCallbackExtended {

                override fun connectComplete(b: Boolean, s: String) {
                    Log.w(TAG, s)
                    Log.w(TAG, "connected")
                }

                override fun connectionLost(throwable: Throwable) {
                    Log.w(TAG, "connection lost")
                }
                @Throws(Exception::class)
                override fun messageArrived(
                    topic: String,
                    mqttMessage: MqttMessage
                ) {
                    Log.w(TAG, mqttMessage.toString())
                }

                override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
                    Log.w(TAG, "ack that msg is find")
                }
            })
            connect()
        }

    }

    private fun connect() {
        val mqttConnectOptions = MqttConnectOptions()
        Log.w(TAG, "Test step 1")
        mqttConnectOptions.isAutomaticReconnect = CONNECTION_RECONNECT
        mqttConnectOptions.isCleanSession = CONNECTION_CLEAN_SESSION
//        mqttConnectOptions.userName = SOLACE_CLIENT_USER_NAME
//        mqttConnectOptions.password = SOLACE_CLIENT_PASSWORD.toCharArray()
        mqttConnectOptions.connectionTimeout = CONNECTION_TIMEOUT
        mqttConnectOptions.keepAliveInterval = CONNECTION_KEEP_ALIVE_INTERVAL
        Log.w(TAG, "Test step 2")
        try {
            mqttAndroidClient!!.connect(mqttConnectOptions, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    val disconnectedBufferOptions =
                        DisconnectedBufferOptions()
                    disconnectedBufferOptions.isBufferEnabled = true
                    disconnectedBufferOptions.bufferSize = 100
                    disconnectedBufferOptions.isPersistBuffer = false
                    disconnectedBufferOptions.isDeleteOldestMessages = false
                    mqttAndroidClient!!.setBufferOpts(disconnectedBufferOptions)
//                    val macId = "70:04:1D:55:7C:28"
                     val macId = "70:04:1D:55:94:9A"

                    subscribe("Mashroom/Green/status",1)
                    subscribe("Mashroom/$MAC_ID/$/command",1)
                    subscribe("humidity",1)
                    subscribe("temperature",1)
                    subscribe("co2",1)

//                    subscribe("Mashroom/Green/status",0)
//                    subscribe("Mashroom/$macId/$/command",0)
//                    subscribe("humidity",0)
//                    subscribe("temperature",0)
//                    subscribe("co2",0)


                    subscribe("maxTemperature")
                    subscribe("maxHumidity")
                    subscribe("maxCo2")
                }

                override fun onFailure(
                    asyncActionToken: IMqttToken,
                    exception: Throwable
                ) {
                    Log.w(TAG, "Failed to connect to: $serverUri ; $exception")
                }
            })
        } catch (ex: MqttException) {
            ex.printStackTrace()
        }
        Log.w(TAG, "Test step 3")
    }

    fun subscribe(subscriptionTopic: String, qos: Int = 0) {
        try {
            mqttAndroidClient!!.subscribe(subscriptionTopic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.w(TAG, "Subscribed to topic '$subscriptionTopic'")
                }

                override fun onFailure(
                    asyncActionToken: IMqttToken,
                    exception: Throwable
                ) {
                    Log.w(TAG, "Subscription to topic '$subscriptionTopic' failed!")
                }
            })
        } catch (ex: MqttException) {
            System.err.println("Exception whilst subscribing to topic '$subscriptionTopic'")
            ex.printStackTrace()
        }
    }

    fun publish(topic: String, msg: String, qos: Int = 0) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            mqttAndroidClient!!.publish(topic, message.payload, qos, false)
            Log.d(TAG, "Message published to topic `$topic`: $msg")
        } catch (e: MqttException) {
            Log.d(TAG, "Error Publishing to $topic: " + e.message)
            e.printStackTrace()
        }

    }

    fun isConnected() : Boolean {
        return mqttAndroidClient!!.isConnected
    }

    fun destroy() {
        mqttAndroidClient!!.unregisterResources()
        mqttAndroidClient!!.disconnect()
    }
}
