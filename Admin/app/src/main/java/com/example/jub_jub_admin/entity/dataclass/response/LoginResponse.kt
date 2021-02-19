package com.example.jub_jub_admin.entity.dataclass.response

data class LoginResponse (
    val code: Int,
    val data: Tokens,
    val msg: String,
    val success: Boolean
)

data class Tokens (
    val accessToken: String,
    val refreshToken: String
)
