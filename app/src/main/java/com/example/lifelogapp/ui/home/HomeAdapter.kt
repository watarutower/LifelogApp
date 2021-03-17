package com.example.lifelogapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.databinding.ListItemDayStatusBinding
import com.example.lifelogapp.ui.historydetail.HistoryDetailAdapter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HomeAdapter: ListAdapter<Lifelog, HomeAdapter.ViewHolder>(HomeDiffCallback()){

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
class HomeDiffCallback : DiffUtil.ItemCallback<Lifelog>() {

    override fun areItemsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem.statusId == newItem.statusId
    }

    override fun areContentsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem == newItem
    }
}

