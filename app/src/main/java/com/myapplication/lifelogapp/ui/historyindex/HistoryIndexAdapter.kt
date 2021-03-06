package com.myapplication.lifelogapp.ui.historyindex

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.lifelogapp.databinding.ListIndexDaysBinding
import com.myapplication.lifelogapp.databinding.ListItemDayStatusBinding


class HistoryIndexAdapter(val clickListener: HistoryIndexListener):
        ListAdapter<String, HistoryIndexAdapter.ViewHolder>(HistoryIndexDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position)!!, clickListener)
    }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder.from(parent)
 }

    class ViewHolder private constructor(val binding: ListIndexDaysBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: String, clickListener: HistoryIndexListener) {
            binding.dayindex = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListIndexDaysBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class HistoryIndexDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class HistoryIndexListener(val clickListener: (statusId: String) -> Unit) {
    fun onClick(oneStatus: String) = clickListener(oneStatus)
}
