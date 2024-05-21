package com.amzi.mastercellusv2.utility

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class myApplication:Application() {

/*
 override fun onCreate() {
        super.onCreate()
    }


    init{
        instance = this
    }

    companion object{
        private var instance:myApplication?=null

        fun getContext():Context{
            return instance!!.applicationContext
        }
    }
*/


}
