package com.news.app.data.repo.network

import com.news.app.data.models.ArticleResponse
import io.reactivex.Single

interface ArticleNetworkManager {

    fun fetchArticle(queryMap: HashMap<String, String?>): Single<ArticleResponse>

}