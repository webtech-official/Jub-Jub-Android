package com.example.jub_jub_android.ui.adapter.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.databinding.LayoutEquipmentItemBinding
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.ui.view.rent_request.RentActivity

class EquipmentRecyclerViewAdapter(val dataList: ArrayList<Equipment>): RecyclerView.Adapter<EquipmentRecyclerViewAdapter.ViewHolder>(), Filterable {

    var filteredList = ArrayList<Equipment>()
    //var unFilteredList = dataList


    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipment_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(filteredList[itemPosition])
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: Equipment){

            //val binding =
                DataBindingUtil.bind<LayoutEquipmentItemBinding>(view)!!.equipment = data
            //binding.equipment = data

            val intent = Intent(itemView.context, RentActivity::class.java)
            intent.putExtra("EquipmentData", data)

            itemView.setOnClickListener {
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    filteredList = dataList
                } else {
                    val filteringList: ArrayList<Equipment> = ArrayList()
                    for (item in dataList) {
                        if (item.name.toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = results.values as ArrayList<Equipment>
                notifyDataSetChanged()
            }
        }
    }

}
