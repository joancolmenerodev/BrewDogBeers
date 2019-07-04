package com.joancolmenerodev.brewdogbeers.service

import com.joancolmenerodev.brewdogbeers.base.networking.Either
import com.joancolmenerodev.brewdogbeers.base.networking.Failure
import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BrewDogService {

    @GET("/beers")
    fun getMatchingBeersByFood(@Query("food") food: String) : Single<Either<Failure, List<Beer>>>
}