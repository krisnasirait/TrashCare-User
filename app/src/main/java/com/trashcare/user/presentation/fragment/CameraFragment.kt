package com.trashcare.user.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.trashcare.user.R
import com.trashcare.user.databinding.FragmentCameraBinding
import com.trashcare.user.utils.ImageClassifier
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.log


class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var imageClassifier: ImageClassifier
    private lateinit var imageCapture: ImageCapture
    private lateinit var preview: Preview

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
        imageClassifier = ImageClassifier(requireContext(), 180)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            preview = Preview.Builder().build()
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

            preview.setSurfaceProvider(binding.viewFinder.surfaceProvider)
        }, ContextCompat.getMainExecutor(requireContext()))

        binding.captureImage.setOnClickListener {
            takePictureAndClassify()
        }

        binding.galleryPick.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    private fun takePictureAndClassify() {
        val outputDirectory = getOutputDirectory()
        val photoFile = File(outputDirectory, SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis()) + ".jpg")

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val msg = "Photo capture succeeded: ${outputFileResults.savedUri}"
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                Log.d("CameraXApp", msg)

                val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 180, 180, false)
                classifyImage(scaledBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraXApp", "Photo capture failed: ${exception.message}", exception)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val bitmapOriginal = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
            val bitmap = Bitmap.createScaledBitmap(bitmapOriginal, 180, 180, false)  // Mengubah ukuran bitmap
            classifyImage(bitmap)
        }
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireContext().externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireContext().filesDir
    }

    private fun classifyImage(bitmap: Bitmap) {
        val results = imageClassifier.classify(bitmap)
        displayResults(results)
    }

    private fun displayResults(results: List<String>) {
        val maxResult = results.find { it.contains(":1.0") }
        if (maxResult != null) {
            Toast.makeText(requireContext(), maxResult, Toast.LENGTH_SHORT).show()
            Log.d("modelTest", "displayResults: $maxResult")
        } else {
            Toast.makeText(requireContext(), "No Result found with 1.0", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
    }
}