package com.alodok.testapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersModel::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {

        @Volatile
        private var instance: RoomDB? = null
        private var LOCK = Any()

        fun getInstance(context: Context): RoomDB {

            synchronized(LOCK) {
                instance?.let { return it }
                instance = Room
                    .databaseBuilder(context.applicationContext, RoomDB::class.java, "user_db")
                    .build()
                return instance!!
            }
        }
    }

}