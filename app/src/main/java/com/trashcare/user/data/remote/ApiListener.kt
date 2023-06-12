package com.trashcare.user.data.remote

import android.util.Log

interface ApiListener<T> {
    fun onSuccess(body: T?)
    fun onFailure(error: Throwable)
    fun onCancel() {
        Log.d("ApiListener", "canceled")
    }
}
