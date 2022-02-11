package com.news.app.ui.articles

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.ui.theme.NewsSampleAppTheme
import com.news.app.ui.theme.transparentBlack
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

    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsSampleAppTheme {
                readParams()
                ArticleDetailsScreen()
            }
        }
    }

    private fun readParams() {
        article = intent.getParcelableExtra(BUNDLE_ARTICLE)
            ?: throw Error("Article details are required to launch")
    }

    @Composable
    private fun ArticleDetailsScreen() {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                var sizeImage by remember { mutableStateOf(IntSize.Zero) }
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = sizeImage.height.toFloat() / 3,
                    endY = sizeImage.height.toFloat()
                )

                Box {
                    Image(
                        painter = rememberImagePainter(getImageUrl(article.urlToImage, article.url)),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .onGloballyPositioned { sizeImage = it.size }
                            .fillMaxSize()
                    )

                    Box(modifier = Modifier.matchParentSize().background(gradient))

                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.TopStart)
                            .background(transparentBlack, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }

    private fun getImageUrl(url: String?, articleUrl: String?): String {
        return if (url == null) {
            val iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
            String.format(iconUrl, Uri.parse(articleUrl).authority)
        } else url
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewsSampleAppTheme {
            ArticleDetailsScreen()
        }
    }

}