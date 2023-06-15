package com.trashcare.user.data.remote

import com.trashcare.user.data.model.historydata.HistoryData
import com.trashcare.user.data.model.request.LoginRequestBody
import com.trashcare.user.data.model.request.RegisterRequestBody
import com.trashcare.user.data.model.response.history.HistoryResponse
import com.trashcare.user.data.model.response.login.LoginResponse
import com.trashcare.user.data.model.response.register.RegisterResponse
import com.trashcare.user.data.model.response.sendtrash.SendTrashResponse
import com.trashcare.user.data.model.trashdata.TrashData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
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


    @Headers("Content-Type: application/json")
    @POST("apisubmittrash/submittrash")
    suspend fun sendTrash(
        @Header("Authorization") token: String,
        @Header("userId") userId: String,
        @Body trashData: TrashData
    ): Response<SendTrashResponse>

    @GET("apigetuserhistory/historyuser")
    suspend fun getHistory(
        @Header("userId") userId: String,
    ): Response<HistoryResponse>

}