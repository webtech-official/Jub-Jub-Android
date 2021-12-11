package com.jubjub.user.entity.dataclass

import androidx.room.Dao
import androidx.room.Entity


@Entity(tableName = "userData")
data class UserData(
    val email: String,
    val password: String,
    val name: String,
    val classNumber: String

)