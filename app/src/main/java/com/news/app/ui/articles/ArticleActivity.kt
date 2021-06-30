package com.news.app.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.databinding.ActivityArticleBinding
import com.news.app.extensions.createFactory
import com.news.app.extensions.getCurrentDate
import com.news.app.extensions.showToast
import com.news.app.data.repo.ArticleRepository
import com.news.app.ui.adapter.ArticleAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "ArticleActivity"
        private const val QUERY = "tesla"
        private const val SORT_BY = "publishedAt"
        fun start(context: Context) {
            context.startActivity(Intent(context, ArticleActivity::class.java))
        }
    }

    @Inject
    lateinit var articleRepo: ArticleRepository

    private lateinit var viewModel: ArticleViewModel

    private lateinit var viewBinding: ActivityArticleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_article)

        init()
        setObserver()
        loadData()
        onClick()
    }

    private fun init() {
        Log.d(TAG, " >>> Initializing viewModel")

        val factory = ArticleViewModel(articleRepo).createFactory()
        viewModel = ViewModelProvider(this, factory).get(ArticleViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.stateObservable.observe( this, Observer {
            when (it) {
                ArticleState.Loading -> {
                    if (viewBinding.swipeRefresh.isRefreshing.not())
                        viewBinding.showProgress = true
                }
                is ArticleState.Error -> {
                    viewBinding.tvErrorMessage.text = it.message
                    viewBinding.showError = true
                    viewBinding.swipeRefresh.isRefreshing = false
                }
                is ArticleState.UpdateUI -> {
                    viewBinding.showProgress = false
                    viewBinding.swipeRefresh.isRefreshing = false
                    setAdapter(it.articles)
                }
                else -> { }
            }
        })
    }

    private fun loadData(fetchFromNetwork: Boolean = false) {
        viewModel.getArticles(QUERY, getCurrentDate(), SORT_BY, fetchFromNetwork)
    }

    private fun onClick() {
        viewBinding.btnRetry.setOnClickListener {
            loadData(true)
            viewBinding.showError = false
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun setAdapter(articles: List<Article>) {
        if (articles.isEmpty()) showToast(getString(R.string.no_data_found))
        viewBinding.rvArticles.adapter = ArticleAdapter(articles, object: ArticleAdapter.OnItemCLickListener {
            override fun onItemClick(article: Article) {
                val controller = AnimationUtils.loadLayoutAnimation(
                        this@ArticleActivity,
                        R.anim.layout_animation_fall_down
                    )
                viewBinding.rvArticles.layoutAnimation = controller
                viewBinding.rvArticles.scheduleLayoutAnimation()
                ArticleDetailsActivity.start(this@ArticleActivity, article)
            }
        })
    }
}