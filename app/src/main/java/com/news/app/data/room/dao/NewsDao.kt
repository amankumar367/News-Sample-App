package com.news.app.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.news.app.data.models.Article

@Dao
interface NewsDao {

    @Insert
    fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM ARTICLE")
    fun getArticles(): List<Article>

    @Query("DELETE FROM ARTICLE")
    fun deleteAllArticles()

}