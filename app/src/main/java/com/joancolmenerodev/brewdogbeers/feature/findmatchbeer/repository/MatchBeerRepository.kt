package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository

import com.joancolmenerodev.brewdogbeers.service.BrewDogService

class MatchBeerRepository(private val brewDogService: BrewDogService) {

    fun getMatchBeer(food: String) = brewDogService.getMatchingBeersByFood(food)
}