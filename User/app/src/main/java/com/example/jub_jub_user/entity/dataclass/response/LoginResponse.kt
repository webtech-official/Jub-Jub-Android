package com.example.jub_jub_user.entity.dataclass.response

data class LoginResponse (
    val email: String,
    val classNumber: String,
    val token: String,
    val msg: String = ""
)