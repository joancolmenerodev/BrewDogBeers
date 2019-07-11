package com.joancolmenerodev.brewdogbeers.base.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dao.BrewBeerDao
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dao.BrewFoodDao
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewFood
import com.joancolmenerodev.brewdogbeers.base.persistence.room.utils.Converters

@Database(entities = [BrewFood::class, BrewBeer::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BrewDatabase : RoomDatabase() {

    abstract fun brewDao(): BrewFoodDao
    abstract fun brewBeersDao(): BrewBeerDao

}