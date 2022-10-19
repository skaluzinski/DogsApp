package com.example.dogsapp.quotes.di

import android.content.Context
import androidx.room.Room
import com.example.dogsapp.quotes.data.AppDatabase
import com.example.dogsapp.quotes.data.IQuotesRepository
import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.quotes.data.remote.HeaderInterceptor
import com.example.dogsapp.quotes.data.remote.IQuotesRemoteDataSource
import com.example.dogsapp.quotes.data.remote.QuotesApi
import com.example.dogsapp.quotes.data.remote.QuotesRemoteDataSource
import com.example.dogsapp.quotes.domain.*
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

    @Singleton
    @Provides
    fun provideSavedQuotesDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "savedQuotes"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuotesDao(appDatabase: AppDatabase): SavedQuotesDao {
        return appDatabase.SavedQuotesDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractQuotesModule {

    @Binds
    abstract fun bindSaveQuoteUseCase(saveQuoteUseCase: SaveQuoteUseCase): ISaveQuoteUseCase

    @Binds
    @Singleton
    abstract fun bindQuotesRepository(quotesRepository: QuotesRepository): IQuotesRepository

    @Binds
    @Singleton
    abstract fun bindRandomQuoteUseCase(getRandomQuoteUseCase: GetRandomQuoteUseCase): IGetRandomQuoteUseCase

    @Binds
    @Singleton
    abstract fun bindAllQuotesUseCase(allQuotesUseCase: GetAllQuotesUseCase): IGetAllQuotesUseCase

    @Binds
    @Singleton
    abstract fun bindQuotesRemoteDataSource(quotesRemoteDataSource: QuotesRemoteDataSource): IQuotesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCheckIfQuoteIsSavedUseCase(checkIfQuoteIsSavedUseCase: CheckIfQuoteIsSavedUseCase): ICheckIfQuoteIsSavedUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteQuotesLocallyUseCase(deleteQuoteLocallyUseCase: DeleteQuoteLocallyUseCase): IDeleteQuoteLocallyUseCase
}

