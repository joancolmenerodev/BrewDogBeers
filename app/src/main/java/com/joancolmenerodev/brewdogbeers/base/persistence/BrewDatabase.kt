package com.joancolmenerodev.brewdogbeers.base.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BrewSearched::class], version = 1, exportSchema = false)
abstract class BrewDatabase : RoomDatabase() {

    abstract fun brewDao(): BrewDao

}