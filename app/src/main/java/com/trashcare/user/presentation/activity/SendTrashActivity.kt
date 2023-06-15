package com.trashcare.user.presentation.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.trashcare.user.R
import com.trashcare.user.databinding.ActivitySendTrashBinding
import com.trashcare.user.presentation.viewmodel.SendTrashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SendTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendTrashBinding
    private val sendTrashViewModel: SendTrashViewModel by viewModel()
    private lateinit var token: String
    private lateinit var userId: String
    private var _markerData: String? = null
    private var _totalTrash: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }


        val markerData = intent.getStringExtra(MARKER_ADD)
        val totalTrash = intent.getIntExtra("TOTAL_AMOUNT", 0)

//        if (markerData != null) {
//            this._markerData = markerData
//        }
//        this._totalTrash = totalTrash

        binding.btnSelectLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        binding.btnCameraX.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            launcherIntentCameraX.launch(intent)
        }


        if (markerData != null) {
            binding.tvLocationTrash.text = markerData.toString()
        } else {
            binding.tvLocationTrash.text = resources.getString(R.string.no_location)
        }

        binding.tvTotalPcsTrash.text = resources.getString(R.string.total_pcs_trash, totalTrash)

//        val getToken = sendTrashViewModel.getToken()
//        val getUserId = sendTrashViewModel.getUserId()
//
//        if (getToken.isNullOrEmpty()) {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        } else {
//            this.token = getToken
//        }
//
//        if (getUserId.isNullOrEmpty()) {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        } else {
//            this.userId = getUserId
//        }

//        sendTrashViewModel.sendTrash.observe(this) {
//            Toast.makeText(this, "Kirim Sampah Berhasil", Toast.LENGTH_SHORT).show()
//        }

//        sendTrash()

    }

//    private fun sendTrash() {
//        binding.btnSendTrash.setOnClickListener {
//            val description = binding.edDescription.text.toString()
//            if (description.isEmpty()) {
//                binding.edDescription.error = resources.getString(R.string.descriptionEmptyError)
//            } else {
//                sendTrashViewModel.sendTrash(token, userId, description, _markerData ?: "", _totalTrash ?: 0)
//            }
//        }
//    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            myFile?.let { file ->
//                rotateFile(file, isBackCamera)
                binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val MARKER_ADD = "marker_add"
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}