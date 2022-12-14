package com.example.dogsapp.quotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.settings.data.local.ISettingsQuotesDao


@Database(entities = [SavedQuotes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun SavedQuotesDao(): SavedQuotesDao

    abstract fun SettingsQuotesDao(): ISettingsQuotesDao
}