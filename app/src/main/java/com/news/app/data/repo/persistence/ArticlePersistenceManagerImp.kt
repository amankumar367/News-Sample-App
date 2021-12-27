package com.news.app.data.repo.persistence

import com.news.app.data.models.Article
import com.news.app.data.room.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticlePersistenceManagerImp @Inject constructor(
    private val database: AppDatabase
) : ArticlePersistenceManager {

    override suspend fun deleteAllArticles(): Boolean {
        return try {
            database.newsDao().deleteAllArticles()
            true
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateArticles(articles: List<Article>): Boolean {
        return try {
            database.newsDao().updateArticles(articles)
            true
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun getArticles(): List<Article> {
        return database.newsDao().getArticles()
    }

}