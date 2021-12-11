package com.jubjub.user.ui.view.notice


import androidx.lifecycle.MutableLiveData
import com.jubjub.user.base.BaseViewModel
import com.jubjub.user.entity.dataclass.response.Notice
import com.jubjub.user.model.repository.NoticeRepository


class NoticeViewModel(private val notice: NoticeRepository): BaseViewModel() {

    val noticeArrayList = MutableLiveData<ArrayList<Notice>>(notice.createDummyData())


    fun update(){
        addDisposable(
            notice.updateNotice()
                .subscribe({
                    if(it.success){
                        //equipmentArrayList.postValue(it.list)
                    }
                },{
                    showLog(it.message.toString())
                })
        )  }



}