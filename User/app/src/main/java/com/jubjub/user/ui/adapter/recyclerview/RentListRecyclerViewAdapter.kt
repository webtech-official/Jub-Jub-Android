package com.jubjub.user.ui.adapter.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jubjub.user.R
import com.jubjub.user.databinding.*
import com.jubjub.user.entity.dataclass.MyEquipment
import com.jubjub.user.util.MyUtil

class RentListRecyclerViewAdapter(val dataList: ArrayList<MyEquipment>): RecyclerView.Adapter<RentListRecyclerViewAdapter.ViewHolder>(), Filterable {

    private var filteredList = dataList

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = when(viewType){
            0 -> inflate(parent, R.layout.layout_rent_item_waiting)
            1 -> inflate(parent, R.layout.layout_rent_item_overdue)
            2 -> inflate(parent, R.layout.layout_rent_item_reject)
            3 -> inflate(parent, R.layout.layout_rent_item_rental)
            4 -> inflate(parent, R.layout.layout_rent_item_return)
            else -> inflate(parent, R.layout.layout_rent_item_return)
        }

        return ViewHolder(v)
    }

    private fun inflate(viewGroup: ViewGroup, resId: Int ): View {
        return LayoutInflater.from(viewGroup.context).inflate(resId, viewGroup, false)
    }

    override fun getItemViewType(position: Int): Int {
        // ROLE_Waiting","ROLE_Accept","ROLE_Reject","ROLE_Rental","ROLE_Return
        return when(filteredList[position].status){
            "ROLE_Waiting" -> 0
            "ROLE_Accept" -> 1
            "ROLE_Reject" -> 2
            "ROLE_Rental" -> 3
            "ROLE_Return" -> 4
            else -> 5
        }

        //super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(filteredList[itemPosition])
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: MyEquipment){

            when(data.status){
                "ROLE_Waiting" -> DataBindingUtil.bind<LayoutRentItemWaitingBinding>(view)!!.myEquipment = data
                "ROLE_Accept" -> DataBindingUtil.bind<LayoutRentItemOverdueBinding>(view)!!.myEquipment = data
                "ROLE_Reject" -> DataBindingUtil.bind<LayoutRentItemRejectBinding>(view)!!.myEquipment = data
                "ROLE_Rental" -> DataBindingUtil.bind<LayoutRentItemRentalBinding>(view)!!.myEquipment = data
                "ROLE_Return" -> DataBindingUtil.bind<LayoutRentItemReturnBinding>(view)!!.myEquipment = data
            }

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {

                val charString = constraint.toString()

                if (charString.isEmpty() || charString == "전체") {
                    filteredList = dataList
                } else {
                    val filteringList: ArrayList<MyEquipment> = ArrayList()
                    for (item in dataList) {
                        if (MyUtil.getRentStatus(item.status) == charString) {
                            filteringList.add(item)
                        }
                    }
                    filteredList = filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredList = results.values as ArrayList<MyEquipment>
                Log.d("TestLog", "$filteredList")
                notifyDataSetChanged()
            }
        }
    }
}
