package com.trashcare.user.data.model.response.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("error")
    val error: Error,
    @SerializedName("message")
    val message: String
)