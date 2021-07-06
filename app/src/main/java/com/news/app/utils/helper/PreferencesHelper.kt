package com.news.app.utils.helper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
open class PreferencesHelper @Inject constructor(context: Application) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.news.app"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val bufferPref: SharedPreferences =
        context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = bufferPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = bufferPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    /**
     * This fun clear the saved SharedPreference
     */
    fun clearPreferences() = bufferPref.edit().clear().apply()
}
