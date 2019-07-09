package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository

import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewDatabase
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewFood
import com.joancolmenerodev.brewdogbeers.service.BrewDogService

class MatchBeerRepository(
    private val brewDogService: BrewDogService,
    private val brewDatabase: BrewDatabase
) {
    fun getMatchBeer(food: String) = brewDogService.getMatchingBeersByFood(food)
    fun findWordsLocally(food: String) = brewDatabase.brewDao().getBrewFoodByFood(food)
    fun getBeersByFoodLocally(food: String) = brewDatabase.brewBeersDao().getAllBrewsBeers(food)
    fun insertBrewSearch(brewFood: BrewFood) = brewDatabase.brewDao().insertBrewSearch(brewFood)
    fun insertBrewBeer(brewBeer: BrewBeer) = brewDatabase.brewBeersDao().insertBrewBeer(brewBeer)
}