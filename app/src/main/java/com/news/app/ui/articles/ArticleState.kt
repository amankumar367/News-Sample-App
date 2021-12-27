package com.news.app.ui.articles

import com.news.app.data.models.Article

sealed class ArticleState {

    object Loading : ArticleState()

    data class Error(var error: Throwable) : ArticleState()

    data class Success(var articles: List<Article>) : ArticleState()

}