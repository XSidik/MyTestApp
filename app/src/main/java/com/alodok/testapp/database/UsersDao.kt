package com.alodok.testapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(userModel: UsersModel)

    @Query("SELECT * FROM UsersModel WHERE username =:userName")
    fun getUserName(userName: String?): LiveData<UsersModel>

//    @Query("SELECT * FROM UsersModel WHERE id =:id")
//    suspend fun getUserID(id: Int): List<UsersModel>

}