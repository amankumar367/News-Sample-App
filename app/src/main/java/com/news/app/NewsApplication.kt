package com.news.app

import android.util.Log
import com.facebook.stetho.Stetho
import com.news.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class NewsApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

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