package com.news.app.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.news.app.R
import com.news.app.repo.repointerface.IArticleRepo
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleActivity : DaggerAppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ArticleActivity::class.java))
        }
    }

    @Inject
    lateinit var articleRepo: IArticleRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
    }
}