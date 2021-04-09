package com.myapplication.lifelogapp.ui.editpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.lifelogapp.R
import com.myapplication.lifelogapp.database.LifelogDao
import com.myapplication.lifelogapp.database.LifelogDatabase
import com.myapplication.lifelogapp.databinding.FragmentEditPageBinding


class EditPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentEditPageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_page, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = EditPageFragmentArgs.fromBundle(arguments)

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = EditPageViewModelFactory(arguments.dayLogId, dataSource)

        val editPageViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(
                EditPageViewModel::class.java
            )

        binding.editPageViewModel = editPageViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }

    class EditPageViewModelFactory(
        private val dayLogId: Long?,
        private val dataSource: LifelogDao
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditPageViewModel::class.java)) {
                return EditPageViewModel(dayLogId, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
