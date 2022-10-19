package com.example.dogsapp.settings.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.settings.data.local.ISettingsQuotesDao

@Database(entities = [SavedQuotes::class], version = 1, exportSchema = false)
abstract class SettingsQuotesDatabase : RoomDatabase() {
    abstract fun settingsQuotesDao(): ISettingsQuotesDao
}