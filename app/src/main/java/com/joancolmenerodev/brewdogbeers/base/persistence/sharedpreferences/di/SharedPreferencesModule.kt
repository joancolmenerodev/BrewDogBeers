package com.joancolmenerodev.brewdogbeers.base.persistence.sharedpreferences.di

import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val sharedPreferencesModule = Kodein.Module("sharedPreferencesModule") {

    bind<SharedPreferences>() with singleton { PreferenceManager.getDefaultSharedPreferences(instance()) }
}