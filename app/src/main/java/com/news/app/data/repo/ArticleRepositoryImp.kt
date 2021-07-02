package com.news.app.data.repo

import com.news.app.data.models.Article
import com.news.app.data.repo.network.ArticleNetworkManager
import com.news.app.data.repo.persistence.ArticlePersistenceManager
import com.news.app.extensions.returnNoDataException
import com.news.app.utils.helper.PreferencesHelper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Expiration time set to 5 minutes
 */
const val EXPIRATION_TIME = (5 * 60 * 1000).toLong()

class ArticleRepositoryImp @Inject constructor(
    private val articleNetworkManager: ArticleNetworkManager,
    private val articlePersistenceManager: ArticlePersistenceManager,
    private val preferencesHelper: PreferencesHelper
) : ArticleRepository {

    override val shouldFetchFromNetwork: Boolean
        get() {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = preferencesHelper.lastCacheTime
            return currentTime - lastUpdateTime > EXPIRATION_TIME
        }

    override fun fetchArticles(
        query: String?,
        from: String?,
        sortBy: String?,
        fetchFromNetwork: Boolean
    ): Single<List<Article>> {

        val queryMap = HashMap<String, String?>().apply {
            put("q", query)
            put("from", from)
            put("sortBy", sortBy)
        }

        return if (shouldFetchFromNetwork || fetchFromNetwork) {
            articleNetworkManager.fetchArticle(queryMap).flatMap { articleResponse ->
                val articles = articleResponse.articles ?: returnNoDataException()
                articlePersistenceManager.deleteAllArticles().blockingAwait()
                val isArticleSaved = articlePersistenceManager.saveArticles(articles)
                preferencesHelper.lastCacheTime = System.currentTimeMillis()
                isArticleSaved.toSingleDefault(articles)
            }.onErrorResumeNext {
                articlePersistenceManager.getArticles().flatMap { list ->
                    if (list.isNullOrEmpty())
                        returnNoDataException(it.localizedMessage ?: "No data found")
                    else
                        Single.just(list)
                }
            }
        } else articlePersistenceManager.getArticles()

    }

}