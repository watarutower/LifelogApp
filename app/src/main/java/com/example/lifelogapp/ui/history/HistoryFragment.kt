//package com.example.lifelogapp.ui.history
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.example.lifelogapp.R
//import com.example.lifelogapp.database.LifelogDatabase
//import com.example.lifelogapp.databinding.FragmentHistoryBinding
//
//class HistoryFragment : Fragment() {
//
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        val binding: FragmentHistoryBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_history, container, false)
//
//        val application = requireNotNull(this.activity).application
//
//        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
//        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
//
//        var historyViewModel =
//                ViewModelProvider(
//                    this, viewModelFactory).get(HistoryViewModel::class.java)
//
//        binding.historyViewModel = historyViewModel
//
//        val adapter = HistoryAdapter(HistoryListener { daylogId ->
//
//            historyViewModel.onHistoryClicked(daylogId)
//        })
//        binding.daylogList.adapter = adapter
//
////        val root = inflater.inflate(R.layout.fragment_history, container, false)
////        val textView: TextView = root.findViewById(R.id.text_history)
////        historyViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//        return root
//    }
//}