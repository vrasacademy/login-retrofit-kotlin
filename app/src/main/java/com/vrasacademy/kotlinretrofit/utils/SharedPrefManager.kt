package com.vrasacademy.kotlinretrofit.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(var context: Context?) {

    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "SharedPreferences"

    private val IS_LOGIN = "is_login"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }


    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun setToken(token: String?) {
        editor?.putString("token", token)
        editor?.commit()
    }
    fun getToken(): String? {
        return pref?.getString("token", "")
    }

    fun setTokenType(tokenType: String?) {
        editor?.putString("tokenType", tokenType)
        editor?.commit()
    }
    fun getTokenType(): String? {
        return pref?.getString("tokenType", "")
    }

    fun setName(name: String?) {
        editor?.putString("name", name)
        editor?.commit()
    }
    fun getName(): String? {
        return pref?.getString("name", "")
    }
    fun setEmail(email: String?) {
        editor?.putString("email", email)
        editor?.commit()
    }
    fun getEmail(): String? {
        return pref?.getString("email", "")
    }
    fun setId(id: Int?){
        if (id != null) {
            editor?.putInt("id",id)
        }
        editor?.commit()
    }
    fun getId():Int?{
        return pref?.getInt("id",0)
    }
    fun removeData() {
        editor?.clear()
        editor?.commit()
    }
}