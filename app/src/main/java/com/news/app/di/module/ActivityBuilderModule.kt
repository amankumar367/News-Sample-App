package com.news.app.di.module

import com.news.app.ui.articles.ArticleActivity
import com.news.app.ui.articles.ArticleDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun articleActivityProvider() : ArticleActivity

    @ContributesAndroidInjector()
    abstract fun articleDetailsActivityProvider() : ArticleDetailsActivity

}
