package com.myapplication.lifelogapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.lifelogapp.R

import com.myapplication.lifelogapp.database.Lifelog
import com.myapplication.lifelogapp.databinding.ListItemDayStatusBinding


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class HomeAdapter(val clickListener: HomeListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(HomeDiffCallback()){

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {

                val logItem = getItem(position) as DataItem.LifelogItem
                holder.bind(logItem.lifeLog, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list: List<Lifelog>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.LifelogItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.LifelogItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    //HeaderのためのHolder
    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: ListItemDayStatusBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Lifelog, clickListener: HomeListener) {
            binding.daystatus = item
            binding.clickListener = clickListener
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

class HomeDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }
    @SuppressLint
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class HomeListener(val clickListener: (statusId: Long) -> Unit) {
    fun onClick(oneStatus: Lifelog) = clickListener(oneStatus.statusId)
}

//
sealed class DataItem {
    data class LifelogItem(val lifeLog: Lifelog): DataItem() {
        override val id = lifeLog.statusId
    }
    // データの中身がないのでobject
    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }

    abstract  val id: Long
}

