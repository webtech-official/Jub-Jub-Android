package com.jubjub.user.entity.dataclass.response

import com.jubjub.user.entity.dataclass.Equipment

data class EquipmentResponse (
        var code: Int,
        var list: ArrayList<Equipment>,
        var msg: String,
        var success: Boolean
        )