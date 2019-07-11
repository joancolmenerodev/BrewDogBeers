package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.base.repository.SharedPreferencesRepository

class GetSortedOrderUseCase(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    fun execute(key: String, defaultValue: Boolean?) = sharedPreferencesRepository.getValue(key,defaultValue)
}