package com.example.jub_jub_android.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent

open class BaseViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    fun showLog(msg: String) = Log.d("TestLog", msg)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}