package com.news.app.repo

import com.news.app.data.ArticleResponse
import com.news.app.helper.PreferencesHelper
import com.news.app.network.ApiInterface
import com.news.app.repo.repointerface.IArticleRepo
import com.news.app.room.database.AppDatabase
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepo @Inject constructor(
    var apiInterface: ApiInterface,
    var preferencesHelper: PreferencesHelper,
    var database: AppDatabase
): IArticleRepo {

    override fun getArticles(): Single<ArticleResponse> {
        return Single.create {  }
    }

}