package com.example.jup_jup_android.ui.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64


class MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{
            val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }


}