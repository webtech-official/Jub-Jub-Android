package com.jubjub.user.util

class NotNullMutableLiveData<T : Any>(defaultValue: T) : SingleLiveEvent<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}
