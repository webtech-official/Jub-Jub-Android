package com.example.jub_jub_user.entity.dataclass.body

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.Serializable

data class ModifyItem (
        @PrimaryKey var id: Int,

        @SerializedName("img_equipment")
        var image: MultipartBody.Part,

        @SerializedName("name")
        var name: String,

        @SerializedName("content")
        var category: String,

        @SerializedName("count")
        var count: Int
) : Serializable {
    constructor(image: MultipartBody.Part, name: String, category: String, count: Int) : this(0, image, name, category, count)
}
