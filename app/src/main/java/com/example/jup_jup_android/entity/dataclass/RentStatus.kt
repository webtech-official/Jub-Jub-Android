package com.example.jup_jup_android.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "rentStatus")
data class RentStatus(
        @PrimaryKey(autoGenerate = true) val rentId: Int,
        var userId: String,
        var name: String,
        var category: String,
        var count: Int,
        var image: String,
        var status: String
): Serializable{
    constructor(userId: String, name: String, category: String, count: Int, image: String, status: String) : this(0, userId, name, category, count, image, status)

}

