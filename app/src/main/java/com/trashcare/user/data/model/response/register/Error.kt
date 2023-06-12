package com.trashcare.user.data.model.response.register


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String
)