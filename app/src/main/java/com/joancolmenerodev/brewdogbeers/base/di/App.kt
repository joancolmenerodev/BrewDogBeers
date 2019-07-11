package com.joancolmenerodev.brewdogbeers.base.di

import android.app.Application
import com.joancolmenerodev.brewdogbeers.base.persistence.room.di.dataBaseModule
import com.joancolmenerodev.brewdogbeers.base.persistence.sharedpreferences.di.sharedPreferencesModule
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.di.matchBeerModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider


class App : Application(), KodeinAware {

    private val contextModule = Kodein.Module("contextModule") {
        bind<App>() with provider { this@App }
    }

    override val kodein by Kodein.lazy {
        import(retrofitModule)
        import(matchBeerModule)
        import(contextModule)
        import(dataBaseModule)
        import(sharedPreferencesModule)
    }
}