package com.news.app.ui.articles

import androidx.lifecycle.MutableLiveData
import com.news.app.base.BaseViewModel
import com.news.app.extensions.transform
import com.news.app.data.repo.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleRepo: ArticleRepository
) : BaseViewModel<ArticleState>() {

    private var state: ArticleState = ArticleState.Init
        set(value) {
            field = value
            publishState(value)
        }

    override val stateObservable: MutableLiveData<ArticleState> by lazy {
        MutableLiveData<ArticleState>()
    }

    fun getArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean = false
    ) {
        if (fetchFromNetwork.not()) state = ArticleState.Loading
        disposables.add(
            articleRepo.fetchArticles(query, from, sortBy, fetchFromNetwork)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articles ->
                    state = if (articles.isEmpty())
                        ArticleState.Error("No data found")
                    else
                        ArticleState.Success(articles)
                }, {
                    state = ArticleState.Error(it.transform().localizedMessage)
                })
        )
    }

}