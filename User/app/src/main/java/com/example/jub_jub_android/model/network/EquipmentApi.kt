package com.example.jub_jub_android.model.network

import com.example.jub_jub_android.entity.dataclass.response.EquipmentAllowDTO
import com.example.jub_jub_android.entity.dataclass.response.EquipmentResponse
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentResponse
import com.example.jub_jub_android.entity.dataclass.response.MyResponse
import retrofit2.Call
import retrofit2.http.*

interface EquipmentApi {

    @GET("equipment/")
    fun getEquipmentData(@Header("Authorization") token: String): Call<EquipmentResponse>

    @GET("myequipment")
    fun getMyEquipmentData(@Header("Authorization") token: String): Call<MyEquipmentResponse>

    @POST("equipmentallow/{name}")
    fun rentEquipment(@Header("Authorization") token: String,
                      @Body equipmentAllowSaveDto : EquipmentAllowDTO,
                      @Path("name") name: String): Call<MyResponse>

}