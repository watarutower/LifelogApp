//package com.example.lifelogapp.ui.history
//
//import android.annotation.SuppressLint
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lifelogapp.database.Lifelog
//
//class HistoryAdapter(val clickListener: HistoryListener):
//        ListAdapter<DataItem, RecyclerView.ViewHolder>(HistoryDiffCallback()) {
//
//}
//
////    -----------------------
//class HistoryDiffCallback : DiffUtil.ItemCallback<DataItem>() {
//    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem.id == newItem.id
//    }
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem == newItem
//    }
//}
//
