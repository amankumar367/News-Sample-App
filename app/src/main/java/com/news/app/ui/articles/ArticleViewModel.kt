package com.news.app.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.app.base.BaseViewModel
import com.news.app.extensions.transform
import com.news.app.repo.repointerface.IArticleRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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

    fun getArticles(query: String?, from: String?, sortBy: String?) {
        state = ArticleState.Loading
        disposables.add(
            articleRepo.getArticles(query, from, sortBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state = ArticleState.UpdateUI(it)
                }, {
                    state = ArticleState.Error(it.transform().localizedMessage)
                })
        )
    }

    class Factory(private val articleRepo: IArticleRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ArticleViewModel(articleRepo) as T
        }
    }
}