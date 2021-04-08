package com.myapplication.lifelogapp.ui.home

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
import com.myapplication.lifelogapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = HomeViewModelFactory(application, dataSource)

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel = homeViewModel

        binding.setLifecycleOwner(this)

        val adapter = HomeAdapter()
        binding.dayStatusList.adapter = adapter

        homeViewModel.daylog.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })


        homeViewModel.navigateToUpdate.observe(viewLifecycleOwner,
                Observer<Boolean?> {navigate ->
                    if(navigate==true)let{
                        val navController = findNavController()
                        navController.navigate(R.id.action_fragment_home_to_fragment_update)
                        homeViewModel.doneNavigating()
                    }
                })

        homeViewModel.navigateToDisplayMemo.observe(viewLifecycleOwner, Observer { memo ->
            memo?.let {
                this.findNavController().navigate(
                      HomeFragmentDirections.actionFragmentHomeToFragmentWriteReview(memo))
                homeViewModel.onDisplayMemoNavigated()
            }
        })

        return binding.root
    }
}