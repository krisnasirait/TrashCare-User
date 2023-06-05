package com.trashcare.user.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trashcare.user.databinding.ActivityDetailTrashBinding

@Suppress("DEPRECATION")
class DetailTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTrashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvTitle = binding.tvDetailTitle
        val tvDesc = binding.tvDetailDesc
        val ivPhoto = binding.ivImgDetail

        val tTitle = intent.getStringExtra(EXTRA_TITLE)
        val tDesc = intent.getStringExtra(EXTRA_DESC)
        val tPhoto = intent.getIntExtra(EXTRA_PHOTO, 0)

        tvTitle.text = tTitle
        tvDesc.text = tDesc
        ivPhoto.setImageResource(tPhoto)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_PHOTO = "extra_photo"
    }
}