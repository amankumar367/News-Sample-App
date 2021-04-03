package com.news.app.di.module

import com.news.app.repo.ArticleRepo
import com.news.app.repo.repointerface.IArticleRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideArticleRepo(articleRepo: ArticleRepo): IArticleRepo {
        return articleRepo
    }
}