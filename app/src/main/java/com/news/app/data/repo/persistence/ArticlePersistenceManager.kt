package com.news.app.data.repo.persistence

import com.news.app.data.models.Article

interface ArticlePersistenceManager {

    suspend fun deleteAllArticles(): Boolean

    suspend fun updateArticles(articles: List<Article>): Boolean

    suspend fun getArticles(): List<Article>

}