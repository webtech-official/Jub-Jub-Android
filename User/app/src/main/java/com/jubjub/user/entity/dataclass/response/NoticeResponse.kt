package com.jubjub.user.entity.dataclass.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NoticeResponse(
    val code: Int,
    val list: ArrayList<Notice>,
    val msg: String,
    val success: Boolean
)


@Entity(tableName = "notice")
data class Notice(
    val adminIdx: Int,
    val content: String,
    val createdDate: String,
    val modifiedDate: String,
    @PrimaryKey val notice_Idx: Int,
    val title: String
)