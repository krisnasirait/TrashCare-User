package com.trashcare.user.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.trashcare.user.TrashCareUserApp
import com.trashcare.user.data.model.request.LoginRequestBody
import com.trashcare.user.data.model.request.RegisterRequestBody
import com.trashcare.user.data.model.response.login.LoginResponse
import com.trashcare.user.data.model.response.register.RegisterResponse
import com.trashcare.user.data.remote.ApiService
import retrofit2.Response


class UserRepository (
    private val apiService: ApiService
        ) {

    private val sharedPreferences: SharedPreferences by lazy {
        TrashCareUserApp.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    suspend fun registerUser(registerRequestBody: RegisterRequestBody) : Response<RegisterResponse> {
        return apiService.registerUser(registerRequestBody)
    }

    suspend fun loginUser(loginUserRequestBody: LoginRequestBody) : Response<LoginResponse> {
        return apiService.loginUser(loginUserRequestBody)
    }


    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
        Log.d("AuthViewModel", "Saving token: $token")
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }


    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID_KEY, userId)
        editor.apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID_KEY, null)
    }

    fun clearUserId() {
        val editor = sharedPreferences.edit()
        editor.remove(USER_ID_KEY)
        editor.apply()
    }

//    fun setLoggedInStatus(isLoggedIn: Boolean) {
//        val editor = sharedPreferences.edit()
//        editor.putBoolean(LOGIN_STATUS_KEY, isLoggedIn)
//        editor.apply()
//    }
//
//    fun isLoggedIn(): Boolean {
//        return sharedPreferences.getBoolean(LOGIN_STATUS_KEY, false)
//    }


    companion object {
        private const val PREFS_NAME = "MyPrefsFile"
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "user_id"
//        private const val LOGIN_STATUS_KEY = "login_status"
    }
}