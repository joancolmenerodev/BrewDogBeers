package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.base.persistence.BrewDatabase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.model.userSearchMapper

class GetUserSearchFromLocalUseCase(private val brewDatabase: BrewDatabase) {

    fun execute() = brewDatabase.brewDao().getAllBrews().map{ userSearchMapper(it)}
}