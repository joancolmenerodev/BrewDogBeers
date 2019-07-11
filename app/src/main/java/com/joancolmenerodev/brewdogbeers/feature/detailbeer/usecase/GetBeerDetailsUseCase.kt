package com.joancolmenerodev.brewdogbeers.feature.detailbeer.usecase

import com.joancolmenerodev.brewdogbeers.base.repository.MatchBeerRepository

class GetBeerDetailsUseCase(private val matchBeerRepository: MatchBeerRepository) {

    fun execute(brewBeerId: Int) = matchBeerRepository.getBrewBeer(brewBeerId)
}