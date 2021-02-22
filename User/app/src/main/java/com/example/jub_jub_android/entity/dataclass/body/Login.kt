package com.example.jub_jub_android.entity.dataclass.body

import java.io.Serializable

data class Login(
    val email: String,
    var password: String
):Serializable
