package com.example.demo.Utility

import android.content.Context
import android.content.SharedPreferences
import com.example.demo.data.PresetData

class sharedDataMushroom(context:Context) {
    private val sharedPrefFile = "myPresetData"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)


    fun commitPreset(ptemp:String,phum:String,pco2:String){
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("preset_temp",ptemp)
        editor.putString("preset_hum",phum)
        editor.putString("preset_co2",pco2)
        editor.apply()
        editor.commit()
    }

    fun getIsFirst():String{
        val tempValue = sharedPreferences.getString("isFirstTime", "NOT")
        return tempValue.toString()
    }

    fun setIsFirst(){
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("isFirstTime","SET")
        editor.apply()
        editor.commit()
    }

    fun getPreset(): PresetData {
        val tempValue = sharedPreferences.getString("preset_temp", "36")
        val humValue = sharedPreferences.getString("preset_hum", "36")
        val co2Value = sharedPreferences.getString("preset_co2", "5000")

        val mPreset = PresetData(tempValue.toString(),humValue.toString(), co2Value.toString())
        return mPreset
    }
}