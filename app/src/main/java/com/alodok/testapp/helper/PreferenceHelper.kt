package com.alodok.testapp.helper

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val USER_NAME = "USER_NAME"
    private const val IS_LOGIN = "IS_LOGIN"

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.nameuser
        get() = getString(USER_NAME, "")
        set(value) {
            editMe {
                it.putString(USER_NAME, value)
            }
        }

    var SharedPreferences.isLogin
        get() = getString(IS_LOGIN, "")
        set(value) {
            editMe {
                it.putString(IS_LOGIN, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}