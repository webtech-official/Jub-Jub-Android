package com.jubjub.user.entity.dataclass.response

data class LoginResponse (
    val code: Int,
    val data: Tokens,
    val msg: String,
    val success: Boolean
)

data class Tokens (
    val accessToken: String
)
