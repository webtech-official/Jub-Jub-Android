package com.example.jup_jup_android.entity.dataclass

import android.graphics.Bitmap

data class ItemStatus(
        var bitmap: Bitmap,
        var itemName: String,
        var itemCategory: String,
        var itemCount: Int
)
