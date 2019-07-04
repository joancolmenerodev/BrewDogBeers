package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository.MatchBeerRepository

class GetMatchBeersUseCase(private val matchBeerRepository: MatchBeerRepository) {

    fun execute(food: String) = matchBeerRepository.getMatchBeer(food)
}