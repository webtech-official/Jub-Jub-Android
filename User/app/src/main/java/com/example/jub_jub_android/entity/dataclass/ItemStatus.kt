package com.example.jub_jub_android.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "itemStatus")
data class ItemStatus(
        @PrimaryKey var id: Int,
        var image: String,
        var name: String,
        var category: String,
        var count: Int
) : Serializable
