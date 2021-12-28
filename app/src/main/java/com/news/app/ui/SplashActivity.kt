package com.news.app.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.news.app.R
import com.news.app.ui.articles.ArticleActivity
import kotlinx.android.synthetic.main.activity_splash.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in_center)
        ivSplash.startAnimation(animationFadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            ArticleActivity.launch(this)
        }, 2000)
    }
}