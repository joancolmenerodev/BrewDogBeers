package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface BrewBeerDao {

    @Query("SELECT * FROM brewbeer WHERE beer_id = :id")
    fun getBrewBeerById(id: String): Maybe<BrewBeer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBrewBeer(brewBeer: BrewBeer) : Completable

    @Query("SELECT * from brewbeer WHERE pairing_food GLOB '*' || :food")
    fun getAllBrewsBeers(food: String): Maybe<List<BrewBeer>>
}