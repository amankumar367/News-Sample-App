package com.news.app.network.interceptor


import com.news.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {

    @Throws(IOException::class, StackOverflowError::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        val request = original.newBuilder()
            .header("Authorization", BuildConfig.NEWS_API_KEY)
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)

    }
}


