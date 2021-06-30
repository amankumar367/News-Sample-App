package com.news.app.di.module

import com.news.app.data.repo.ArticleRepositoryImp
import com.news.app.data.repo.ArticleRepository
import com.news.app.data.repo.network.ArticleNetworkManager
import com.news.app.data.repo.network.ArticleNetworkManagerImp
import com.news.app.data.repo.persistence.ArticlePersistenceManager
import com.news.app.data.repo.persistence.ArticlePersistenceManagerImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideArticleNetworkManager(
        articleNetworkManager: ArticleNetworkManagerImp
    ): ArticleNetworkManager = articleNetworkManager

    @Provides
    @Singleton
    fun provideArticlePersistenceManager(
        articlePersistenceManager: ArticlePersistenceManagerImp
    ): ArticlePersistenceManager = articlePersistenceManager

    @Provides
    @Singleton
    fun provideArticleRepo(articleRepo: ArticleRepositoryImp): ArticleRepository = articleRepo

}