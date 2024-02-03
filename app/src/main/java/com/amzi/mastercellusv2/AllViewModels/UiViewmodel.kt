package com.amzi.mastercellusv2.AllViewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amzi.mastercellusv2.utility.KEY_HOMEAUTO_MACID
import com.amzi.mastercellusv2.utility.PREFERNCES_NAME

class UiViewmodel(context: Context) : ViewModel() {


    val mContext = context
    var showHomeMacId by mutableStateOf(false)
        private set

    var showMushMacId by mutableStateOf(false)
        private set

    fun toggleHomeMacIdVisibility() {
        showHomeMacId = !showHomeMacId
    }

    fun toggleMushMacIdVisibility() {
        showMushMacId = !showMushMacId
    }


    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences(PREFERNCES_NAME, Context.MODE_PRIVATE)

    fun saveHomeMacId(macId: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_HOMEAUTO_MACID, macId)
        editor.apply()
    }

    fun getMushMacId(): String {
        return sharedPreferences.getString(KEY_HOMEAUTO_MACID, "") ?: ""
    }


}
