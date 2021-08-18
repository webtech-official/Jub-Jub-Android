package com.example.jub_jub_android.util

import androidx.lifecycle.MutableLiveData

class NotNullMutableLiveData<T : Any>(defaultValue: T) : SingleLiveEvent<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}
