package com.trashcare.user.presentation.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.trashcare.user.R
import com.trashcare.user.data.model.trashlocation.dummyLocationTrash
import com.trashcare.user.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var selectedMarkerData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val builder = LatLngBounds.Builder()


        for (location in dummyLocationTrash) {
            val markerOptions = MarkerOptions()
                .position(location.location)
                .title(location.title)
                .snippet(location.snippet)
                .icon(bitmapDescriptorFromVector(this,R.drawable.baseline_trash_24))

            mMap.addMarker(markerOptions)
            builder.include(location.location)
        }

        val bounds: LatLngBounds = builder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                150
            )
        )


        mMap.setOnMarkerClickListener { marker ->
            selectedMarkerData = marker.snippet.toString()

            binding.btnAddLocation.visibility = View.VISIBLE

            false
        }

        binding.btnAddLocation.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MARKER_ADD, selectedMarkerData)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    companion object {
        const val MARKER_ADD = "marker_add"
    }
}