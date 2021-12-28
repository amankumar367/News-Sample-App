package com.news.app.data.repo

import android.util.Log
import com.news.app.data.models.Article
import com.news.app.data.repo.network.ArticleNetworkManager
import com.news.app.data.repo.persistence.ArticlePersistenceManager
import com.news.app.extensions.returnNoDataException
import com.news.app.utils.helper.PreferencesHelper
import javax.inject.Inject

/**
 * Expiration time set to 5 minutes
 */
const val EXPIRATION_TIME = (5 * 60 * 1000).toLong()

class ArticleRepositoryImp @Inject constructor(
    private val networkManager: ArticleNetworkManager,
    private val persistenceManager: ArticlePersistenceManager,
    private val preferencesHelper: PreferencesHelper
) : ArticleRepository {

    override val shouldFetchFromNetwork: Boolean
        get() {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = preferencesHelper.lastCacheTime
            return currentTime - lastUpdateTime > EXPIRATION_TIME
        }

    override suspend fun fetchArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean
    ): List<Article> {

        val queryMap = HashMap<String, String?>().apply {
            put("q", query)
            put("from", from)
            put("sortBy", sortBy)
        }

        val articles = if (shouldFetchFromNetwork || fetchFromNetwork) {
            try {
                val articles = networkManager.fetchArticle(queryMap).articles ?: returnNoDataException()
                if (articles.isEmpty()) returnNoDataException()

                val isArticlesDeleted = persistenceManager.deleteAllArticles()
                val isArticlesUpdated = persistenceManager.updateArticles(articles)

                if (isArticlesDeleted && isArticlesUpdated) {
                    preferencesHelper.lastCacheTime = System.currentTimeMillis()
                    persistenceManager.getArticles()
                } else {
                    returnNoDataException()
                }
            } catch (exception: Exception) {
                Log.e(TAG, "Failed to fetch from network", exception)
                persistenceManager.getArticles()
            }
        } else persistenceManager.getArticles()

        return if (articles.isNotEmpty()) articles else returnNoDataException()
    }

    companion object {
        private const val TAG = "ArticleRepositoryImp"
    }

}