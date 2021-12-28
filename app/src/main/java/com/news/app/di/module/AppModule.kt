package com.news.app.di.module

import com.news.app.data.repo.ArticleRepository
import com.news.app.data.repo.ArticleRepositoryImp
import com.news.app.data.repo.network.ArticleNetworkManager
import com.news.app.data.repo.network.ArticleNetworkManagerImp
import com.news.app.data.repo.persistence.ArticlePersistenceManager
import com.news.app.data.repo.persistence.ArticlePersistenceManagerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun bindArticleNetworkManager(
        articleNetworkManager: ArticleNetworkManagerImp
    ): ArticleNetworkManager

    @Binds
    @Singleton
    fun bindArticlePersistenceManager(
        articlePersistenceManager: ArticlePersistenceManagerImp
    ): ArticlePersistenceManager

    @Binds
    @Singleton
    fun bindArticleRepoRepository(articleRepository: ArticleRepositoryImp): ArticleRepository
}