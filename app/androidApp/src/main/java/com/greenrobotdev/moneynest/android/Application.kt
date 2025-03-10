package com.greenrobotdev.moneynest.android

import android.app.Application
import com.greenrobotdev.moneynest.di.appModule
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }

    }

}

