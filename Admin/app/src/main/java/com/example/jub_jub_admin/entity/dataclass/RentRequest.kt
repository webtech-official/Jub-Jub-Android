package com.example.jub_jub_admin.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "rentRequest")
data class RentRequest(
    @PrimaryKey(autoGenerate = true) val requestId: Int,
    var itemName: String,
    var image: String,
    var category: String,
    var studentNumber: Int,
    var count: Int

): Serializable{
    constructor(itemName: String,  image: String, category: String, studentNumber: Int, count: Int,): this(0, itemName, image, category, studentNumber, count)
}
