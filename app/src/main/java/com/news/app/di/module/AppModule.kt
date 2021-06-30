package com.news.app.di.module

import com.news.app.data.repo.ArticleRepositoryImp
import com.news.app.data.repo.ArticleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideArticleRepo(articleRepo: ArticleRepositoryImp): ArticleRepository {
        return articleRepo
    }
}