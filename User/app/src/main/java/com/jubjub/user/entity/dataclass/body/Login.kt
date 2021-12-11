package com.jubjub.user.entity.dataclass.body

import java.io.Serializable

data class Login(
    val email: String,
    var password: String
):Serializable
