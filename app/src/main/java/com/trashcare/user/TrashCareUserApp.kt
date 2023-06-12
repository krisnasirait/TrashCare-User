package com.trashcare.user

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.trashcare.user.data.remote.ApiService
import com.trashcare.user.data.remote.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class TrashCareUserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@TrashCareUserApp)
            modules(
                repositoryModule
            )
        }
    }

    private val repositoryModule = module {
        single { RetrofitClient.createService<ApiService>() }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


}