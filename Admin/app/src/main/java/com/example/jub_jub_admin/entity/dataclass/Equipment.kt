package com.example.jub_jub_admin.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URL

@Entity(tableName = "equipment")
data class Equipment(
        @SerializedName("equ_Idx")
        @PrimaryKey(autoGenerate = true) var id: Int,

        @SerializedName("img_equipment")
        var image: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("content")
        var category: String,

        @SerializedName("count")
        var count: Int
) : Serializable{
    constructor(image: String, name: String, category: String, count: Int) : this(0, image, name, category, count)
}
