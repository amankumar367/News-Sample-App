package com.news.app.ui.articles

import androidx.lifecycle.MutableLiveData
import com.news.app.base.BaseViewModel
import com.news.app.extensions.transform
import com.news.app.data.repo.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleViewModel(
    private val articleRepo: ArticleRepository
): BaseViewModel<ArticleState>() {

    private var state: ArticleState = ArticleState.Init
        set(value) {
            field = value
            publishState(value)
        }

    override val stateObservable: MutableLiveData<ArticleState> by lazy {
        MutableLiveData<ArticleState>()
    }

    fun getArticles(query: String?, from: String?, sortBy: String?, fetchFromNetwork: Boolean = false) {
        state = ArticleState.Loading
        disposables.add(
            articleRepo.getArticles(query, from, sortBy, fetchFromNetwork)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state = ArticleState.UpdateUI(it)
                }, {
                    state = ArticleState.Error(it.transform().localizedMessage)
                })
        )
    }

}