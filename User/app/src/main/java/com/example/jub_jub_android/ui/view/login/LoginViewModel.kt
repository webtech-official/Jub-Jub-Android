package com.example.jub_jub_android.ui.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.result.AuthResult
import com.example.jub_jub_android.model.repository.AuthRepository
import com.example.jub_jub_android.util.NotNullMutableLiveData

class LoginViewModel(private val auth: AuthRepository): BaseViewModel() {

    private val _profile : MutableLiveData<Unit> = MutableLiveData()

    //로딩 중 ProgressBar Visible 결정하는 Bool 변수
    val refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)

    //로그인 버튼 클릭 시 Update
    val loginButtonClick = MutableLiveData<Unit>()

    //회원가입 버튼 클릭 시 Update
    val signUpButtonClick = MutableLiveData<Unit>()

    //로그인 실패 시 Update
    val loginError = MutableLiveData<String>()

    //로그인에 성공시 Id, Pw 등록
    var loginSuccessData = MutableLiveData<Login>()

    val loginResult = MutableLiveData<AuthResult>()

    fun clickLogin(){ loginButtonClick.postValue(Unit) }

    fun clickSignUp(){ signUpButtonClick.postValue(Unit) }

    private fun startRefreshing(){ refreshing.postValue(true) }

    private fun stopRefreshing(){ refreshing.postValue(false) }



    fun login(loginData: Login){


        startRefreshing()

        addDisposable(
            auth.login(loginData)
                .subscribe({ //로그인 성공 시
                    auth.loginSuccess(it.data.accessToken, loginData)
                    setLoginResult(true, "로그인 성공")
                   }, {// 로그인 실패 시
                    setLoginResult(false, "로그인 실패")
                    }
                )
        )
    }

    private fun setLoginResult(result: Boolean, msg:String){
        stopRefreshing()
        loginResult.postValue(AuthResult(result, msg))
    }

}