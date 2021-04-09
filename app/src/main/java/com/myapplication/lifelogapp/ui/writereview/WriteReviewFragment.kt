package com.myapplication.lifelogapp.ui.writereview

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
import com.myapplication.lifelogapp.databinding.FragmentUpdateBinding
import com.myapplication.lifelogapp.databinding.FragmentWriteReviewBinding
import com.myapplication.lifelogapp.util.hideKeyboard


class WriteReviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentWriteReviewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_write_review, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = WriteReviewFragmentArgs.fromBundle(arguments)

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = WriteReviewViewModelFactory(arguments.dayLogsKey, dataSource)

        val writeReviewViewModel: WriteReviewViewModel =
                ViewModelProvider(this, viewModelFactory).get(WriteReviewViewModel::class.java)

        binding.writeReviewViewModel = writeReviewViewModel


        binding.setLifecycleOwner(this)

        writeReviewViewModel.navigateToHistoryDetail.observe(viewLifecycleOwner, Observer { day ->
            day?.let {
                this.findNavController().navigate(
                        WriteReviewFragmentDirections.actionFragmentWriteReviewToFragmentHistoryDetail(day))
                hideKeyboard()
                writeReviewViewModel.doneNavigating()
            }
        })

        writeReviewViewModel.navigateToHome.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate==true)let {
                this.findNavController().navigate(
                        WriteReviewFragmentDirections.actionFragmentWriteReviewToFragmentHome())
                hideKeyboard()
                writeReviewViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}






