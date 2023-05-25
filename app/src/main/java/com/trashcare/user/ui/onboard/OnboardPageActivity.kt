package com.trashcare.user.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.MainActivity
import com.trashcare.user.databinding.ActivityOnboardPageBinding

class OnboardPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}