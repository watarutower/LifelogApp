package com.example.lifelogapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController

//import com.example.lifelogapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navView: BottomNavigationView = findViewById(R.id.navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home  -> {
                    navController.navigate(R.id.fragment_home)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_worklog  -> {

                    navController.navigate(R.id.fragment_worklog)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_history_index -> {
                    navController.navigate(R.id.fragment_history_index)
                    return@OnNavigationItemSelectedListener true
                }
//                R.id.navigation_mypage -> {
//                    navController.navigate(R.id.fragment_mypage)
//                    return@OnNavigationItemSelectedListener true
//                }

            }
            false
        }
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }



}