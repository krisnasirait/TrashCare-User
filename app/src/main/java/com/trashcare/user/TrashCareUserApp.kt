package com.trashcare.user

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class TrashCareUserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@TrashCareUserApp)
            modules(
                // TODO: add modules here
            )
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


}