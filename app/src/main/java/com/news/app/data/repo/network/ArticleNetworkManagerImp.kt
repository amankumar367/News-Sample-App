package com.news.app.data.repo.network

import com.news.app.data.models.ArticleResponse
import com.news.app.data.network.ApiInterface
import com.news.app.extensions.returnNoDataException
import io.reactivex.Single
import javax.inject.Inject

class ArticleNetworkManagerImp @Inject constructor(
    private val apiInterface: ApiInterface
) : ArticleNetworkManager {

    override fun fetchArticle(queryMap: HashMap<String, String?>): Single<ArticleResponse> {
        return apiInterface.fetchArticles(queryMap)
            .map {
                if (it.isSuccessful)
                    it.body()
                else
                    returnNoDataException()
            }
    }

}