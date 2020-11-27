package com.alodok.testapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alodok.testapp.database.UsersModel
import com.alodok.testapp.repository.UserRepository

class UserViewModel : ViewModel() {

    var liveDataUsers: LiveData<UsersModel>? = null

    fun insertData(
        context: Context,
        name: String,
        password: String,
        handphone: String,
        isGender: Int
    ) {
        UserRepository.insertData(context, name, password, handphone, isGender)
    }

    fun getUserData(context: Context, name: String): LiveData<UsersModel>? {
        liveDataUsers = UserRepository.getUsersData(context, name)
        return liveDataUsers
    }
}