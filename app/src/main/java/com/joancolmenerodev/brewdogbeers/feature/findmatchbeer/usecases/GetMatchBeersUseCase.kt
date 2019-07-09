package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases

import android.util.Log
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewFood
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository.MatchBeerRepository
import io.reactivex.Maybe
import io.reactivex.Observable

class GetMatchBeersUseCase(
    private val matchBeerRepository: MatchBeerRepository
) {

    fun execute(food: String): Maybe<List<BrewBeer>> {
        val fk_id = -1

        return matchBeerRepository.findWordsLocally(food).flatMap {
            Log.d("UseCase", "I found ${it.name} in db")
            matchBeerRepository.getBeersByFoodLocally(food)
        }
            .switchIfEmpty(
                matchBeerRepository.getMatchBeer(food)
                    .flatMapMaybe { beerList ->
                        Log.d("UseCase", "I got ${beerList.size} from API")
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
                                Log.d("UseCase", "I'm inserting $brewBeer to DB")
                                Log.d("UseCase", "I'm inserting $food to DB")
                                matchBeerRepository.insertBrewBeer(brewBeer)
                            }.andThen(
                                matchBeerRepository.insertBrewSearch(BrewFood(food))
                            ).andThen(Maybe.just(brewBeers))
                    }
            )
    }

}

