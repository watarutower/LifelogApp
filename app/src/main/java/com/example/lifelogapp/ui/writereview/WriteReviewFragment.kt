package com.example.lifelogapp.ui.writereview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lifelogapp.MainActivity
import com.example.lifelogapp.R
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.database.LifelogDatabase
import com.example.lifelogapp.databinding.FragmentUpdateBinding
import com.example.lifelogapp.databinding.FragmentWriteReviewBinding
import com.example.lifelogapp.ui.historydetail.HistoryDetailFragmentDirections
import com.example.lifelogapp.ui.historydetail.HistoryDetailViewModel



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
                writeReviewViewModel.doneNavigating()
            }
        })

        writeReviewViewModel.navigateToHome.observe(viewLifecycleOwner, Observer { memo ->
            memo?.let {
                this.findNavController().navigate(
                        WriteReviewFragmentDirections.actionFragmentWriteReviewToFragmentHome())
                writeReviewViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}






