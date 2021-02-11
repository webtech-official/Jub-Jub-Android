package com.example.jub_jub_android.data.remote

import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.body.SignUp
import com.example.jub_jub_android.entity.dataclass.response.*

import retrofit2.Call
import retrofit2.http.*


interface Api {

    @POST("signin")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Call<SignUpResponse>

    @GET("equipment/")
    fun getEquipmentData(@Header("X-AUTH-TOKEN") token: String): Call<EquipmentResponse>

    @GET("myequipment")
    fun getMyEquipmentData(@Header("X-AUTH-TOKEN") token: String): Call<MyEquipmentResponse>

    @POST("equipmentallow/{name}")
    fun rentEquipment(@Header("X-AUTH-TOKEN") token: String,
                           @Body equipmentAllowDTO: EquipmentAllowDTO,
                           @Path("name") name: String
    ): Call<MyResponse>
}

