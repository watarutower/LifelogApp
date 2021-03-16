package com.example.lifelogapp.ui.update

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
import com.example.lifelogapp.databinding.FragmentUpdateBinding
import com.example.lifelogapp.ui.historydetail.HistoryDetailViewModelFactory
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_util.*

class UpdateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = UpdateViewModelFactory(dataSource, application)

        val updateViewModel: UpdateViewModel
        updateViewModel = ViewModelProvider(this, viewModelFactory).get(UpdateViewModel::class.java)

        binding.updateViewModel = updateViewModel


        binding.setLifecycleOwner(this)

        updateViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {navigate ->
            navigate?.let{
                val navController = findNavController()
                navController.navigate(R.id.action_fragment_update_to_fragment_home)
                updateViewModel.doneNavigating()
            }
        })

        return binding.root
    }

//    val mSlider = slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
//        override fun onStartTrackingTouch(slider: Slider) {
//            // Responds to when slider's touch event is being started
//        }
//
//        override fun onStopTrackingTouch(slider: Slider) {
//            // Responds to when slider's touch event is being stopped
//        }
//
//    })


}

