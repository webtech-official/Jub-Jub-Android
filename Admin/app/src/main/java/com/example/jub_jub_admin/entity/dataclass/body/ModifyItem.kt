package com.example.jub_jub_admin.entity.dataclass.body

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.Serializable

data class ModifyItem (
        @PrimaryKey var id: Int,

        @SerializedName("img_equipment")
        var image: MultipartBody.Part,

        @SerializedName("name")
        var name: MultipartBody.Part,

        @SerializedName("content")
        var category: MultipartBody.Part,

        @SerializedName("count")
        var count: MultipartBody.Part
) : Serializable {
    constructor(image: MultipartBody.Part, name: MultipartBody.Part, category: MultipartBody.Part, count: MultipartBody.Part) : this(0, image, name, category, count)
}
