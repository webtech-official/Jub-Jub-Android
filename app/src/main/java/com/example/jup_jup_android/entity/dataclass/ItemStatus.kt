package com.example.jup_jup_android.entity.dataclass

import android.graphics.Bitmap
import java.io.Serializable
import java.util.*

data class ItemStatus(
        var id: Long,
        var image: String,
        var name: String,
        var category: String,
        var count: Int
) : Serializable
