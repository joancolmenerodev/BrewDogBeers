package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewFood
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository.MatchBeerRepository
import io.reactivex.Maybe
import io.reactivex.Observable

class GetMatchBeersUseCase(
    private val matchBeerRepository: MatchBeerRepository
) {

    fun execute(food: String): Maybe<List<BrewBeer>> {

        return matchBeerRepository.findWordsLocally(food).flatMap {
            matchBeerRepository.getBeersByFoodLocally(food)
        }
            .switchIfEmpty(
                matchBeerRepository.getMatchBeer(food)
                    .flatMapMaybe { beerList ->
                        val brewBeers = beerList.map { beer ->
                            BrewBeer(
                                beer.id,
                                beer.name,
                                beer.tagline,
                                beer.description,
                                beer.imageUrl,
                                beer.abv,
                                beer.foodPairing,
                                food
                            )
                        }
                        Observable.fromIterable(beerList)
                            .map { beer ->
                                BrewBeer(
                                    beer.id,
                                    beer.name,
                                    beer.tagline,
                                    beer.description,
                                    beer.imageUrl,
                                    beer.abv,
                                    beer.foodPairing,
                                    food
                                )
                            }
                            .flatMapCompletable { brewBeer ->
                                matchBeerRepository.insertBrewBeer(brewBeer)
                            }.andThen(
                                matchBeerRepository.insertBrewSearch(BrewFood(food))
                            ).andThen(Maybe.just(brewBeers))
                    }
            )
    }

}

