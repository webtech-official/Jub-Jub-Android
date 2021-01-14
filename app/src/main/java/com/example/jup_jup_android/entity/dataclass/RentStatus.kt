package com.example.jup_jup_android.entity.dataclass

import java.io.Serializable


data class RentStatus(
        var userId: String,
        var name: String,
        var category: String,
        var count: Int,
        var image: String,
        var status: String
): Serializable
