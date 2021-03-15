package com.example.lifelogapp.ui.historydetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.databinding.ListItemDayStatusBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HistoryDetailAdapter: ListAdapter<Lifelog, HistoryDetailAdapter.ViewHolder>(HistoryDetailDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemDayStatusBinding)
                                            : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Lifelog) {
            binding.daystatus = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDayStatusBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

//    -----------------------
class HistoryDetailDiffCallback : DiffUtil.ItemCallback<Lifelog>() {

    override fun areItemsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem.statusId == newItem.statusId
    }

    override fun areContentsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem == newItem
    }
}

