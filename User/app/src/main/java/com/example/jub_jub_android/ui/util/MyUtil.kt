package com.example.jub_jub_android.ui.util

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.Window
import android.widget.Toast
import androidx.room.RoomDatabase
import com.example.jub_jub_android.R
import com.example.jub_jub_android.entity.dataclass.ItemStatus
import com.example.jub_jub_android.entity.singleton.ManageItemListManager
import kotlinx.android.synthetic.main.layout_alertdialog.*
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

    fun getMotorTestImage(context: Context): String{
        val byteStream = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
    
    fun processShowList(dataList: ArrayList<Any>, key: String, showList: ArrayList<ArrayList<Any>>){

        showList.clear()
        var page = 0
        var cnt = 0
        showList.add(ArrayList())

        for (i in 0 until dataList.size) {
            if (cnt == 5) {
                showList.add(ArrayList())
                cnt = 0
                page++
            }
            showList[page].add(dataList[i])
            cnt++
        }
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