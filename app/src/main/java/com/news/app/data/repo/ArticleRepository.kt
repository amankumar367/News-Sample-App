package com.news.app.data.repo

import com.news.app.data.models.Article

interface ArticleRepository {

    val shouldFetchFromNetwork: Boolean

    suspend fun fetchArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean
    ): List<Article>

}