package com.jubjub.user.model.network

import com.jubjub.user.entity.dataclass.response.NoticeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface NoticeApi {

    @GET("notice")
    fun getAllNotice(@Header("Authorization") token: String): Single<NoticeResponse>

}