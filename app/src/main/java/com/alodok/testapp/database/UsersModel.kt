package com.alodok.testapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val pass_user: String,
    val handphone: String,
    val gender: Int? = null

)