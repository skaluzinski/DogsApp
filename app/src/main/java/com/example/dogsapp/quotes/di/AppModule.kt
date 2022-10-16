package com.example.dogsapp.quotes.di

import android.content.Context
import androidx.room.Room
import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.dogs.di.IoDispatcher
import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.AppDatabase
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.quotes.data.remote.IQuotesRemoteDataSource
import com.example.dogsapp.quotes.data.remote.QuotesApi
import com.example.dogsapp.quotes.data.remote.QuotesRemoteDataSource
import com.example.dogsapp.quotes.domain.*
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
    fun provideQuotesRepository(
        savedQuotesDao: SavedQuotesDao,
        quotesRemoteDataSource: QuotesRemoteDataSource,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): QuotesRepository {
        return QuotesRepository(savedQuotesDao, quotesRemoteDataSource, defaultDispatcher)
    }

    @Provides
    @Singleton
    fun provideAllQuotesUseCase(quotesRepository: QuotesRepository): IGetAllQuotesUseCase {
        return GetAllQuotesUseCase(quotesRepository)
    }

    @Provides
    @Singleton
    fun provideRandomQuoteUseCase(quotesRepository: QuotesRepository): IGetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(quotesRepository)
    }

    @Provides
    @Singleton
    fun provideSaveQuoteUseCase(
        quotesRepository: QuotesRepository,
        @IoDispatcher defaultDispatcher: CoroutineDispatcher
    ): ISaveQuoteUseCase {
        return SaveQuoteUseCase(quotesRepository, defaultDispatcher)
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

    @Singleton
    @Provides
    fun provideCheckIfQuoteIsSavedUseCase(quotesRepository: QuotesRepository): ICheckIfQuoteIsSavedUseCase {
        return CheckIfQuoteIsSavedUseCase(quotesRepository)
    }

    @Provides
    @Singleton
    fun provideQuotesDao(appDatabase: AppDatabase): SavedQuotesDao {
        return appDatabase.SavedQuotesDao()
    }

    @Provides
    @Singleton
    fun provideDeleteQuotesLocallyUseCase(quotesRepository: QuotesRepository): IDeleteQuoteLocallyUseCase {
        return DeleteQuoteLocallyUseCase(quotesRepository)
    }
}