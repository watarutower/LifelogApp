package com.myapplication.lifelogapp.ui.historyindex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.myapplication.lifelogapp.R
import com.myapplication.lifelogapp.database.LifelogDatabase
import com.myapplication.lifelogapp.databinding.FragmentHistoryIndexBinding

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

        val adapter = HistoryIndexAdapter(HistoryIndexListener { dayIndex ->
//            Toast.makeText(context, "${dayIndex}", Toast.LENGTH_SHORT).show()
            historyIndexViewModel.onDayClicked(dayIndex)
        })
        binding.statusIndex.adapter = adapter

        historyIndexViewModel.dayIndex.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        binding.setLifecycleOwner(this)

        historyIndexViewModel.navigateToHistoryDetail.observe(viewLifecycleOwner, Observer { specificDay ->
            specificDay?.let {

                this.findNavController().navigate(
                        HistoryIndexFragmentDirections
                                .actionFragmentHistoryIndexToFragmentHistoryDetail(specificDay))
                historyIndexViewModel.onHistoryDetailNavigated()
            }
        })

        return binding.root
    }
}