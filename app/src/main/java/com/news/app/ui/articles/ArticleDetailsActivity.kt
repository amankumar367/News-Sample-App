package com.news.app.ui.articles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.extensions.formatDate
import com.news.app.extensions.getImageUrl
import com.news.app.ui.theme.NewsSampleAppTheme
import com.news.app.ui.theme.gray200
import com.news.app.ui.theme.transparentBlack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        readParams()

        setContent {
            NewsSampleAppTheme {
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
            val scrollState = rememberScrollState(0)
            var sizeImage by remember { mutableStateOf(IntSize.Zero) }
            val gradient = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = sizeImage.height.toFloat() / 3,
                endY = sizeImage.height.toFloat()
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(getImageUrl(article.urlToImage, article.url)),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .onGloballyPositioned { sizeImage = it.size }
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(gradient)
                )

                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 30.dp)
                        .align(Alignment.TopStart)
                        .background(transparentBlack, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .align(BottomCenter)
                ) {
                    ScrollableContent(scrollState)
                }
            }
        }
    }

    @Composable
    private fun ScrollableContent(scrollState: ScrollState) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .verticalScroll(state = scrollState)
        ) {
            ArticleTitle(article.title.orEmpty())
            Spacer(modifier = Modifier.height(30.dp))
            ArticleAuthorAndPublishTime(article.author.orEmpty(), article.publishedAt.orEmpty())
            Spacer(modifier = Modifier.height(10.dp))
            ArticleDescription(article.description.orEmpty())
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    @Composable
    fun ArticleTitle(title: String, modifier: Modifier = Modifier) {
        Text(
            text = title,
            color = Color.White,
            style = typography.h5,
            modifier = modifier,
            maxLines = 3
        )
    }

    @Composable
    private fun ArticleDescription(description: String, modifier: Modifier = Modifier) {
        Text(
            text = description,
            color = gray200,
            style = typography.body2,
            modifier = modifier
        )
    }

    @Composable
    private fun ArticleAuthorAndPublishTime(
        author: String,
        publishedAt: String,
        modifier: Modifier = Modifier
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                text = author,
                color = Color.White,
                style = typography.body1,
                modifier = Modifier.weight(3F),
                maxLines = 1
            )
            Text(
                text = publishedAt.formatDate(),
                color = Color.White,
                style = typography.body1,
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.End
            )
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewsSampleAppTheme {
            ArticleDetailsScreen()
        }
    }

    companion object {
        private const val BUNDLE_ARTICLE = "article"

        fun launch(context: Activity, article: Article) {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(BUNDLE_ARTICLE, article)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.slide_up_animation, R.anim.fade_exit_transition)
        }
    }

}