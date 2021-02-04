package com.example.jub_jub_android.entity.dataclass.body

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.File
import java.io.Serializable

data class ModifyItem (
        @PrimaryKey var id: Int,

        @SerializedName("img_equipment")
        var image: File,

        @SerializedName("name")
        var name: String,

        @SerializedName("content")
        var category: String,

        @SerializedName("count")
        var count: Int
) : Serializable {
    constructor(image: File, name: String, category: String, count: Int) : this(0, image, name, category, count)
}
