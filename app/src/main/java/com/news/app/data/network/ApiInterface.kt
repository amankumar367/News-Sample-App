package com.news.app.data.network

import com.news.app.data.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("everything?language=en")
    fun getArticles(
        @Query("q") query: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?
    ): Call<ArticleResponse>

}