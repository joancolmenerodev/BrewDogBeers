package com.joancolmenerodev.brewdogbeers.base.persistence.sharedpreferences.di

import android.content.SharedPreferences

operator fun SharedPreferences.get(key: String, defaultValue: Any? = null): Any? {
    return when (defaultValue) {
        is String? -> getString(key, defaultValue ?: "")
        is Int? -> getInt(key, defaultValue ?: -1)
        is Boolean? -> getBoolean(key, defaultValue ?: false)
        is Float? -> getFloat(key, defaultValue ?: -1f)
        is Long? -> getLong(key, defaultValue ?: -1)
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}