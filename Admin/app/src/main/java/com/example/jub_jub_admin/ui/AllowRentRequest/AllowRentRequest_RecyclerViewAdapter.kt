package com.example.jub_jub_admin.ui.AllowRentRequest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.response.MyResponse
import com.example.jub_jub_admin.entity.dataclass.response.RentRequestDetailInfo
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.util.MyUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_alertdialog.*
import kotlinx.android.synthetic.main.layout_manage_rent_request_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AllowRentRequest_RecyclerViewAdapter(val context: Context, var dataList: ArrayList<RentRequestDetailInfo>): RecyclerView.Adapter<AllowRentRequest_RecyclerViewAdapter.ViewHolder>() {

    //private var devideditemStatusList = dataList

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_manage_rent_request_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(context, dataList[itemPosition], holder)
    }


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindItemStatusListItems(context: Context, data: RentRequestDetailInfo, holder: ViewHolder){
            setTextViewsText(data)
            //각각의 아이템 클릭시
            itemView.button_AllowRentRequest.setOnClickListener {
                allowRequest(context, data.eqa_Idx)
            }

            itemView.button_DenyRentRequest.setOnClickListener {
                denyRequest(context, data.eqa_Idx)
            }
        }

        private fun allowRequest(dialogContext: Context, eqa_Idx: Int) {
            val dialog = MyUtil.makeBaseDialog(dialogContext, "수락")
            dialog.accept.setOnClickListener {
                dialog.progress_bar.visibility = View.VISIBLE
                allow(dialogContext, eqa_Idx)
                dialog.progress_bar.visibility = View.GONE
                dialog.dismiss()
            }

            dialog.cancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun denyRequest(dialogContext: Context, eqa_Idx: Int) {
            val dialog = MyUtil.makeBaseDialog(dialogContext, "거절")

            dialog.accept.setOnClickListener {
                dialog.progress_bar.visibility = View.VISIBLE
                deny(dialogContext, eqa_Idx)
                dialog.progress_bar.visibility = View.GONE
                dialog.dismiss()

            }

            dialog.cancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        private fun setTextViewsText(dataInfo: RentRequestDetailInfo) {
            val data = dataInfo.equipment
            Picasso.get().load(data.img_equipment).into(itemView.imageView_ItemImage)
            itemView.textView_ItemName.text = data.name
            itemView.textView_ItemCategory.text = data.content
            itemView.textView_ItemCount.text = "수량 : ${dataInfo.amount}개"
            itemView.textView_StudentNumber.text = dataInfo.admin.classNumber

        }

        private fun allow(dialogContext: Context, idx: Int){
            val response: Call<MyResponse> = NetRetrofit.getServiceApi().allowRentRequest(TokenManager.getToken(), idx)

            response.enqueue(object : Callback<MyResponse>{
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    Log.d("TestLog_AllowRequestReAdap", response.body()?.msg!!)

                    if(response.body()?.success!!){
                        Toast.makeText(dialogContext, "수락 완료" , Toast.LENGTH_SHORT).show()
                        AllowRentRequest_ViewModel.update()
                    }else {
                        Toast.makeText(dialogContext, response.message() , Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Toast.makeText(dialogContext, "서버 통신 실패!" , Toast.LENGTH_SHORT).show()
                    Log.d("TestLog_AllowRequestReAdap", t.message!!)
                }

            })
        }

        private fun deny(dialogContext: Context, idx: Int){
            val response: Call<MyResponse> = NetRetrofit.getServiceApi().denyRentRequest(TokenManager.getToken(), idx)

            response.enqueue(object : Callback<MyResponse>{
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    Log.d("TestLog_AllowRequestReAdap", response.body()?.msg!!)
                    if(response.body()?.success!!){
                        Toast.makeText(dialogContext, "거절 완료" , Toast.LENGTH_SHORT).show()
                        AllowRentRequest_ViewModel.update()
                    }else {
                        Toast.makeText(dialogContext, response.message() , Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Toast.makeText(dialogContext, "서버 통신 실패!" , Toast.LENGTH_SHORT).show()
                    Log.d("TestLog_AllowRequestReAdap", t.message!!)
                }

            })
        }

    }
}
