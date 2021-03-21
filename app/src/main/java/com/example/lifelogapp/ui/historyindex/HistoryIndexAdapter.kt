package com.example.lifelogapp.ui.historyindex

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.databinding.ListIndexDaysBinding
import com.example.lifelogapp.databinding.ListItemDayStatusBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HistoryIndexAdapter(val clickListener: HistoryIndexListener):
        ListAdapter<Lifelog, HistoryIndexAdapter.ViewHolder>(HistoryIndexDiffCallback()) {

//    --------------------------
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position)!!, clickListener)
    }

 //    --------------------------
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder.from(parent)
 }


//    --------------------------
    class ViewHolder private constructor(val binding: ListIndexDaysBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Lifelog, clickListener: HistoryIndexListener) {
            binding.daystatus = item
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
//    -----------------------
class HistoryIndexDiffCallback : DiffUtil.ItemCallback<Lifelog>() {
    override fun areItemsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem.statusId == newItem.statusId
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Lifelog, newItem: Lifelog): Boolean {
        return oldItem == newItem
    }
}

//    --------------------------
class HistoryIndexListener(val clickListener: (statusId: Long) -> Unit) {
    fun onClick(oneStatus: Lifelog) = clickListener(oneStatus.statusId)
}


//  Headerを入れるとき用　reserved
//
//package com.example.lifelogapp.ui.historyindex
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lifelogapp.R
//import com.example.lifelogapp.database.Lifelog
//import com.example.lifelogapp.databinding.ListIndexDaysBinding
//import com.example.lifelogapp.databinding.ListItemDayStatusBinding
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//private val ITEM_VIEW_TYPE_HEADER = 0
//private val ITEM_VIEW_TYPE_ITEM = 1
//
//class HistoryIndexAdapter(val clickListener: HistoryIndexListener):
//        ListAdapter<DataItem, RecyclerView.ViewHolder>(HistoryIndexDiffCallback()) {
//
//    private val adapterScope = CoroutineScope(Dispatchers.Default)
//    //  --------------------------
//    fun addHeaderAndSubmitList(list: List<Lifelog>?) {
//        adapterScope.launch {
//            val items = when (list) {
//                null -> listOf(DataItem.Header)
//                else -> listOf(DataItem.Header) + list.map { DataItem.LifelogItem(it) }
//            }
//            withContext(Dispatchers.Main) {
//                submitList(items)
//            }
//        }
//    }
//    //    --------------------------
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is ViewHolder -> {
//                val daysItem = getItem(position) as DataItem.LifelogItem
//                holder.bind(daysItem.lifelog, clickListener)
//            }
//        }
//    }
//
//    //    --------------------------
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
//            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
//            else -> throw ClassCastException("Unknown viewType ${viewType}")
//        }
//    }
//
//    //    --------------------------
//    override fun getItemViewType(position: Int): Int {
//        return when (getItem(position)) {
//            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
//            is DataItem.LifelogItem -> ITEM_VIEW_TYPE_ITEM
//        }
//    }
//
//    //    --------------------------
//    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        companion object {
//            fun from(parent: ViewGroup): TextViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.header, parent, false)
//                return TextViewHolder(view)
//            }
//        }
//    }
//
//    //    --------------------------
//    class ViewHolder private constructor(val binding: ListIndexDaysBinding)
//        : RecyclerView.ViewHolder(binding.root){
//
//        fun bind(item: Lifelog, clickListener: HistoryIndexListener) {
//            binding.daystatus = item
//            binding.clickListener = clickListener
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListIndexDaysBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//
//}
////    -----------------------
//class HistoryIndexDiffCallback : DiffUtil.ItemCallback<DataItem>() {
//    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem.id == newItem.id
//    }
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem == newItem
//    }
//}
//
////    --------------------------
//class HistoryIndexListener(val clickListener: (statusId: Long) -> Unit) {
//    fun onClick(oneStatus: Lifelog) = clickListener(oneStatus.statusId)
//}
//
////    --------------------------
//sealed class DataItem {
//    data class LifelogItem(val lifelog: Lifelog): DataItem() {
//        override val id = lifelog.statusId
//    }
//
//    object Header: DataItem() {
//        override val id = Long.MIN_VALUE
//    }
//
//    abstract val id: Long
//}
//
//
//
//
//
//
//
