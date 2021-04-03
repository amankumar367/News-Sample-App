package com.news.app.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {

    @BindingAdapter("bind:url", "bind:articleUrl")
    @JvmStatic
    fun loadThumbnailImage(imageView: ImageView, url: String?, articleUrl: String?) {
        var originalUrl = url
        if (originalUrl == null) {
            val iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
            originalUrl = String.format(iconUrl, Uri.parse(articleUrl).authority)
        }
        Glide.with(imageView)
                .load(url)
                .into(imageView)
    }

    @BindingAdapter( "bind:url", "bind:articleUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?, articleUrl: String?) {
        var originalUrl = url
        if (originalUrl == null) {
            val iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
            originalUrl = String.format(iconUrl, Uri.parse(articleUrl).authority)
        }
        Glide.with(imageView)
                .load(originalUrl)
                .into(imageView)
    }

    @JvmStatic
    fun formatDate(date: String?): String {
        var formatedDate = ""
        date?.let {
            formatedDate = it.split("T")[0]
        }
        return formatedDate
    }


}