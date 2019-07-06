package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface BrewDao {

    @Query("SELECT * FROM brewsearched WHERE food = :id")
    fun getFoodById(id: String): Maybe<BrewSearched>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(food: BrewSearched)

    @Query("SELECT * from brewsearched")
    fun getAllBrews(): Flowable<List<BrewSearched>>
}

