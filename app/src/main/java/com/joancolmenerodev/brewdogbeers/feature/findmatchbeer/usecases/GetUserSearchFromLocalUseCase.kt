package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.base.persistence.room.BrewDatabase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.model.userSearchMapper
import io.reactivex.Flowable

class GetUserSearchFromLocalUseCase(private val brewDatabase: BrewDatabase) {

    fun execute(): Flowable<List<String>> = brewDatabase.brewDao().getAllBrews().map{ userSearchMapper(it)}
}