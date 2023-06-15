package com.trashcare.user.data.model.response.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("customToken")
    val token: String,
    @SerializedName("userId")
    val userId: String,
)