package com.example.lifelogapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDatabase

import com.example.lifelogapp.databinding.FragmentHistoryIndexBinding
import com.example.lifelogapp.ui.historyindex.HistoryIndexAdapter
import com.example.lifelogapp.ui.historyindex.HistoryIndexListener
import com.example.lifelogapp.ui.historyindex.HistoryIndexViewModel
import com.example.lifelogapp.ui.historyindex.HistoryIndexViewModelFactory

class HistoryIndexFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentHistoryIndexBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history_index, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = HistoryIndexViewModelFactory(dataSource, application)

        var historyIndexViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HistoryIndexViewModel::class.java)

        binding.historyIndexViewModel = historyIndexViewModel

        val adapter = HistoryIndexAdapter(HistoryIndexListener { statusId ->

            historyIndexViewModel.onDayClicked(statusId)
        })
        binding.daysIndex.adapter = adapter

        historyIndexViewModel.daylogs.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
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