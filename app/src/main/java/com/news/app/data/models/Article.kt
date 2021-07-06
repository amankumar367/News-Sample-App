package com.news.app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "article")
@Parcelize
data class Article(

    @ColumnInfo(name = "author")
    @SerializedName("author")
    var author: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    var content: String? = null
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "articleId")
    var articleId: Int = 0
}