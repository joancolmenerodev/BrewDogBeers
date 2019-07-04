package com.joancolmenerodev.brewdogbeers.base.di

import com.joancolmenerodev.brewdogbeers.service.BrewDogService
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = Kodein.Module("retrofitModule") {

    bind<OkHttpClient>() with singleton {
        OkHttpClient().newBuilder().build()
    }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .client(instance())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    bind<BrewDogService>() with singleton {
        instance<Retrofit>().create(BrewDogService::class.java)
    }
}
