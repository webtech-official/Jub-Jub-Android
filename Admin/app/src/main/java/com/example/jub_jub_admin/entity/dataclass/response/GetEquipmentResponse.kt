package com.example.jub_jub_admin.entity.dataclass.response

import com.example.jub_jub_admin.entity.dataclass.Equipment

data class GetEquipmentResponse(
        val code: Int,
        val list: ArrayList<Equipment>,
        val msg: String,
        val success: Boolean
)
