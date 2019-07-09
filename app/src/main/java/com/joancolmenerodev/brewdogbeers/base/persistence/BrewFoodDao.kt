package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface BrewFoodDao {

    @Query("SELECT * FROM brew_food WHERE name = :food")
    fun getBrewFoodByFood(food: String): Maybe<BrewFood>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBrewSearch(food: BrewFood) : Completable

    @Query("SELECT * from brew_food")
    fun getAllBrews(): Flowable<List<BrewFood>>

}

