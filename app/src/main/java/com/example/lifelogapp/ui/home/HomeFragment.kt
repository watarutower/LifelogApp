package com.example.lifelogapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifelogapp.MainActivity
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDatabase
import com.example.lifelogapp.databinding.FragmentHomeBinding

import com.example.lifelogapp.ui.home.HomeFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item_day_status.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var binding: FragmentHomeBinding

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


//        homeViewModel.aStatus.observe(viewLifecycleOwner, Observer {
//            it?.let {it
//                homeViewModel.qualityToImage(it?.oneCondition)
//            }
//        })


        homeViewModel.navigateToUpdate.observe(viewLifecycleOwner,
                Observer<Boolean?> {navigate ->
                    if(navigate==true)let{
                        val navController = findNavController()
                        navController.navigate(R.id.action_fragment_home_to_fragment_update)
                        homeViewModel.doneNavigating()
                    }
                })

//        val webView = context?.let { WebView(it) }
//        homeViewModel.refreshDisplay.observe(viewLifecycleOwner,
//        Observer<Boolean?> {
//            if(it==true)let{
//                webView?.reload()
//            }
//        })

        homeViewModel.navigateToDisplayMemo.observe(viewLifecycleOwner, Observer { memo ->
            memo?.let {
                this.findNavController().navigate(
                      HomeFragmentDirections.actionFragmentHomeToFragmentWriteReview(memo))
                homeViewModel.onDisplayMemoNavigated()
            }
        })
//        val manager = RecyclerView.LayoutManager(activity, 3)
//        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int) =  when (position) {
//                0 -> 3
//                else -> 1
//            }
//        }


//        binding.dayStatusList.layoutManager = manager

        return binding.root
    }
}