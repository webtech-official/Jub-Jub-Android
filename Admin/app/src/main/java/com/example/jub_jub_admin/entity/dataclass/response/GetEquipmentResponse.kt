package com.example.jub_jub_admin.entity.dataclass.response

import androidx.room.PrimaryKey
import com.example.jub_jub_admin.entity.dataclass.Equipment
import com.google.gson.annotations.SerializedName

data class GetEquipmentResponse(
        val code: Int,
        val list: ArrayList<Equipment>,
        val msg: String,
        val success: Boolean
)
