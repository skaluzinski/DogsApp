package com.example.dogsapp.quotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SavedQuotes::class], version = 1, exportSchema = false)
abstract class SavedQuotesDatabase : RoomDatabase(), ISavedQuotesDatabase {
}