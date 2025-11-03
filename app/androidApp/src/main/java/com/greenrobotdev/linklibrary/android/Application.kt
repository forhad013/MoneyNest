package com.greenrobotdev.linklibrary.android

import android.app.Application
import com.greenrobotdev.linklibrary.di.appModule
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }

    }

}

