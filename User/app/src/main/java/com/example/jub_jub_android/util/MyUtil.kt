package com.example.jub_jub_android.util

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.Window
import com.example.jub_jub_android.R
import kotlinx.android.synthetic.main.layout_alertdialog.*
import java.io.ByteArrayOutputStream
import java.io.File


object MyUtil {

    fun getMotorTestImage(context: Context): String{
        val byteStream = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(context.resources, com.example.jub_jub_android.R.drawable.imageex)
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
        dialog.setContentView(R.layout.layout_alertdialog)

        dialog.textView_AlertName_AlertDialogLayout.text = dialogName
        dialog.textView_AlertContent_AlertDialogLayout.text = "정말 $dialogName 하시겠습니까?"
        return dialog
    }

}