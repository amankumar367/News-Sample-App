package com.news.app.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.app.data.repo.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepo: ArticleRepository
) : ViewModel() {

    private var _articleState = MutableStateFlow<ArticleState>(ArticleState.Success(emptyList()))
    val articleState: StateFlow<ArticleState> = _articleState

    fun getArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean = false
    ) {
        if (fetchFromNetwork.not()) _articleState.value = ArticleState.Loading
        viewModelScope.launch {
            try {
                val articles = articleRepo.fetchArticles(query, from, sortBy, fetchFromNetwork)
                _articleState.value = ArticleState.Success(articles)
            } catch (exception: Exception) {
                _articleState.value = ArticleState.Error(exception)
            }
        }
    }

}