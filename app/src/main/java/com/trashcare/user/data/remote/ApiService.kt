package com.trashcare.user.data.remote

import com.trashcare.user.data.model.request.LoginRequestBody
import com.trashcare.user.data.model.request.RegisterRequestBody
import com.trashcare.user.data.model.response.login.LoginResponse
import com.trashcare.user.data.model.response.register.RegisterResponse
import com.trashcare.user.data.model.response.sendtrash.SendTrashResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
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

    @POST("apisubmittrash/submittrash")
    suspend fun submitTrash(
        @Header("Authorization") Authorization: String,
        @Header("userId") userId: String,
        @Field("description") description: String,
        @Field("location") location: String?,
        @Field("amount") amount: Int?,
    ): Response<SendTrashResponse>

}