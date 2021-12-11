package com.jubjub.user.util

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.Window
import com.jubjub.user.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat


object MyUtil {

    fun getMotorTestImage(context: Context): String{
        val byteStream = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(context.resources, com.jubjub.user.R.drawable.imageex)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun convertBase64ToBitmap(base64Code: String): Bitmap{
        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun convertFileToBase64(imageString: String): String? {
        var imageFile = File(imageString)
        return convertBitmapToBase64(convertFileToBitmap(imageFile)!!)
        //val bigPictureBitmap = BitmapFactory.decodeResource(context.getResources(), imageString)
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

    fun makeBaseDialog(context: Context, dialogName: String): Dialog {
        var dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_rent_request_complete)

        return dialog
    }

    fun getRentStatus(status: String): String {
        return when(status){
            "ROLE_Waiting" -> "승인대기"
            "ROLE_Accept" -> "대여승인"
            "ROLE_Reject" -> "거절"
            "ROLE_Rental" -> "대여중"
            "ROLE_Return" -> "반납완료"
            else -> "?"
        }
    }

    fun getDate(dateString: String): String {
        //2021-09-06  T02:12:14.890Z   "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

        val dateFormat = SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss")
        val date = dateFormat.parse(dateString)

        val myFormat = SimpleDateFormat("yyyy.mm.dd")
        val myDate = myFormat.format(date)

        Log.d("TestLog", "string = $dateString date = $date  myDate = $myDate }")


        return myDate
    }

}