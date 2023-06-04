package com.trashcare.user.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.databinding.ActivityDetailTrashBinding

class DetailTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}