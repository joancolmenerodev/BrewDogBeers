package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BrewFood::class, BrewBeer::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BrewDatabase : RoomDatabase() {

    abstract fun brewDao(): BrewFoodDao
    abstract fun brewBeersDao(): BrewBeerDao

}