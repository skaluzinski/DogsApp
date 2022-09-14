package com.example.dogsapp.quotes.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val API_KEY = "HNXi5rTruaM7JKfAM6g1JQ==OPheU1nj36mkYbh3"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request
            .newBuilder()
            .addHeader("X-Api-Key", API_KEY)
            .build()
        return chain.proceed(request)
    }
}