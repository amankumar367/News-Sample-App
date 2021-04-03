package com.news.app.repo.repointerface

import com.news.app.data.ArticleResponse
import io.reactivex.Single

interface IArticleRepo {
    fun getArticles(): Single<ArticleResponse>
}