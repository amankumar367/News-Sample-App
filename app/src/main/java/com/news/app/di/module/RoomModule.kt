package com.news.app.di.module

import android.app.Application
import com.news.app.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(applicationContext: Application): AppDatabase {
        return AppDatabase.getInstance(applicationContext)!!
    }

}