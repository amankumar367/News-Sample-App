package com.news.app.data.repo

import com.news.app.data.models.Article
import io.reactivex.Single

interface ArticleRepository {

    val shouldFetchFromNetwork: Boolean

    fun fetchArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean
    ): Single<List<Article>>

}