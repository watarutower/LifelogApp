package com.example.lifelogapp

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
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        Toolbarを使うためのコード、うまくいかない
//        setSupportActionBar(binding.toolbar)
//        setupActionBarWithNavController(navController, binding.drawerLayout)
//
//        binding.navigationView.setupWithNavController(navController)

//        fabのコード未完
//        val fab :FloatingActionButton = findViewById(R.id.extended_fab)
        val context = this
        val singleItems = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItem = 1


//        val inflator = LayoutInflater.from(context)
//        val slider :Slider = inflator.inflate(R.layout.activity_util)


//            sliderやってたけど、できない。。
//        val continuousSlider : View = findViewById((R.id.continuousSlider))
//
//        continuousSlider.addOnChangeListener(object: Slider.OnChangeListener{
//            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
//                Log.d("addOnChangeListener", slider.value.toString())
//            }
//        })
//          ダイアログをmaterialを使わないでやろうとした
//        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
//        val builder: AlertDialog.Builder? = this?.let {
//            AlertDialog.Builder(it)
//        }
//
//// 2. Chain together various setter methods to set the dialog characteristics
//        builder?.setMessage(R.string.dialog_message)
//                ?.setTitle(R.string.dialog_title)
//
//// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
//        val dialog: AlertDialog? = builder?.create()

        val fab: View = findViewById(R.id.extended_fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            MaterialAlertDialogBuilder(context)
                .setTitle(resources.getString(R.string.dialog_title))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                    // Respond to positive button press
                }
                // Single-choice items (initialized with checked item)
                .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                    // Respond to item chosen
                }
                    .show()

        }



        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_activitylog, R.id.navigation_history, R.id.navigation_mypage))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)





    }


}