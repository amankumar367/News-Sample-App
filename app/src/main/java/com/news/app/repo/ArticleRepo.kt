package com.news.app.repo

import com.news.app.data.Article
import com.news.app.data.ArticleResponse
import com.news.app.helper.PreferencesHelper
import com.news.app.network.ApiInterface
import com.news.app.repo.repointerface.IArticleRepo
import com.news.app.room.database.AppDatabase
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Expiration time set to 5 minutes
 */
const val EXPIRATION_TIME = (60 * 5 * 1000).toLong()

class ArticleRepo @Inject constructor(
    var apiInterface: ApiInterface,
    var preferencesHelper: PreferencesHelper,
    var database: AppDatabase
): IArticleRepo {

    override fun getArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean
    ): Single<List<Article>> {

        return Single.create { emitter ->
            if (shouldFetchFromNetwork() || fetchFromNetwork) {
                apiInterface.getArticles(query, from, sortBy).enqueue(object: Callback<ArticleResponse> {
                    override fun onResponse(
                        call: Call<ArticleResponse>,
                        response: Response<ArticleResponse>
                    ) {
                        if (response.body() != null && response.isSuccessful) {
                            val articles = response.body()?.articles ?: listOf()
                            if (articles.isNotEmpty()) {
                                database.newsDao().deleteAllArticles() // delete past articles
                                database.newsDao().insertArticles(articles) // insert latest articles
                                emitter.onSuccess(articles)
                                preferencesHelper.lastCacheTime = System.currentTimeMillis()
                            } else {
                                emitter.onSuccess(database.newsDao().getArticles())
                            }
                        }
                    }

                    override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                        if (database.newsDao().getArticles().isNotEmpty())
                            emitter.onSuccess(database.newsDao().getArticles())
                        else
                            emitter.onError(t)
                    }
                })
            } else {
                emitter.onSuccess(database.newsDao().getArticles())
            }
        }

    }

    override fun shouldFetchFromNetwork(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = preferencesHelper.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

}