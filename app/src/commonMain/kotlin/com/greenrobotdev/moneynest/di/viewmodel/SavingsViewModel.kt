package com.greenrobotdev.moneynest.di.viewmodel

import com.greenrobotdev.moneynest.di.repository.SavingsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SavingsViewModel : KoinComponent {
    private val savingsRepository: SavingsRepository by inject()

    fun getSavingsAmount(): Double {
        return savingsRepository.getSavings()
    }
}
