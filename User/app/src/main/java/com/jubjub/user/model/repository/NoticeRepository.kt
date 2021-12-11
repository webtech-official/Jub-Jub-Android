package com.jubjub.user.model.repository

import android.content.Context
import com.jubjub.user.entity.dataclass.response.Notice
import com.jubjub.user.entity.dataclass.response.NoticeResponse
import com.jubjub.user.entity.singleton.TokenManager
import com.jubjub.user.model.local.NoticeDAO
import com.jubjub.user.model.network.NoticeApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class NoticeRepository(private val context: Context, private val api: NoticeApi, private val notice: NoticeDAO): KoinComponent {

    fun getAllNotice(): ArrayList<Notice> {
        return notice.getAll() as ArrayList<Notice>
    }

    fun updateNotice(): Single<NoticeResponse> {
        return api.getAllNotice(TokenManager.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createDummyData(): ArrayList<Notice> {
        val list = ArrayList<Notice>()

        repeat(10){
            list.add(Notice(1, "현재 많은 학생들이 기자재 대여를 하고나서 반납을 하고있지 않습니다. 학교의 물건이기 때문에 연체되지 않게 반납을 해주시길 바랍니다."
            ,"2021-09-06T02:12:14.890Z", "2021-09-06T02:12:14.890Z", it, "0$it 연체된 학생들 얼른 반납해주세요!"))
        }

        return list
    }
}