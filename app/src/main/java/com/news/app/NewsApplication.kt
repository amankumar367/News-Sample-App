package com.news.app

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, ">>> NewsApplication Created")

        if (BuildConfig.DEBUG) {
            Log.d(TAG, ">>> Initializing Stetho")
            Stetho.initializeWithDefaults(this)
        }

    }

    companion object {
        const val TAG = "NewsApplication"
    }
}