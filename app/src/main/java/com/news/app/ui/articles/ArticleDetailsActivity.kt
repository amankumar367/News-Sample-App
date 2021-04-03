package com.news.app.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.news.app.R
import com.news.app.data.Article
import com.news.app.databinding.ActivityArticleDetailsBinding
import dagger.android.support.DaggerAppCompatActivity

class ArticleDetailsActivity : DaggerAppCompatActivity() {

    companion object {
        private const val BUNDLE_ARTICLE = "article"
        fun start(context: Context, article: Article) {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_ARTICLE, article)
            intent.putExtras(bundle)
            context.startActivity(intent)
            (context as ArticleActivity).overridePendingTransition(R.anim.slide_up_animation, R.anim.fade_exit_transition)
        }
    }

    private lateinit var viewBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_details)

        val article: Article? = intent.extras?.getParcelable(BUNDLE_ARTICLE)
        viewBinding.data = article

        viewBinding.ivBack.setOnClickListener { onBackPressed() }
    }

}