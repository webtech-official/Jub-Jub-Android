package com.example.jub_jub_admin.data.remote

import com.example.jub_jub_admin.entity.dataclass.body.Login
import com.example.jub_jub_admin.entity.dataclass.body.SignUp
import com.example.jub_jub_admin.entity.dataclass.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @GET("coffe")
    fun getTest(): Call<ResponseTest>

    @POST("signin")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Call<SignUpResponse>

    @Multipart
    @POST("equipment")
    fun addItem(@Header("X-AUTH-TOKEN") token: String,
                @Part img_equipment: MultipartBody.Part,
                @Part name: RequestBody,
                @Part content: RequestBody,
                @Part count: RequestBody,
    ): Call<MyResponse>

    @GET("equipment/")
    fun getAllEquipment(@Header("X-AUTH-TOKEN") token: String): Call<GetEquipmentResponse>

    @GET("equipment/{name}")
    fun searchEquipment(
            @Header("X-AUTH-TOKEN") token: String,
            @Path("name") name: String
    ): Call<SearchEquipment>

    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @PUT("equipmentAll/{oldName}")
    fun modifyEquipment(
            @Header("X-AUTH-TOKEN") token: String,
            @Path("oldName") oldName: String,
            @Query("content") content: String,
            @Query("count") count: Int,
            @Part img_equipment: MultipartBody.Part,
            @Query("newName") newName: String
    ): Call<MyResponse>



}