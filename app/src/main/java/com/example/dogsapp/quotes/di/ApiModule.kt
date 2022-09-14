package com.example.dogsapp.quotes.di

import com.example.dogsapp.quotes.data.remote.QuotesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.api-ninjas.com/v1/quotes?category="

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideQuoteApi(moshi: Moshi, client: OkHttpClient.Builder): QuotesApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(client.build())
            .build()
            .create(QuotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpClientBuilder(interceptor: Interceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(interceptor)
    }

}