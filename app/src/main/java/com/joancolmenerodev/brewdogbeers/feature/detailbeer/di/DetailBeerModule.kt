package com.joancolmenerodev.brewdogbeers.feature.detailbeer.di

import com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter.DetailBeerContract
import com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter.DetailBeerPresenterImpl
import com.joancolmenerodev.brewdogbeers.feature.detailbeer.usecase.GetBeerDetailsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val detailBeerModule = Kodein.Module("detailBeerModule") {

    bind<DetailBeerContract.Presenter>() with singleton { DetailBeerPresenterImpl(instance()) }

    bind<GetBeerDetailsUseCase>() with singleton { GetBeerDetailsUseCase(instance()) }

}