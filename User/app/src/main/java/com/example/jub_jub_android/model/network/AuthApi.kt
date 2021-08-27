package com.example.jub_jub_android.model.network

import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.body.SignUp
import com.example.jub_jub_android.entity.dataclass.response.LoginResponse
import com.example.jub_jub_android.entity.dataclass.response.SignUpResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("signin")
    fun login(@Body login: Login): Single<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Single<SignUpResponse>

}