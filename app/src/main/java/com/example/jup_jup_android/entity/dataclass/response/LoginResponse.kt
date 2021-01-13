package com.example.jup_jup_android.entity.dataclass.response

data class LoginResponse (
    val email: String,
    val classNumber: String,
    val token: String,
    val msg: String = ""
)