package com.trashcare.user.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.trashcare.user.data.remote.ApiService

class UserRepository private constructor(private val dataStore: DataStore<Preferences>, private val apiService: ApiService){

}