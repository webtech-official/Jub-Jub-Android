package com.jubjub.user.model.repository

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.jubjub.user.R
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.entity.dataclass.response.EquipmentRentRequestDTO
import com.jubjub.user.entity.dataclass.response.EquipmentResponse
import com.jubjub.user.entity.dataclass.response.MyResponse
import com.jubjub.user.entity.singleton.TokenManager
import com.jubjub.user.model.local.EquipmentStatusDAO
import com.jubjub.user.model.network.EquipmentApi
import com.jubjub.user.util.MyUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class EquipmentRepository(private val context: Context, private val api: EquipmentApi, private val equipment: EquipmentStatusDAO) : KoinComponent{

    fun getEquipmentData(): Single<EquipmentResponse> {
        return api.getEquipmentData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun rentRequest(rentRequestDTO: EquipmentRentRequestDTO, equipmentName: String): Single<MyResponse> {
        return api.rentEquipment(TokenManager.getToken(),rentRequestDTO, equipmentName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //private lateinit var equipmentStatusDB = EquipmentStatusDB.getInstance(context)

    fun clearDB(){ equipment.clear() }

    fun insert(item: Equipment){ equipment.insert(item) }

    fun getAll(): ArrayList<Equipment> { return equipment.getAll() as ArrayList<Equipment>}

    //fun search(word: String): ArrayList<Equipment> { return equipment.search(word) as ArrayList<Equipment>}



    // 이 아래론 Test용 코드
    fun createDummyDataArrayList(): ArrayList<Equipment> {
        return arrayListOf<Equipment>(
            Equipment("패드 & 탭", 20, getDrawable(R.drawable.image), "애플 iPad Pro 11형"),
            Equipment("노트북", 10, getDrawable(R.drawable.image), "Macbook 13인치"),
            Equipment("마우스", 40, getDrawable(R.drawable.image), "Magic Mouse 2"),
            Equipment("아두이노", 150, getDrawable(R.drawable.image), "Arduino"),
            Equipment("아이패드", 20, getDrawable(R.drawable.image), "iPad Pro"),
            Equipment("카테고리", 99, getDrawable(R.drawable.image), "애플 iPad Pro 11형"),
        )
    }
    
    private fun getDrawable(resId: Int): String {
        return MyUtil.convertBitmapToBase64((ContextCompat.getDrawable(context, resId) as BitmapDrawable).bitmap)!!
    }

}