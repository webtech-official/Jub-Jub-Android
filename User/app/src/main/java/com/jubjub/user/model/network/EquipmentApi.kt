package com.jubjub.user.model.network

import com.jubjub.user.entity.dataclass.response.EquipmentRentRequestDTO
import com.jubjub.user.entity.dataclass.response.EquipmentResponse
import com.jubjub.user.entity.dataclass.response.MyEquipmentResponse
import com.jubjub.user.entity.dataclass.response.MyResponse
import io.reactivex.Single
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