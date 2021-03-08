package com.example.jub_jub_admin.ui.util

import android.app.Dialog
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Process
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.jub_jub_admin.R
import kotlinx.android.synthetic.main.layout_alertdialog.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File


object MyUtil {

    fun convertBase64ToBitmap(base64Code: String): Bitmap{
        val decodedString: ByteArray = Base64.decode(base64Code, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun makeBaseDialog(context: Context, dialogName: String): Dialog {
        var dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_alertdialog)

        dialog.textView_AlertName_AlertDialogLayout.text = dialogName
        dialog.textView_AlertContent_AlertDialogLayout.text = "정말 $dialogName 하시겠습니까?"

        return dialog

    }

    fun getPathFromBase64(context: Context, image: String): String {
        return getPathFromUri(context, getUriFromBitmap(context, convertBase64ToBitmap(image))!!)
    }

    fun getUriFromBitmap(context: Context, imageBitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.getContentResolver(), imageBitmap, "Title", null)
        return Uri.parse(path)
    }

    fun getPathFromUri(context: Context, contentURI: Uri): String {

        var result: String = ""

        var cursor: Cursor = context.contentResolver.query(contentURI, null, null, null, null)!!
        try {
            if(cursor == null){
                result = contentURI.path.toString()
            } else {
                cursor.moveToFirst()
                var idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                result = cursor.getString(idx)
                cursor.close()

            }
        }catch (e: NullPointerException){
            Log.d("TestLog_ModifyEquipmentActivity_image", "error = ${e.message}")
        }

        return result
    }


    fun uriToFile(uri: Uri?, context: Context): String {
        val cursor: Cursor = context.contentResolver.query(uri!!, null, null, null)!!
        cursor.moveToNext()
        val path: String = cursor.getString(cursor.getColumnIndex("_data"))
        cursor.close()
        return path
    }

    fun createMultiPart(filePath: String?): MultipartBody.Part {
        val file = File(filePath)
        val requestBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("files", file.name, requestBody)
    }

}