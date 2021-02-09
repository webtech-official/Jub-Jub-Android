package com.example.jub_jub_android.entity.dataclass.response

import com.google.gson.annotations.SerializedName
import java.io.File
import java.sql.Timestamp
import kotlin.collections.ArrayList

data class MyEquipmentResponse (
        var success: Boolean,
        var code: Int,
        var msg: String,
        var list: ArrayList<MyEquipmentDetailInfo>
)

data class MyEquipmentDetailInfo(
        var createdDate: String,
        var modifiedDate: String,
        var eqa_Idx: Int,
        var amount: Int,
        var reason: String,
        var equipmentEnum: String,
        var isReturn: Boolean?,
        var equipment: MyEquipmentFromServer
)

data class MyEquipmentFromServer(
        var equ_Idx: Int,
        var name: String,
        var content: String,
        var count: Int,
        var img_equipment: String
)

