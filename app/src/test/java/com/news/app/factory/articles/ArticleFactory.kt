package com.news.app.factory.articles

import com.news.app.data.Article
import com.news.app.factory.DataFactory

object ArticleFactory {

    fun generateListOfArticle(size: Int): MutableList<Article> {
        val listOfArticles = mutableListOf<Article>()
        repeat(size) {
            listOfArticles.add(generateArticles())
        }
        return listOfArticles
    }

    private fun generateArticles(): Article {
        return Article(
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            description = DataFactory.getRandomString(),
            url = DataFactory.getRandomString(),
            urlToImage = DataFactory.getRandomString(),
            publishedAt = DataFactory.getRandomString(),
            content = DataFactory.getRandomString()
        )
    }
}
