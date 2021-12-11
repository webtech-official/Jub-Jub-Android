package com.jubjub.user.model.network

import com.jubjub.user.entity.dataclass.body.Login
import com.jubjub.user.entity.dataclass.body.SignUp
import com.jubjub.user.entity.dataclass.response.LoginResponse
import com.jubjub.user.entity.dataclass.response.SignUpResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("signin")
    fun login(@Body login: Login): Single<LoginResponse>

    @POST("signup")
    fun signUp(@Body signUp: SignUp): Single<SignUpResponse>

}