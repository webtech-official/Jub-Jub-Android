package com.example.jub_jub_android.model.repository

import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.body.SignUp
import com.example.jub_jub_android.entity.dataclass.response.LoginResponse
import com.example.jub_jub_android.entity.dataclass.response.SignUpResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.model.local.SharedPref
import com.example.jub_jub_android.model.network.AuthApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class AuthRepository(private val api: AuthApi, private val sharedPref: SharedPref): KoinComponent {

    fun login(loginData: Login): Single<LoginResponse> {

        return api.login(loginData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun signUp(signUpData: SignUp): Single<SignUpResponse>{

        return api.signUp(signUpData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginSuccess(token: String, loginData: Login ){
        saveToken(token)
        saveAccountData(loginData)
    }

    private fun saveToken(token: String) = TokenManager.setToken(token)
    private fun saveAccountData(loginData: Login) = sharedPref.saveAccount(loginData
    )
    //fun saveAuthData(loginData: Login) = SharedPref().saveAccount( viewModel.loginSuccessData.email, viewModel.loginSuccessData.password)


}