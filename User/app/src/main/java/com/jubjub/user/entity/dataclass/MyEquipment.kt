package com.jubjub.user.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "myEquipment")
data class MyEquipment(
        @PrimaryKey(autoGenerate = true) val rentId: Int,
        var name: String,
        var category: String,
        var count: Int,
        var image: String,
        var status: String
): Serializable{
    constructor(name: String, category: String, count: Int, image: String, status: String) : this(0, name, category, count, image, status)
}

