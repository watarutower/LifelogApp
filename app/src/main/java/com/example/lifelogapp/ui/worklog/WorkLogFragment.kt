package com.example.lifelogapp.ui.worklog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDatabase
import com.example.lifelogapp.databinding.FragmentWorkLogBinding
import com.example.lifelogapp.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_history_detail.*

class WorkLogFragment : Fragment() {

    private lateinit var workLogViewModel: WorkLogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentWorkLogBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_work_log, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = WorkLogViewModelFactory(dataSource, application)

        workLogViewModel =
                ViewModelProvider(this, viewModelFactory).get(WorkLogViewModel::class.java)

        binding.workLogViewModel = workLogViewModel

        binding.setLifecycleOwner(this)

        val adapter = WorkLogAdapter()
        binding.dayWorkList.adapter = adapter

        workLogViewModel.workedlog.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })



        return binding.root
    }
}