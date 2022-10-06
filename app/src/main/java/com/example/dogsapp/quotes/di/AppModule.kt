package com.example.dogsapp.quotes.di

import android.content.Context
import androidx.room.Room
import com.example.dogsapp.quotes.data.local.SavedQuotesDatabase
import com.example.dogsapp.quotes.data.remote.IQuotesRemoteDataSource
import com.example.dogsapp.quotes.data.remote.QuotesApi
import com.example.dogsapp.quotes.data.remote.QuotesRemoteDataSource
import com.example.dogsapp.quotes.domain.GetAllQuotesUseCase
import com.example.dogsapp.quotes.domain.GetRandomQuoteUseCase
import com.example.dogsapp.quotes.domain.IGetAllQuotesUseCase
import com.example.dogsapp.quotes.domain.IGetRandomQuoteUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://random-quote-api1.p.rapidapi.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideHttpClientBuilder(interceptor: Interceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(interceptor)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideQuotesRemoteDataSource(
        quotesApi: QuotesApi,
        defaultDispatcher: CoroutineDispatcher
    ): IQuotesRemoteDataSource {
        return QuotesRemoteDataSource(quotesApi, defaultDispatcher)
    }

    @Provides
    @Singleton
    fun provideAllQuotesUseCase(quotesRemoteDataSource: QuotesRemoteDataSource): IGetAllQuotesUseCase {
        return GetAllQuotesUseCase(quotesRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRandomQuoteUseCase(quotesRemoteDataSource: QuotesRemoteDataSource): IGetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(quotesRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideSavedQuotesDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        SavedQuotesDatabase::class.java,
        "saved_quotes"
    ).build()

    @Singleton
    @Provides
    fun provideSavedQuotesDao(db: SavedQuotesDatabase) = db.getSavedQuotesDao()
}