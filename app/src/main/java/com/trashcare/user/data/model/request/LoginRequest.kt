package com.trashcare.user.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String? = null,

    @SerializedName("password")
    val password: String? = null
)
