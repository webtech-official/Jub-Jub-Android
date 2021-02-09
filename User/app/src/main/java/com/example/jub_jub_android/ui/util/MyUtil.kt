package com.example.jub_jub_android.ui.util

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File


class MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{
        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun convertFileToBase64(context:Context, imageString: String): String? {
        var imageFile = File(imageString)
        return convertBitmapToBase64(convertFileToBitmap(imageFile)!!)
        val bigPictureBitmap = BitmapFactory.decodeResource(context.getResources(), imageString)

    }

    fun convertFileToBitmap(imageFile: File): Bitmap? {
            return BitmapFactory.decodeFile(imageFile.path)
    }

    fun convertBitmapToBase64(bitmap: Bitmap): String? {
        val byteStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }




}