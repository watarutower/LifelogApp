package com.example.lifelogapp.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lifelogapp.R
import com.example.lifelogapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update, container, false)

        return binding.root
    }
}