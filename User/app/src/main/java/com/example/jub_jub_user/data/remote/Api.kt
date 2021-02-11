package com.example.jub_jub_user.data.remote

import com.example.jub_jub_user.entity.dataclass.body.Login
import com.example.jub_jub_user.entity.dataclass.body.SignUp
import com.example.jub_jub_user.entity.dataclass.response.LoginResponse
import com.example.jub_jub_user.entity.dataclass.response.MyResponse
import com.example.jub_jub_user.entity.dataclass.response.ResponseTest
import com.example.jub_jub_user.entity.dataclass.response.SignUpResponse
import okhttp3.MultipartBody
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
                @Query("name") name: String,
                @Query("content") content: String,
                @Query("count") count: Int,
                ): Call<MyResponse>

}