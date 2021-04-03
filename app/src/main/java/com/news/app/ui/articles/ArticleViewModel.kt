package com.news.app.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.app.base.BaseViewModel
import com.news.app.repo.repointerface.IArticleRepo

class ArticleViewModel(
    private val articleRepo: IArticleRepo
): BaseViewModel<ArticleState>() {

    private var state: ArticleState = ArticleState.Init
        set(value) {
            field = value
            publishState(value)
        }

    override val stateObservable: MutableLiveData<ArticleState> by lazy {
        MutableLiveData<ArticleState>()
    }

    // q=tesla&from=2021-03-03&sortBy=publishedAt

    class Factory(private val articleRepo: IArticleRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ArticleViewModel(articleRepo) as T
        }
    }
}