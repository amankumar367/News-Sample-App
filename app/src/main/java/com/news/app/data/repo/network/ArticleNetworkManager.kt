package com.news.app.data.repo.network

import com.news.app.data.models.ArticleResponse

interface ArticleNetworkManager {

    suspend fun fetchArticle(queryMap: HashMap<String, String?>): ArticleResponse

}