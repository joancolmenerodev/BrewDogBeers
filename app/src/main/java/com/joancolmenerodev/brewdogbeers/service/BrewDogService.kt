package com.joancolmenerodev.brewdogbeers.service

import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BrewDogService {

    @GET("beers")
    fun getMatchingBeersByFood(@Query("name") food: String): Single<List<Beer>>
}