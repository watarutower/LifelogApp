package com.example.lifelogapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lifelogapp.ui.activitylog.ActivityLogFragment
import com.example.lifelogapp.ui.history.HistoryFragment
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
//        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_activitylog, R.id.navigation_history, R.id.navigation_mypage))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        val activityLogFragment = ActivityLogFragment()
//        val historyFragment = HistoryFragment()
//        val myPageFragment = MyPageFragment()
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {menuItem
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_activitylog -> {
                    val fragment = ActivityLogFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    val fragment = HistoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }



    }
}