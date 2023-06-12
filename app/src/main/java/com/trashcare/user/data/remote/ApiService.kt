package com.trashcare.user.data.remote

import com.trashcare.user.data.model.request.LoginRequest
import com.trashcare.user.data.model.request.RegisterRequest
import com.trashcare.user.data.model.response.LoginResponse
import com.trashcare.user.data.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>


    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}