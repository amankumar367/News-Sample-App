package com.news.app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.news.app.R
import com.news.app.ui.articles.ArticleActivity
import com.news.app.ui.theme.NewsSampleAppTheme
import com.news.app.ui.theme.gray

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsSampleAppTheme {
                SplashScreen()
                Handler(Looper.getMainLooper()).postDelayed({
                    ArticleActivity.launch(this)
                }, 2000)
            }
        }
    }

    @Composable
    fun SplashScreen() {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_news),
                    contentDescription = "",
                )
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewsSampleAppTheme {
            SplashScreen()
        }
    }
}