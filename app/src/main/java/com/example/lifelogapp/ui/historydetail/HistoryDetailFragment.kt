package com.example.lifelogapp.ui.historydetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDatabase
import com.example.lifelogapp.databinding.FragmentHistoryDetailBinding
import com.example.lifelogapp.ui.historyindex.HistoryIndexFragmentDirections
import com.example.lifelogapp.ui.home.HistoryDetailAdapter

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
                adapter.addHeaderAndSubmitList(it)
            }
        })

        historyDetailViewModel.navigateToWriteReview.observe(viewLifecycleOwner, Observer { day ->
            day?.let {
                this.findNavController().navigate(
                        HistoryDetailFragmentDirections.actionFragmentHistoryDetailToFragmentWriteReview(day))
                historyDetailViewModel.onWriteReviewNavigated()
            }
        })

        binding.setLifecycleOwner(this)

        return binding.root
    }
}