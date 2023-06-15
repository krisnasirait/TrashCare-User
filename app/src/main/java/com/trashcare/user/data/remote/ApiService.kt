package com.trashcare.user.data.remote

import com.trashcare.user.data.model.request.LoginRequestBody
import com.trashcare.user.data.model.request.RegisterRequestBody
import com.trashcare.user.data.model.response.login.LoginResponse
import com.trashcare.user.data.model.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("apiloginregister/register")
    suspend fun registerUser(
        @Body registerRequestBody: RegisterRequestBody
    ): Response<RegisterResponse>


    @POST("apiloginregister/login")
    suspend fun loginUser(
        @Body loginRequestBody: LoginRequestBody
    ): Response<LoginResponse>

}