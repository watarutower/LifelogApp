package com.example.lifelogapp.ui.worklog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.WorkLog
import com.example.lifelogapp.databinding.ListItemDayStatusBinding
import com.example.lifelogapp.databinding.ListWorkItemBinding
import com.example.lifelogapp.ui.home.HomeAdapter

class WorkLogAdapter: ListAdapter<WorkLog, WorkLogAdapter.ViewHolder>(WorkLogDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListWorkItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: WorkLog) {
            binding.workedlogs = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListWorkItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

//    -----------------------
class WorkLogDiffCallback : DiffUtil.ItemCallback<WorkLog>() {

    override fun areItemsTheSame(oldItem: WorkLog, newItem: WorkLog): Boolean {
        return oldItem.workId == newItem.workId
    }

    override fun areContentsTheSame(oldItem: WorkLog, newItem: WorkLog): Boolean {
        return oldItem == newItem
    }
}

