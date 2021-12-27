package com.news.app.data.repo.network

import com.news.app.data.models.ArticleResponse
import com.news.app.data.network.ApiInterface
import com.news.app.extensions.returnNoDataException
import javax.inject.Inject

class ArticleNetworkManagerImp @Inject constructor(
    private val apiInterface: ApiInterface
) : ArticleNetworkManager {

    override suspend fun fetchArticle(queryMap: HashMap<String, String?>): ArticleResponse {
        val articles = apiInterface.fetchArticles(queryMap)
        return if (articles.isSuccessful)
            articles.body() ?: returnNoDataException()
        else
            returnNoDataException()
    }

}