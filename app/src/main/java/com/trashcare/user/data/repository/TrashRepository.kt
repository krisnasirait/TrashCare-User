package com.trashcare.user.data.repository

import com.trashcare.user.data.model.response.sendtrash.SendTrashResponse
import com.trashcare.user.data.model.trashdata.TrashData
import com.trashcare.user.data.remote.ApiService
import retrofit2.Response

class TrashRepository(
    private val apiService: ApiService
) {
    suspend fun sendTrash(token: String, userId: String, trashData: TrashData): Response<SendTrashResponse> {
        return apiService.sendTrash("Bearer $token", userId, trashData)
    }
}