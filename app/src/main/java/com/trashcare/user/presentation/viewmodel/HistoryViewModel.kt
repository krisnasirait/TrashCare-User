package com.trashcare.user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trashcare.user.data.model.historydata.HistoryData
import com.trashcare.user.data.model.response.history.HistoryResponse
import com.trashcare.user.data.model.response.history.HistoryResponseItem
import com.trashcare.user.data.model.trashdata.TrashData
import com.trashcare.user.data.repository.TrashRepository
import com.trashcare.user.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class HistoryViewModel(
    private val userRepository: UserRepository,
    private val trashRepository: TrashRepository
): ViewModel() {

//    private val _history = MutableLiveData<HistoryData>()
//    val getHistory: LiveData<HistoryData?> = _history

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _historyUser = MutableLiveData<List<HistoryResponseItem>>()
    val historyUser: LiveData<List<HistoryResponseItem>> = _historyUser

    private fun handleHistoryError(response: Response<HistoryResponse>) {
        val errorMessage = response.errorBody()?.string()
        if (errorMessage != null) {
            try {
                val errorJson = JSONObject(errorMessage)
                val error = errorJson.getString("message")
                Log.d("historyTrash", "error $error")
                _errorMessage.value = error
            } catch (e: JSONException) {
                Log.d("historyTrash", "JSON parsing error: ${e.message}")
                _errorMessage.value = "Unexpected error occurred"
            }
        } else {
            _errorMessage.value = "Unexpected error occurred"
        }
    }

    fun getHistory(userId: String) {
        Log.d("sendTrashCalled", "sendTrash: in view model")
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                trashRepository.getHistory(userId)
            }.onSuccess { response ->
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _historyUser.value = response.body()
                    } else {
                        handleHistoryError(response)
                    }
                }
            }.onFailure { e ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }


    fun getUserId(): String? {
        return userRepository.getUserId()
    }
}