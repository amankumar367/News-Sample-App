package com.news.app.data.network

import com.news.app.data.models.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("everything?language=en")
    suspend fun fetchArticles(
        @QueryMap queryMap: HashMap<String, String?>
    ): Response<ArticleResponse>

}