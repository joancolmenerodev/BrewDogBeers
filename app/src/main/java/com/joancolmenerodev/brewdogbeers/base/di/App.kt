package com.joancolmenerodev.brewdogbeers.base.di

import android.app.Application
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.di.matchBeerModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(retrofitModule)
        import(matchBeerModule)
    }
}