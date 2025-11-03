package com.greenrobotdev.linklibrary.di

import com.greenrobotdev.linklibrary.data.repository.LinkRepositoryImpl
import com.greenrobotdev.linklibrary.domain.repository.LinkRepository
import org.koin.dsl.module

val appModule = module {
    single<LinkRepository> { LinkRepositoryImpl() }
}
