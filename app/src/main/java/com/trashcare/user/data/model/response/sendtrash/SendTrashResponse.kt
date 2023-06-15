package com.trashcare.user.data.model.response.sendtrash

import com.google.gson.annotations.SerializedName
import com.trashcare.user.data.model.response.register.Error

data class SendTrashResponse(
    @SerializedName("error")
    val error: Error,
    @SerializedName("message")
    val message: String
)