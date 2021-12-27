package com.news.app.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.databinding.ActivityArticleBinding
import com.news.app.extensions.getCurrentDate
import com.news.app.ui.adapter.ArticleAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val viewModel: ArticleViewModel by viewModels { vmFactory }

    private lateinit var binding: ActivityArticleBinding
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article)

        setAdapter()
        setObserver()
        loadData()
        onClick()

    }

    private fun loadData(fetchFromNetwork: Boolean = false) {
        viewModel.getArticles(QUERY, getCurrentDate(), SORT_BY, fetchFromNetwork)
    }

    private fun onClick() {
        binding.btnRetry.setOnClickListener {
            loadData(true)
            setLoadingState()
        }

        binding.swipeRefresh.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun setAdapter() {
        adapter = ArticleAdapter(object : ArticleAdapter.OnItemCLickListener {
            override fun onItemClick(article: Article) {
                val controller = AnimationUtils.loadLayoutAnimation(
                    this@ArticleActivity,
                    R.anim.layout_animation_fall_down
                )
                binding.rvArticles.layoutAnimation = controller
                binding.rvArticles.scheduleLayoutAnimation()
                ArticleDetailsActivity.start(this@ArticleActivity, article)
            }
        })
        binding.rvArticles.adapter = adapter
    }

    private fun updateArticleList(articles: List<Article>) {
        adapter.updateList(articles)
    }

    private fun setObserver() {
        viewModel.articleState.observe(this) { state ->
            when (state) {
                ArticleState.Loading -> setLoadingState()
                is ArticleState.Error -> setErrorState(state.error)
                is ArticleState.Success -> setSuccessState(state)
            }
        }
    }

    private fun setLoadingState() {
        showLoading()
        hideContent()
        hideNetworkError()
    }

    private fun setSuccessState(state: ArticleState.Success) {
        updateArticleList(state.articles)
        showContent()
        hideLoading()
        hideNetworkError()
    }

    private fun setErrorState(state: Throwable) {
        Log.e(TAG, "Failed to fetch articles", state)
        binding.tvErrorMessage.text = state.message
        hideContent()
        hideLoading()
        showNetworkError()
    }

    private fun showLoading() {
        binding.showProgress = true
    }

    private fun hideLoading() {
        binding.showProgress = false
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showContent() {
        binding.showContent = true
    }

    private fun hideContent() {
        binding.showContent = false
    }

    private fun showNetworkError() {
        binding.showError = true
    }

    private fun hideNetworkError() {
        binding.showError = false
    }

    companion object {
        private const val TAG = "ArticleActivity"
        private const val QUERY = "tesla"
        private const val SORT_BY = "publishedAt"

        fun start(context: Context) {
            context.startActivity(Intent(context, ArticleActivity::class.java))
        }
    }

}