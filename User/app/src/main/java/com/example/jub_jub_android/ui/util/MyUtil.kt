package com.example.jub_jub_android.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import java.io.ByteArrayOutputStream


class MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{

        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }


}