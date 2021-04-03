package com.news.app.ui.articles

import android.os.Bundle
import com.news.app.R
import dagger.android.support.DaggerAppCompatActivity

class ArticleDetailsActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
    }
}