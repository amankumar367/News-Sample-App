package com.news.app.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news.app.data.models.Article
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>): Completable

    @Query("SELECT * FROM ARTICLE")
    fun getArticles(): Single<List<Article>>

    @Query("DELETE FROM ARTICLE")
    fun deleteAllArticles(): Completable

}