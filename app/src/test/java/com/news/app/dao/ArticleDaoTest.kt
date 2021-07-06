package com.news.app.dao

import androidx.room.Room
import com.news.app.factory.articles.ArticleFactory
import com.news.app.data.room.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ArticleDaoTest {

    lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.baseContext,
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun addArticlesToDatabase() {
        // Arrange
        val articles = ArticleFactory.generateListOfArticle(5)

        // Act
        appDatabase.newsDao().insertArticles(articles)

        // Assert
        val storedArticles = appDatabase.newsDao().getArticles()
        assert(storedArticles.isNotEmpty())
    }

    @Test
    fun getArticles_returnData() {
        // Arrange
        val articles = ArticleFactory.generateListOfArticle(5)

        // Act
        appDatabase.newsDao().insertArticles(articles)

        // Assert
        val storedArticles = appDatabase.newsDao().getArticles()
        assert(articles.size == storedArticles.size)
    }

    @Test
    fun getArticles_returnEmpty() {
        // Arrange
        // No Arrangement needed

        // Act
        val storedArticles = appDatabase.newsDao().getArticles()

        // Assert
        assert(storedArticles.isEmpty())
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }
}
