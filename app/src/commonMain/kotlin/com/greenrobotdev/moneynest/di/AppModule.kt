package com.greenrobotdev.moneynest.di

import com.greenrobotdev.moneynest.di.repository.SavingsRepository
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    single {
        SavingsRepository()
    }
}
