package com.example.maybank

import android.app.Application
import com.example.maybank.ui.main.mainModule
import com.example.maybank.services.networkModule
import com.example.maybank.services.repository
import com.example.maybank.ui.main.mainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MaybankApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree())
        Timber.e("Run")
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MaybankApp)
            modules(
                listOf(
                    networkModule,
                    repository,
                    mainModule,
                    mainViewModel
                )
            )
        }
    }

}