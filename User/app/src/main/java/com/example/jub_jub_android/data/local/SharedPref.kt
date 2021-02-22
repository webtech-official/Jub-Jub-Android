package com.example.jub_jub_android.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.jub_jub_android.entity.dataclass.body.Login

class SharedPref(val context: Context) {

    fun saveAccount(id: String, password: String){
        Log.d("TestLog_SharedPref", "id= ")
        return getPref(context).edit().let {
            it.putString("Id", id)
            it.putString("Pw", password)
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