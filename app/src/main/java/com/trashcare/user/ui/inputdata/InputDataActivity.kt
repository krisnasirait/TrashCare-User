package com.trashcare.user.ui.inputdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.databinding.ActivityInputDataBinding
import com.trashcare.user.ui.welcome.WelcomeActivity

class InputDataActivity : AppCompatActivity() {
    private lateinit var binding  : ActivityInputDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitData.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}