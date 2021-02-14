package com.example.jub_jub_admin.entity.dataclass.response

import com.example.jub_jub_admin.entity.dataclass.Equipment

data class SearchEquipment(
        val code: Int,
        val data: Equipment,
        val msg: String,
        val success: Boolean
)
