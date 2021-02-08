package com.example.jub_jub_android.ui.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log


class MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{
        Log.d("TestLog_MyUtil", "${base64Code}")
        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }


}