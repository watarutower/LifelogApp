package com.example.lifelogapp.ui.activitylog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.R

class ActivityLogFragment : Fragment() {

    private lateinit var activityLogViewModel: ActivityLogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activityLogViewModel =
                ViewModelProvider(this).get(ActivityLogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_activitylog, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        activityLogViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}