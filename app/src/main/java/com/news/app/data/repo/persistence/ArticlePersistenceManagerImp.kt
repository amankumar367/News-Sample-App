package com.news.app.data.repo.persistence

import com.news.app.data.models.Article
import com.news.app.data.room.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticlePersistenceManagerImp @Inject constructor(
    private val database: AppDatabase
) : ArticlePersistenceManager {

    override fun deleteAllArticles(): Completable {
        return database.newsDao().deleteAllArticles()
    }

    override fun saveArticles(articles: List<Article>): Completable {
        return database.newsDao().insertArticles(articles)
    }

    override fun getArticles(): Single<List<Article>> {
        return database.newsDao().getArticles()
    }

}