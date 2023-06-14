package com.trashcare.user.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val bankName: String,
    val accountName: String,
    val accountNumber: String,
)
