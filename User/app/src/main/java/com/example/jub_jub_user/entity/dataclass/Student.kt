package com.example.jub_jub_user.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student")
data class Student(
    val name: String,
    @PrimaryKey val number: Int
)
