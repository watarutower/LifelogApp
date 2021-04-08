package com.myapplication.lifelogapp.ui.update

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
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

class UpdateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_update, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = LifelogDatabase.getInstance(application).lifeLogDao
        val viewModelFactory = UpdateViewModelFactory(application, dataSource)

        val updateViewModel: UpdateViewModel
        updateViewModel = ViewModelProvider(this, viewModelFactory).get(UpdateViewModel::class.java)

        binding.updateViewModel = updateViewModel


        binding.setLifecycleOwner(this)

        updateViewModel.navigateToHome.observe(viewLifecycleOwner, Observer { navigate ->
            navigate?.let {
                val navController = findNavController()
                navController.navigate(R.id.action_fragment_update_to_fragment_home)
                updateViewModel.doneNavigating()
            }
        })

        createChannel(
                getString(R.string.lifelog_app_id),
                getString(R.string.lifelogapp)
        )

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    // TODO: Step 2.4 change importance
                    NotificationManager.IMPORTANCE_HIGH
            )// TODO: Step 2.6 disable badges for this channel
                    .apply {
                        setShowBadge(false)
                    }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                    NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
        // TODO: Step 1.6 END create a channel
    }
    companion object {
        fun newInstance() = UpdateFragment()
    }


    }


