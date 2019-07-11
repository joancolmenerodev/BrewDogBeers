package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.di

import com.joancolmenerodev.brewdogbeers.base.repository.SharedPreferencesRepository
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter.BeerMatchContract
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter.BeerMatchPresenterImpl
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.repository.MatchBeerRepository
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetMatchBeersUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetSortedOrderUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetUserSearchFromLocalUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.SetSortedOrderUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val matchBeerModule = Kodein.Module("MatchBeerModule"){

    bind<BeerMatchContract.Presenter>() with singleton { BeerMatchPresenterImpl(instance(), instance(), instance(), instance()) }

    bind<GetMatchBeersUseCase>() with singleton { GetMatchBeersUseCase(instance()) }
    bind<GetUserSearchFromLocalUseCase>() with singleton { GetUserSearchFromLocalUseCase(instance()) }
    bind<SetSortedOrderUseCase>() with singleton { SetSortedOrderUseCase(instance()) }
    bind<GetSortedOrderUseCase>() with singleton { GetSortedOrderUseCase(instance()) }


    bind<MatchBeerRepository>() with singleton { MatchBeerRepository(instance(),instance()) }
    bind<SharedPreferencesRepository>() with singleton { SharedPreferencesRepository(instance()) }
}