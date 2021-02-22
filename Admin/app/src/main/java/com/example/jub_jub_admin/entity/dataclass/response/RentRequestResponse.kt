package com.example.jub_jub_admin.entity.dataclass.response

import android.util.Size
import com.example.jub_jub_admin.entity.dataclass.RentRequest

data class RentRequestResponse(
        val success: Boolean,
        val code: Int,
        val msg: String,
        val list: ArrayList<RentRequestDetailInfo>
)

data class RentRequestDetailInfo(
        var createdDate: String,
        var modifiedDate: String,
        var eqa_Idx: Int,
        var amount: Int,
        var reason: String,
        var equipmentEnum: String,
        var isReturn: Boolean?,
        var equipment: RentEquipmentFromServer,
        var admin: ApplicantInfo
)

data class ApplicantInfo(
        val auth_Idx: Int,
        val email: String,
        val classNumber: String,
        val name: String,
        val roles: ArrayList<String>,
        val authorities: ArrayList<Authority>

)

data class Authority(
        val authority: String
)

data class RentEquipmentFromServer (
        var equ_Idx: Int,
        var name: String,
        var content: String,
        var count: Int,
        var img_equipment: String
        )

