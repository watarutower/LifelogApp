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
import com.example.lifelogapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update, container, false)
        val updateViewModel: UpdateViewModel
        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

        binding.updateViewModel = updateViewModel

        updateViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {navigate ->
            navigate?.let{
            val navController = findNavController()
                navController.navigate(R.id.action_fragment_update_to_fragment_home)
                updateViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}