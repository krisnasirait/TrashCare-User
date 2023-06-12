package com.trashcare.user.data.model.trashlocation

import com.google.android.gms.maps.model.LatLng

data class LocationTrash(
    val location: LatLng,
    val title: String,
    val snippet: String,
)

val dummyLocationTrash = listOf(
    LocationTrash(LatLng(-6.37251980562147, 106.88602089436743), "Duren Sawit, Jakarta Timur", "Jl. Laut Banda Duren Sawit, Jakarta Timur"),
    LocationTrash(LatLng(-6.32188309619532, 106.81032107787063), "Jagakarsa, Jakarta Selatan", "Jl. M Kahfi 1 Gg Tohir 2 RT 03 RW 04 Jagakarsa Jakarta Selatan"),
    LocationTrash(LatLng(-6.238389418305545, 106.75542740970958), "Pesanggrahan, Jakarta Selatan", "Jl. Pesanggrahan Raya, Jakarta Selatan"),
    LocationTrash(LatLng(-6.115803686852089, 106.7149099179417), "Kalideres, Jakarta Barat", "Jl. Bhakti Mulia Tegal Alur, Jakarta Barat"),
)
