package com.example.lifelogapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lifelogapp.ui.activitylog.ActivityLogFragment

import com.example.lifelogapp.ui.home.HomeFragment

import com.example.lifelogapp.ui.mypage.MyPageFragment
import com.example.lifelogapp.ui.update.UpdateFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar

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
                R.id.navigation_activitylog  -> {

                    navController.navigate(R.id.fragment_activitylog)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_history_index -> {
                    navController.navigate(R.id.fragment_history_index)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_mypage -> {
                    navController.navigate(R.id.fragment_mypage)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }



}