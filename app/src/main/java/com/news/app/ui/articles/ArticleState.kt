package com.news.app.ui.articles

import com.news.app.data.models.Article

sealed class ArticleState {
    object Init : ArticleState()
    object Loading : ArticleState()
    data class Error(var message: String?) : ArticleState()
    data class Success(var articles: List<Article>) : ArticleState()
}