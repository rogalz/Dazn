package com.example.dazn

import android.app.Application
import com.example.dazn.data.di.dataModule
import com.example.dazn.domain.di.domainModule
import com.example.dazn.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DaznApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DaznApplication)
            modules(dataModule, domainModule, uiModule)
        }
    }
}
