package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.base.repository.SharedPreferencesRepository

class SetSortedOrderUseCase(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    fun execute(key: String, value: Any) = sharedPreferencesRepository.setValue(key,value)
}