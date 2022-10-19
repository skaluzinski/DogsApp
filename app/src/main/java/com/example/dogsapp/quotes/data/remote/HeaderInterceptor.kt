package com.example.dogsapp.quotes.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val API_KEY = "94e5a94790msh060c81a7a6f28f8p1d375cjsne963a4a25fb6"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request
            .newBuilder()
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", "random-quote-api1.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}