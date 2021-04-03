package com.news.app.repo.repointerface

import com.news.app.data.Article
import com.news.app.data.ArticleResponse
import io.reactivex.Single

interface IArticleRepo {
    fun getArticles(query: String?, from: String?, sortBy: String?): Single<List<Article>>
}