package com.example.jub_jub_android.model.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.jub_jub_android.entity.dataclass.body.Login

class SharedPref(val context: Context) {

    fun saveAccount(loginData: Login){
        Log.d("TestLog_SharedPref", "id= ")
        return getPref(context).edit().let {
            it.putString("Id", loginData.email)
            it.putString("Pw", loginData.password)
            it.apply()
        }
    }

    fun getAccount(): Login =Login(getPref(context).getString("Id","")!!, getPref(context).getString("Pw", "")!!)

    fun logOut() {
        getPref(context).edit().let {
            it.remove("Id")
            it.remove("Pw")
            it.apply()
        }
    }

    fun isExist(key: String): Boolean = getPref(context).contains(key)

    private fun getPref(context: Context): SharedPreferences =
            context.getSharedPreferences("pref", Context.MODE_PRIVATE)



}