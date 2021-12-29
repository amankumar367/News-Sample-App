package com.news.app.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.app.data.repo.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepo: ArticleRepository
) : ViewModel() {

    private var _articleState = MutableSharedFlow<ArticleState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val articleState: SharedFlow<ArticleState> = _articleState

    fun getArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean = false
    ) {
        viewModelScope.launch {
            if (fetchFromNetwork.not()) _articleState.emit(ArticleState.Loading)
            try {
                val articles = articleRepo.fetchArticles(query, from, sortBy, fetchFromNetwork)
                _articleState.emit(ArticleState.Success(articles))
            } catch (exception: Exception) {
                _articleState.emit(ArticleState.Error(exception))
            }
        }
    }

}