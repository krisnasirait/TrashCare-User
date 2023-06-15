package com.trashcare.user.data.model.response.history

import com.google.gson.annotations.SerializedName

data class HistoryResponseItem(
    @SerializedName("Trash ID")
    val trashId: String,
    @SerializedName("Deskripsi")
    val description: String,
    @SerializedName("Lokasi")
    val location: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Jumlah")
    val total: Int,
)