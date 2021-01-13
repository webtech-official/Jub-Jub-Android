package com.example.jup_jup_android.entity.dataclass

import android.graphics.Bitmap
import java.io.Serializable

data class ItemStatus(
        var itemImage: Bitmap,
        var itemName: String,
        var itemCategory: String,
        var itemCount: Int
) : Serializable
