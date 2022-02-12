package com.news.app.ui.articles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.extensions.RequestStateRender
import com.news.app.extensions.formatDate
import com.news.app.extensions.getCurrentDate
import com.news.app.extensions.getImageUrl
import com.news.app.ui.theme.NewsSampleAppTheme
import com.news.app.ui.theme.gray
import com.news.app.ui.theme.gray200
import com.news.app.ui.theme.pink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsSampleAppTheme {
                ArticleScreen()
            }
        }
    }

    private fun loadData(fetchFromNetwork: Boolean = false) {
        viewModel.getArticles(QUERY, getCurrentDate(), SORT_BY, fetchFromNetwork)
    }

    @Composable
    private fun ArticleScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.header_headlines),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.h5.copy(
                                fontSize = 29.sp,
                                letterSpacing = 3.sp
                            )
                        )
                    },
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                )
            },
            content = { ArticleScreenContent() }
        )
    }

    @Composable
    private fun ArticleScreenContent() {
        LaunchedEffect(key1 = Unit, block = {
            loadData()
        })
        RequestStateRender(
            state = viewModel.articleState.collectAsState(),
            onLoading = { ShowLoading() },
            onError = { ShowError(it) },
            onSuccess = {
                ArticleList(it) { article ->
                    ArticleDetailsActivity.launch(this, article)
                }
            }
        )
    }

    @Composable
    private fun ShowLoading() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }


    @Composable
    private fun ShowError(exception: Exception) {
        Log.e(TAG, "Failed to fetch articles: ${exception.localizedMessage}")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            val errorMessage = exception.localizedMessage ?: stringResource(R.string.error_message)
            Text(
                text = errorMessage,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                modifier = Modifier.size(120.dp, height = 40.dp),
                onClick = { loadData(fetchFromNetwork = true) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = pink,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }

    @Composable
    private fun ArticleList(articles: List<Article>, onItemClicked: (Article) -> Unit) {
        LazyColumn(modifier = Modifier.background(color = gray)) {
            items(items = articles) { article ->
                ArticleItem(article, onItemClicked)
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun ArticleItem(article: Article, onItemClicked: (Article) -> Unit) {
        Card(
            onClick = { onItemClicked(article) },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 8.dp
        ) {

            var sizeImage by remember { mutableStateOf(IntSize.Zero) }
            val gradient = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = sizeImage.height.toFloat() / 2,
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    CardContent(article)
                }
            }
        }
    }

    @Composable
    private fun CardContent(article: Article) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = article.title.orEmpty(),
                color = Color.White,
                style = MaterialTheme.typography.h6,
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = article.author.orEmpty(),
                    color = gray200,
                    style = MaterialTheme.typography.h6.copy(fontSize = 12.sp),
                    modifier = Modifier.weight(3F),
                    maxLines = 1
                )
                Text(
                    text = article.publishedAt.orEmpty().formatDate(),
                    color = gray200,
                    style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
                    modifier = Modifier.weight(1F),
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewsSampleAppTheme {
            ArticleList(listOf()) { }
        }
    }

    companion object {
        private const val TAG = "ArticleActivity"
        private const val QUERY = "tesla"
        private const val SORT_BY = "publishedAt"

        fun launch(context: Activity) {
            context.startActivity(Intent(context, ArticleActivity::class.java))
        }
    }

}