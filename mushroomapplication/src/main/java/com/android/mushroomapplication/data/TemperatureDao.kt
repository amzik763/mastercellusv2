package com.example.demo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TemperatureDao {
    @Insert
    suspend fun insertTemperature (temperature: Temperature)

    @Query("SELECT * FROM temperature")
    suspend fun getTemperature (): Temperature

}