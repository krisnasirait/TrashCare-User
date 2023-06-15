package com.trashcare.user.data.repository

import com.trashcare.user.data.model.response.sendtrash.SendTrashResponse
import com.trashcare.user.data.remote.ApiService
import retrofit2.Response

class TrashRepository(
    private val apiService: ApiService
) {
    suspend fun sendTrash(token: String, userId: String, description: String, location: String, amount: Int): Response<SendTrashResponse> {
        return apiService.submitTrash("Bearer $token", userId, description, location, amount)
    }
}