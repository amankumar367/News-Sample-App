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

class ArticleRepo @Inject constructor(
    var apiInterface: ApiInterface,
    var preferencesHelper: PreferencesHelper,
    var database: AppDatabase
): IArticleRepo {

    // q=tesla&from=2021-03-03&sortBy=publishedAt
    override fun getArticles(query: String?, from: String?, sortBy: String?): Single<List<Article>> {
        return Single.create { emitter ->
            apiInterface.getArticles(query, from, sortBy).enqueue(object: Callback<ArticleResponse> {
                override fun onResponse(
                    call: Call<ArticleResponse>,
                    response: Response<ArticleResponse>
                ) {
                    if (response.body() != null && response.isSuccessful) {
                        emitter.onSuccess(response.body()?.articles ?: listOf())
                    }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

}