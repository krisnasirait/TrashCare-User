package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivityPopUpSuccessBinding
import com.trashcare.user.presentation.fragment.HistoryFragment
import com.trashcare.user.presentation.fragment.HomeFragment

class PopUpSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPopUpSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopUpSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        binding.btnHistory.setOnClickListener {
//            val intent = Intent(this, HistoryFragment::class.java)
//            startActivity(intent)
//        }
    }
}