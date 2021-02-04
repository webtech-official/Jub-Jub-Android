package com.example.jub_jub_android.entity.dataclass.response

data class LoginResponse (
        val code: Int,
        val data: String,
        val msg: String,
        val success: Boolean
)