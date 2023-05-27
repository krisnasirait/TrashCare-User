@file:Suppress("DEPRECATION")

package com.trashcare.user.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.trashcare.user.databinding.ActivityOnboardPageBinding
import com.trashcare.user.ui.inputdata.InputDataActivity

class OnboardPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, InputDataActivity::class.java)
            startActivity(intent)
        }
    }
}