package com.news.app.data.network

import com.news.app.data.models.ArticleResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("everything?language=en")
    fun fetchArticles(
        @QueryMap queryMap: HashMap<String, String?>
    ): Single<Response<ArticleResponse>>

}