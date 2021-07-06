package com.news.app.data.repo.persistence

import com.news.app.data.models.Article
import io.reactivex.Completable
import io.reactivex.Single

interface ArticlePersistenceManager {

    fun deleteAllArticles(): Completable

    fun saveArticles(articles: List<Article>): Completable

    fun getArticles(): Single<List<Article>>

}