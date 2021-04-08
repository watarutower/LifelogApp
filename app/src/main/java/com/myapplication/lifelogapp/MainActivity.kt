package com.myapplication.lifelogapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavDestination
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navView: BottomNavigationView = findViewById(R.id.navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.fragment_home)

                    return@OnNavigationItemSelectedListener true
                }
                //未実装
//                R.id.navigation_worklog  -> {
//
//                    navController.navigate(R.id.fragment_worklog)
//                    return@OnNavigationItemSelectedListener true
//                }
                R.id.navigation_history_index -> {
                    navController.navigate(R.id.fragment_history_index)
                    return@OnNavigationItemSelectedListener true
                }
//               未実装
//                R.id.navigation_mypage -> {
//                    navController.navigate(R.id.fragment_mypage)
//                    return@OnNavigationItemSelectedListener true
//                }

            }
            false
        }
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.fragment_home || nd.id == R.id.fragment_history_index
                    || nd.id == R.id.fragment_history_detail) {
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }
    }
}




