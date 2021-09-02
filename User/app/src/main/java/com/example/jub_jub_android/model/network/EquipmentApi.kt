package com.example.jub_jub_android.model.network

import com.example.jub_jub_android.entity.dataclass.response.EquipmentRentRequestDTO
import com.example.jub_jub_android.entity.dataclass.response.EquipmentResponse
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentResponse
import com.example.jub_jub_android.entity.dataclass.response.MyResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface EquipmentApi {

    @GET("equipment")
    fun getEquipmentData(): Single<EquipmentResponse>

    @GET("myequipment")
    fun getMyEquipmentData(@Header("Authorization") token: String): Single<MyEquipmentResponse>

    @POST("equipmentallow/{name}")
    fun rentEquipment(@Header("Authorization") token: String,
                      @Body equipmentAllowSaveDto : EquipmentRentRequestDTO,
                      @Path("name") name: String): Single<MyResponse>

}