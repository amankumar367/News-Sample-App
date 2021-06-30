package com.news.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.app.data.repo.ArticleRepository
import com.news.app.data.repo.ArticleRepositoryImp
import com.news.app.data.repo.network.ArticleNetworkManager
import com.news.app.data.repo.network.ArticleNetworkManagerImp
import com.news.app.data.repo.persistence.ArticlePersistenceManager
import com.news.app.data.repo.persistence.ArticlePersistenceManagerImp
import com.news.app.di.ViewModelKey
import com.news.app.extensions.ViewModelProviderFactory
import com.news.app.ui.articles.ArticleViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun articleViewModel(viewModel: ArticleViewModel): ViewModel

}

@Module
class RepositoryModule {

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