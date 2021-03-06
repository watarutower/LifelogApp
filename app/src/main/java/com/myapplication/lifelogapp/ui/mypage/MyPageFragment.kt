package com.myapplication.lifelogapp.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapplication.lifelogapp.R

class MyPageFragment : Fragment()  {
    private lateinit var myPageViewModel: MyPageViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myPageViewModel =
                ViewModelProvider(this).get(MyPageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mypage, container, false)
        val textView: TextView = root.findViewById(R.id.text_mypage)
        myPageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}