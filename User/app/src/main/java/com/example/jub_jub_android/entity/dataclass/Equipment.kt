package com.example.jub_jub_android.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "equipmentStatus")
data class Equipment(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("equ_Idx") var id: Int,

        @SerializedName("content") var category: String,
        var count: Int,
        @SerializedName("img_equipment") var image: String,
        var name: String

) : Serializable{
    constructor(category: String, count: Int, image: String, name: String): this(0, category, count, image, name)
}
