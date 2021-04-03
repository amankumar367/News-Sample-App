package com.news.app.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import com.news.app.data.Article
import com.news.app.databinding.ActivityArticleBinding
import com.news.app.extensions.createFactory
import com.news.app.repo.repointerface.IArticleRepo
import com.news.app.ui.adapter.ArticleAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "ArticleActivity"
        fun start(context: Context) {
            context.startActivity(Intent(context, ArticleActivity::class.java))
        }
    }

    @Inject
    lateinit var articleRepo: IArticleRepo

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
                ArticleState.Loading -> viewBinding.showProgress = true
                is ArticleState.Error -> {
                    viewBinding.tvErrorMessage.text = it.message
                    viewBinding.showError = true
                    viewBinding.showProgress = false
                }
                is ArticleState.UpdateUI -> {
                    viewBinding.showProgress = false
                    setAdapter(it.articles)
                }
                else -> { }
            }
        })
    }

    private fun loadData() {
        viewModel.getArticles("tesla", "2021-03-03", "publishedAt")
    }

    private fun onClick() {
        viewBinding.btnRetry.setOnClickListener {
            loadData()
            viewBinding.showError = false
        }
    }

    private fun setAdapter(articles: List<Article>) {
        viewBinding.rvArticles.adapter = ArticleAdapter(articles, object: ArticleAdapter.OnItemCLickListener {
            override fun onItemClick(repoView: Article) {

            }
        })
    }
}