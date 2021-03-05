package com.example.jub_jub_admin.entity.dataclass.response

import java.io.Serializable

data class GETLaptopSpecResponse(
    val success: Boolean,
    val code: Int,
    val msg: String,
    val list: ArrayList<LaptopSpec>
)

data class LaptopSpec(
        val createdDate: String,
        val modifiedDate: String,
        val specIdx: Int,
        val laptopName: String,
        val laptopBrand: String,
        val cpu: String,
        val gpu: String,
        val ram: String,
        val ssd: String,
        val hdd: String
): Serializable{}
