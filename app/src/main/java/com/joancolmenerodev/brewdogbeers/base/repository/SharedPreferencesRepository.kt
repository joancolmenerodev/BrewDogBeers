package com.joancolmenerodev.brewdogbeers.base.repository

import android.content.SharedPreferences
import com.joancolmenerodev.brewdogbeers.base.persistence.sharedpreferences.di.get
import com.joancolmenerodev.brewdogbeers.base.persistence.sharedpreferences.di.set

class SharedPreferencesRepository(private val sharedPreferences: SharedPreferences) {

    fun setValue(key: String, value: Any?) {
        sharedPreferences.set(key, value)
    }

    fun getValue(key: String, defaultValue: Any?) = sharedPreferences.get(key, defaultValue)

}