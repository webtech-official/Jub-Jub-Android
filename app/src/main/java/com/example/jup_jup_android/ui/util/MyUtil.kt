package com.example.jup_jup_android.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import java.io.ByteArrayOutputStream


class MyUtil {
    var baseString = ""

    fun convertBase64ToBitmap(base64Code: String): Bitmap{

        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun setBaseString(context: Context){
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
        val byteStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        baseString = Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    @JvmName("getBaseString1")
    fun getBaseString(): String {
        return baseString
    }


}