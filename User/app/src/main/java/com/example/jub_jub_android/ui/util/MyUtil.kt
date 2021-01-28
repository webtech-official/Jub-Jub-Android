package com.example.jub_jub_android.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.jub_jub_android.R
import java.io.ByteArrayOutputStream


class MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{

        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun getLaptopTestImage(context: Context): String {
        val byteStream = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.image_laptop)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

}