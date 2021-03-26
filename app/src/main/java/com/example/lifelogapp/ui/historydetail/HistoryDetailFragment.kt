package com.example.lifelogapp.ui.historydetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDatabase
import com.example.lifelogapp.databinding.FragmentHistoryDetailBinding

class HistoryDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentHistoryDetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_history_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = HistoryDetailFragmentArgs.fromBundle(arguments)

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = HistoryDetailViewModelFactory(arguments.dayLogsKey, dataSource)

        var historyDetailViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(HistoryDetailViewModel::class.java)

        binding.historyDetailViewModel = historyDetailViewModel

        val adapter = HistoryDetailAdapter()
        binding.dayStatusList.adapter = adapter

        historyDetailViewModel.daylog.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

//        val root = inflater.inflate(R.layout.fragment_history, container, false)
//        val textView: TextView = root.findViewById(R.id.text_history)
//        historyViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }
}