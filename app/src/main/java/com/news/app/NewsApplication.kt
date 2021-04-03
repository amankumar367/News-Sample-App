package com.news.app

import android.util.Log
import com.facebook.stetho.Stetho
import com.news.app.di.AppComponent
import com.news.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class NewsApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {

        Log.d(TAG, ">>> NewsApplication Created")

        if (BuildConfig.DEBUG) {
            Log.d(TAG, ">>> Initializing Stetho")
            Stetho.initializeWithDefaults(this)
        }

        appComponent = DaggerAppComponent.builder().application(this).build()

        return appComponent
    }

    companion object {
        const val TAG = "NewsApplication"

        private var appComponent: AppComponent? = null

        fun getAppComponent(): AppComponent? {
            return appComponent
        }
    }
}