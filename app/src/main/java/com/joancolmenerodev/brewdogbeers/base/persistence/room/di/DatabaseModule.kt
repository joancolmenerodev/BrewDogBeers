package com.joancolmenerodev.brewdogbeers.base.persistence.room.di

import androidx.room.Room
import com.joancolmenerodev.brewdogbeers.base.di.App
import com.joancolmenerodev.brewdogbeers.base.persistence.room.BrewDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataBaseModule = Kodein.Module("databaseModule") {

    bind<BrewDatabase>() with singleton {
        Room.databaseBuilder(
            instance<App>(),
            BrewDatabase::class.java,
            "sample_db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}