package com.news.app.ui.articles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.databinding.ActivityArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsActivity : AppCompatActivity() {

    companion object {
        private const val BUNDLE_ARTICLE = "article"
        fun launch(context: Activity, article: Article) {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(BUNDLE_ARTICLE, article)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.slide_up_animation, R.anim.fade_exit_transition)
        }
    }

    private lateinit var viewBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_details)

        val article: Article = intent.getParcelableExtra(BUNDLE_ARTICLE)
            ?: throw Error("Article details are required to launch")

        viewBinding.data = article

        viewBinding.ivBack.setOnClickListener { onBackPressed() }
    }

}