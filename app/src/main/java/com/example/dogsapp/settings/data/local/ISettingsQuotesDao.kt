package com.example.dogsapp.settings.data.local

import androidx.room.Dao
import androidx.room.Query
@Dao
interface ISettingsQuotesDao {
    @Query("DELETE FROM savedQuotes")
    fun deleteSavedQuotes()
}