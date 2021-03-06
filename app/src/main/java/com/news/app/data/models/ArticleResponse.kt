package com.news.app.data.models

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: List<Article>? = null
)