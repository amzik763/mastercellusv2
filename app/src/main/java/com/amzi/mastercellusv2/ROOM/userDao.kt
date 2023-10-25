package com.amzi.mastercellusv2.ROOM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.amzi.mastercellusv2.models.auth

@Dao
interface userDao {

    @Insert
    suspend fun insertUser(auth: auth)

    @Update
    suspend fun updateUser(auth: auth)

    @Delete
    suspend fun deleteUser(auth: auth)

    @Query("SELECT * FROM auth")
    fun getUser(): LiveData<List<auth>>

}