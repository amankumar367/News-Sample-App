package com.news.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.news.app.data.Article
import com.news.app.extensions.getCurrentDate
import com.news.app.factory.articles.ArticleFactory
import com.news.app.repo.ArticleRepo
import com.news.app.scheduler.RxJavaTestScheduler
import com.news.app.ui.articles.ArticleState
import com.news.app.ui.articles.ArticleViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import com.nhaarman.mockitokotlin2.any

@RunWith(JUnit4::class)
class ArticlesViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var scheduler: RxJavaTestScheduler = RxJavaTestScheduler()

    @Mock
    lateinit var articlesRepository: ArticleRepo

    @Mock
    private lateinit var stateObserver: Observer<ArticleState>

    private lateinit var articleViewModel: ArticleViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        articleViewModel = ArticleViewModel(articlesRepository)
        articleViewModel.stateObservable.observeForever(stateObserver)
    }

    @Test
    fun fetchArticleList_returnsEmpty() {
        // Arrange
        stubFetchArticles(Single.just(listOf()))

        // Act
        articleViewModel.getArticles("", "", "")

        // Assert
        verify(stateObserver).onChanged(ArticleState.Loading)
        verify(stateObserver).onChanged(ArticleState.UpdateUI(listOf()))
    }

    @Test
    fun fetchArticleList_returnsError() {
        // Arrange
        stubFetchArticles(Single.error(TestingException(TestingException.GENERIC_EXCEPTION_MESSAGE)))

        // Act
        articleViewModel.getArticles("", "", "")

        // Assert
        verify(stateObserver).onChanged(ArticleState.Loading)
        verify(stateObserver).onChanged(ArticleState.Error(TestingException.GENERIC_EXCEPTION_MESSAGE))
    }

    @Test
    fun fetchArticleList_returnsData() {
        // Arrange
        val listOfArticles = ArticleFactory.generateListOfArticle(10)
        stubFetchArticles(Single.just(listOfArticles))

        // Act
        articleViewModel.getArticles("", "", "")

        // Assert
        verify(stateObserver).onChanged(ArticleState.Loading)
        verify(stateObserver).onChanged(ArticleState.UpdateUI(listOfArticles))
    }


    /**
     * Stub Helpers Methods
     */
    private fun stubFetchArticles(single: Single<List<Article>>) {
        `when`(articlesRepository.getArticles("", "", "", false))
            .thenReturn(single)
    }

    class TestingException(message: String = GENERIC_EXCEPTION_MESSAGE) : Exception(message) {
        companion object {
            const val GENERIC_EXCEPTION_MESSAGE = "Something went wrong please try again."
        }
    }

}
