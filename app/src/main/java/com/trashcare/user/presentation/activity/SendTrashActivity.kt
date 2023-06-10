package com.trashcare.user.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivitySendTrashBinding

class SendTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendTrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val markerData = intent.getStringExtra(MARKER_ADD)

        binding.btnSelectLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        if (markerData != null) {
            binding.tvLocationTrash.text = markerData.toString()
        } else {
            binding.tvLocationTrash.text = resources.getString(R.string.no_location)
        }
    }

    companion object {
        const val MARKER_ADD = "marker_add"
    }
}