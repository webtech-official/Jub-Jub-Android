package com.example.jub_jub_android.ui.view.signup

import androidx.lifecycle.MutableLiveData
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.entity.dataclass.result.AuthResult
import com.example.jub_jub_android.entity.dataclass.body.SignUp
import com.example.jub_jub_android.model.repository.AuthRepository

class SignUpViewModel(private val auth: AuthRepository): BaseViewModel() {

    val signUpClick = MutableLiveData<Unit>()

    val refreshing = MutableLiveData(false)

    val signUpResult = MutableLiveData<AuthResult>()

    fun clickSignUp(){
        signUpClick.postValue(Unit)
    }

    fun signUp(signUpData: SignUp) {
        refreshing.postValue(true)
        addDisposable(
            auth.signUp(signUpData)
                .subscribe({
                    setSignUpResult(true, "회원가입 성공")
                }, {
                    setSignUpResult(false, it.message.toString())
                })
        )
    }

    private fun setSignUpResult(result: Boolean, msg: String){
        refreshing.postValue(false)
        signUpResult.postValue(AuthResult(result, msg))
    }

}