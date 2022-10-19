package com.example.dogsapp.settings.data

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.settings.data.local.ISettingsSavedQuotesLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsQuotesRepository @Inject constructor(
    private val savedQuotesLocalDataSource: ISettingsSavedQuotesLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ISettingQuotesRepository {
    override suspend fun deleteAllQuotes() = withContext(defaultDispatcher) {
        savedQuotesLocalDataSource.deleteSavedQuotes()
    }
}