package com.example.jup_jup_android.entity.dataclass

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "itemStatus")
data class ItemStatus(
        @PrimaryKey var id: Long,
        var image: String,
        var name: String,
        var category: String,
        var count: Int
) : Serializable
