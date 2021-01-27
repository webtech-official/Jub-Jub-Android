package com.example.jub_jub_user.data.remote

import com.example.jub_jub_user.entity.dataclass.body.Login
import com.example.jub_jub_user.entity.dataclass.body.SignUp
import com.example.jub_jub_user.entity.dataclass.response.LoginResponse
import com.example.jub_jub_user.entity.dataclass.response.ResponseTest
import com.example.jub_jub_user.entity.dataclass.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @GET("coffe")
    fun getTest(): Call<ResponseTest>

    @POST("login")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Call<SignUpResponse>

}