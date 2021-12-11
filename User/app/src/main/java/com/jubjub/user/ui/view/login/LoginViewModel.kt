package com.jubjub.user.ui.view.login

import androidx.lifecycle.MutableLiveData
import com.jubjub.user.base.BaseViewModel
import com.jubjub.user.entity.dataclass.body.Login
import com.jubjub.user.entity.dataclass.result.ServerResult
import com.jubjub.user.model.repository.AuthRepository
import com.jubjub.user.util.NotNullMutableLiveData

class LoginViewModel(private val auth: AuthRepository): BaseViewModel() {

    //private val _profile : MutableLiveData<Unit> = MutableLiveData()

    //로딩 중 ProgressBar Visible 결정하는 Bool 변수
    val loading: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)

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

    private fun startRefreshing(){ loading.postValue(true) }

    fun stopRefreshing(){ loading.postValue(false) }



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
        loginResult.postValue(ServerResult(result, msg))
    }

}