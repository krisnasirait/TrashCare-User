package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        bottomNav = binding.bottomNav
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_camera -> {
                    navController.navigate(R.id.action_camera)
                    true
                }
                R.id.action_home -> {
                    navController.navigate(R.id.action_home)
                    true
                }
                R.id.action_history -> {
                    navController.navigate(R.id.action_history)
                    true
                }
                else -> false
            }
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(this, CameraActivity::class.java)
        startActivity(takePictureIntent)
    }
}