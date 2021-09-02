package com.example.jub_jub_android.ui.view.login

import androidx.lifecycle.MutableLiveData
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.result.ServerResult
import com.example.jub_jub_android.model.repository.AuthRepository
import com.example.jub_jub_android.util.NotNullMutableLiveData

class LoginViewModel(private val auth: AuthRepository): BaseViewModel() {

    //private val _profile : MutableLiveData<Unit> = MutableLiveData()

    //로딩 중 ProgressBar Visible 결정하는 Bool 변수
    val refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)

    //로그인 버튼 클릭 시 Update
    //val loginButtonClick = MutableLiveData<Unit>()

    //회원가입 버튼 클릭 시 Update
    //val signUpButtonClick = MutableLiveData<Unit>()

    //로그인 실패 시 Update
    //val loginError = MutableLiveData<String>()

    //로그인에 성공시 Id, Pw 등록
    var loginSuccessData = MutableLiveData<Login>()

    val loginResult = MutableLiveData<ServerResult>()

    //fun clickLogin(){ loginButtonClick.postValue(Unit) }

    //fun clickSignUp(){ signUpButtonClick.postValue(Unit) }

    private fun startRefreshing(){ refreshing.postValue(true) }

    private fun stopRefreshing(){ refreshing.postValue(false) }



    fun signIn(loginData: Login){
        startRefreshing()

        addDisposable(
            auth.login(loginData)
                .subscribe({ //로그인 성공 시
                    if(it.success){ auth.loginSuccess(it.data.accessToken, loginData) }
                    setLoginResult(it.success, it.msg)
                   }, {// 로그인 실패 시
                    showLog("Login Fail! ${it.message}")
                    setLoginResult(false, "${it.message}")
                    }
                )
        )
    }

    private fun setLoginResult(result: Boolean, msg:String){
        stopRefreshing()
        loginResult.postValue(ServerResult(result, msg))
    }

}