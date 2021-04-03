package com.news.app.di

import android.app.Application
import com.news.app.di.module.ActivityBuilderModule
import com.news.app.di.module.NetworkModule
import com.news.app.di.module.RoomModule
import com.news.app.NewsApplication
import com.news.app.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
    RoomModule::class,
    NetworkModule::class
])
interface AppComponent: AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}