package com.example.jub_jub_android.model.repository

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.example.jub_jub_android.R
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.MyEquipment
import com.example.jub_jub_android.model.local.MyEquipmentDAO
import com.example.jub_jub_android.util.MyUtil
import org.koin.core.KoinComponent

class MyRentRepository(private val context: Context, private val myrent: MyEquipmentDAO): KoinComponent {



    fun clearDB(){ myrent.clear() }

    fun insert(item: MyEquipment){ myrent.insert(item) }

    fun getAll(): ArrayList<MyEquipment> { return myrent.getAll() as ArrayList<MyEquipment>}

    fun search(word: String): ArrayList<MyEquipment> { return myrent.search(word) as ArrayList<MyEquipment>}

    fun createDummyDataArrayList(): ArrayList<MyEquipment> {
        return arrayListOf<MyEquipment>(
            MyEquipment("애플 iPad Pro 11형", "패드 & 탭", 20, getDrawable(R.drawable.image), "ROLE_Return"),
            MyEquipment("Macbook 13인치", "노트북", 20, getDrawable(R.drawable.image), "ROLE_Rental"),
            MyEquipment("Magic Mouse 2", "마우스", 20, getDrawable(R.drawable.image), "ROLE_Waiting"),
            MyEquipment("Arduino", "아두이노", 20, getDrawable(R.drawable.image), "ROLE_Reject"),
            MyEquipment("iPad Pro", "아이패드", 20, getDrawable(R.drawable.image), "ROLE_Accept"),
        )
    }

    private fun getDrawable(resId: Int): String {
        return MyUtil.convertBitmapToBase64((ContextCompat.getDrawable(context, resId) as BitmapDrawable).bitmap)!!
    }


}