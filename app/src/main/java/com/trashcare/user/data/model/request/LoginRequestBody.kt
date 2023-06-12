package com.trashcare.user.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    val email: String,
    val password: String
)
