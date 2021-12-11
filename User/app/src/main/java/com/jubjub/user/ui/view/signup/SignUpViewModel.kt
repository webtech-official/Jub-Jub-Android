package com.jubjub.user.ui.view.signup

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.jubjub.user.base.BaseViewModel
import com.jubjub.user.entity.dataclass.result.ServerResult
import com.jubjub.user.entity.dataclass.body.SignUp
import com.jubjub.user.model.repository.AuthRepository
import com.jubjub.user.ui.adapter.viewpager.SignUpViewPagerAdapter

class SignUpViewModel(private val auth: AuthRepository): BaseViewModel() {

    val emailTitle = "Email"; val emailHint = "이메일을 입력하세요."; val passwordTitle = "Password"; val passwordHint = "비밀번호를 입력하세요."; val passwordCheckTitle = "Check Password"; val nameTitle = "Name"; val classNumTitle = "Class & Number"

    lateinit var adapter: SignUpViewPagerAdapter

    val loading = MutableLiveData(false)

    val signUpResult = MutableLiveData<ServerResult>()

    val viewpagerPosition = MutableLiveData(0)

    val finish = MutableLiveData<Unit>()

    val signUpEditTextArrayList = ArrayList<EditText>()

    private fun startLoading(){ loading.postValue(true) }

    fun stopLoading(){ loading.postValue(false) }


    fun viewPagerPrevPage(){
        showLog("clickPrev")
        if(viewpagerPosition.value == 0){
            finish.postValue(Unit)
        }else {
            viewpagerPosition.value = viewpagerPosition.value?.minus(1)
        }
    }

    fun viewPagerNextPage(){
        showLog("clickNext")
        if(viewpagerPosition.value == 3){
            signUp(createSignUpObject())
        }else{
            viewpagerPosition.value = viewpagerPosition.value?.plus(1)
        }
    }

    private fun createSignUpObject(): SignUp {
        return SignUp(signUpEditTextArrayList[3].text.toString(), signUpEditTextArrayList[0].text.toString(), signUpEditTextArrayList[2].text.toString(), signUpEditTextArrayList[1].text.toString())
    }

    private fun signUp(signUp: SignUp) {
        showLog("signUpData = $signUp")

        //Start Loading
        startLoading()

        addDisposable(
            auth.signUp(signUp)
                .subscribe({
                    setSignUpResult(it.success, it.msg)
                }, {
                    setSignUpResult(false, it.message.toString())
                })
        )
    }

    private fun setSignUpResult(result: Boolean, msg: String){
        //Stop Loading
        showLog("signUpResult: $result   msg: $msg")

        signUpResult.postValue(ServerResult(result, msg))
    }

}