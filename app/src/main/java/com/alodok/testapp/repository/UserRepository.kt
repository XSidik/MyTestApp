package com.alodok.testapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alodok.testapp.database.RoomDB
import com.alodok.testapp.database.UsersModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object {

        var roomDB: RoomDB? = null
        var userModel: LiveData<UsersModel>? = null

        fun initializeDB(context: Context): RoomDB {
            return RoomDB.getInstance(context)
        }

        /*addUsers*/
        fun insertData(
            context: Context,
            name: String,
            password: String,
            handphone: String,
            isGender: Int
        ) {
            roomDB = initializeDB(context)
            CoroutineScope(IO).launch {
                val usersData = UsersModel(0,name, password, handphone, isGender)
                roomDB!!.usersDao().addUsers(usersData)
            }
        }

        /*getUsers*/
        fun getUsersData(context: Context, name: String): LiveData<UsersModel>? {
            roomDB = initializeDB(context)
            userModel = roomDB!!.usersDao().getUserName(name)
            return userModel
        }
    }
}