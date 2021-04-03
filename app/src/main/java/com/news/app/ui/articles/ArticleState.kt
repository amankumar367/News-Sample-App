package com.news.app.ui.articles

sealed class ArticleState {
    object Init : ArticleState()
    object Loading : ArticleState()
    data class Error(var message: String?) : ArticleState()
}