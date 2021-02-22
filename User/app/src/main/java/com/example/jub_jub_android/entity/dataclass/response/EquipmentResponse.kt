package com.example.jub_jub_android.entity.dataclass.response

import com.example.jub_jub_android.entity.dataclass.Equipment

data class EquipmentResponse (
        var code: Int,
        var list: ArrayList<Equipment>,
        var msg: String,
        var success: Boolean
        )