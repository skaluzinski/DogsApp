package com.example.dogsapp.settings.data

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.local.ISavedQuotesLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingQuotesRepository @Inject constructor(
    private val savedQuotesLocalDataSource: ISavedQuotesLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun deleteAllQuotes() = withContext(defaultDispatcher) {
        savedQuotesLocalDataSource.deleteSavedQuotes()
    }
}