package com.trashcare.user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trashcare.user.data.model.response.sendtrash.SendTrashResponse
import com.trashcare.user.data.repository.TrashRepository
import com.trashcare.user.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class SendTrashViewModel(
    private val userRepository: UserRepository,
    private val trashRepository: TrashRepository
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _sendTrash = MutableLiveData<SendTrashResponse>()
    val sendTrash: LiveData<SendTrashResponse?> = _sendTrash


    private fun handleSubmitTrashError(response: Response<SendTrashResponse>) {
        val errorMessage = response.errorBody()?.string()
        if (errorMessage != null) {
            try {
                val errorJson = JSONObject(errorMessage)
                val error = errorJson.getString("message")
                Log.d("sendTrashMessage", "error $error")
                _errorMessage.value = error
            } catch (e: JSONException) {
                Log.d("sendTrashMessage", "JSON parsing error: ${e.message}")
                _errorMessage.value = "Unexpected error occurred"
            }
        } else {
            _errorMessage.value = "Unexpected error occurred"
        }
    }

    fun sendTrash(token: String, userId: String, description: String, location: String, amount: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                trashRepository.sendTrash(token, userId, description, location, amount)
            }.onSuccess { response ->
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _sendTrash.value = response.body()
                    } else {
                        handleSubmitTrashError(response)
                    }
                }
            }.onFailure { e ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }


    fun getToken(): String? {
        return userRepository.getToken()
    }


    fun getUserId(): String? {
        return userRepository.getUserId()
    }
}