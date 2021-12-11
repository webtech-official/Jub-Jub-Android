package com.jubjub.user.model.repository

import android.util.Log
import com.jubjub.user.entity.dataclass.body.Login
import com.jubjub.user.entity.dataclass.body.SignUp
import com.jubjub.user.entity.dataclass.response.LoginResponse
import com.jubjub.user.entity.dataclass.response.SignUpResponse
import com.jubjub.user.entity.singleton.TokenManager
import com.jubjub.user.model.local.SharedPref
import com.jubjub.user.model.network.AuthApi
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

    fun loginSuccess(token: String, loginData: Login){
        showLog("token = $token")
        saveToken(token)
        saveAccountData(loginData)
    }

    private fun saveToken(token: String) = TokenManager.setToken(token)

    private fun saveAccountData(loginData: Login) = sharedPref.saveAccount(loginData)


    //fun saveAuthData(loginData: Login) = SharedPref().saveAccount( viewModel.loginSuccessData.email, viewModel.loginSuccessData.password)


    private fun showLog(msg: String) { Log.d("TestLog_AuthRepository", msg) }

}