package com.example.dogsapp.settings.data.local

import javax.inject.Inject

class SavedQuotesLocalDataSource @Inject constructor(
    private val savedQuotesDatabase: ISettingsQuotesDao,
) : ISettingsSavedQuotesLocalDataSource {
    override suspend fun deleteSavedQuotes() {
        savedQuotesDatabase.deleteSavedQuotes()
    }
}