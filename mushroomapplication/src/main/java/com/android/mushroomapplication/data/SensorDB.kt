package com.example.demo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database( entities = [Temperature::class], version = 1)
abstract class SensorDB: RoomDatabase() {
    abstract fun temperatureDao (): TemperatureDao

}