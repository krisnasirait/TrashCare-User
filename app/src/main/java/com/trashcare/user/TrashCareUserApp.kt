package com.trashcare.user

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.trashcare.user.data.remote.ApiService
import com.trashcare.user.data.remote.RetrofitClient
import com.trashcare.user.data.repository.TrashRepository
import com.trashcare.user.data.repository.UserRepository
import com.trashcare.user.presentation.viewmodel.AuthViewModel
import com.trashcare.user.presentation.viewmodel.HistoryViewModel
import com.trashcare.user.presentation.viewmodel.SendTrashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
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
                repositoryModule,
                vmModule
            )
        }
    }

    private val vmModule = module {
        viewModel { AuthViewModel(get()) }
        viewModel { SendTrashViewModel(get(), get()) }
        viewModel { HistoryViewModel(get(), get()) }
    }

    private val repositoryModule = module {
        single { RetrofitClient.createService<ApiService>() }
        single { UserRepository(get()) }
        single { TrashRepository(get()) }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


}