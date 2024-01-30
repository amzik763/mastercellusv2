package com.example.demo.data

import com.google.gson.annotations.SerializedName

data class D(
    @SerializedName("Environment Data")
    val Environment_Data: String,

    @SerializedName("Firmware Version Revision")
    val Firmware_Version_Revision: String,

    @SerializedName("LIVE CO2")
    val LIVE_CO2: Int,

    @SerializedName("LIVE HUMIDITY")
    val LIVE_HUMIDITY: Int,

    @SerializedName("LIVE TEMP")
    val LIVE_TEMP: Int,

    @SerializedName("PRESET CO2")
    val PRESET_CO2: Int,

    @SerializedName("PRESET HUMIDITY")
    val PRESET_HUMIDITY: Int,

    @SerializedName("PRESET TEMP")
    val PRESET_TEMP: Int,
    val counter: Int,
    val heap: Int,
    val millis: Int,
    val rssi: Int,
    val subscription: Int,
    val updateInterval: Int
)