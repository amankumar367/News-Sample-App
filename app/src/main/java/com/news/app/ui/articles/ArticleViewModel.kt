package com.news.app.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.app.data.models.Article
import com.news.app.data.repo.ArticleRepository
import com.news.app.extensions.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepo: ArticleRepository
) : ViewModel() {

    private val _articleState = MutableStateFlow<RequestState<List<Article>, Exception>>(RequestState.Idle)
    val articleState: StateFlow<RequestState<List<Article>, Exception>> = _articleState

    fun getArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean = false
    ) {
        viewModelScope.launch {
            _articleState.value = RequestState.Loading
            try {
                val articles = articleRepo.fetchArticles(query, from, sortBy, fetchFromNetwork)
                _articleState.value = RequestState.Success(articles)
            } catch (exception: Exception) {
                _articleState.value = RequestState.Error(exception)
            }
        }
    }

}