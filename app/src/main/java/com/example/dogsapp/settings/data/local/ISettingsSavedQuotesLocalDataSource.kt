package com.example.dogsapp.settings.data.local


interface ISettingsSavedQuotesLocalDataSource {

    suspend fun deleteSavedQuotes()
}