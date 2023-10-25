package com.amzi.mastercellusv2.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amzi.mastercellusv2.models.auth

@Database(entities = [auth::class], version = 1)
abstract class authDatabase: RoomDatabase() {

    abstract fun authDao():userDao

    companion object{

        @Volatile
        private var ROOMINSTANCE:authDatabase?= null

            fun getDatabase(context:Context):authDatabase{

                if(ROOMINSTANCE==null){
                    synchronized(this){
                        ROOMINSTANCE = Room.databaseBuilder(context,
                            authDatabase::class.java,
                            "authDB").build()
                    }
                }

                return ROOMINSTANCE!!

            }

    }

}